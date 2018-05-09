package com.work.nalandya.pawoon_test.presenter.custom.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.theartofdev.edmodo.cropper.CropImageView;
import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.callback.CallBackCameraUtils;
import com.work.nalandya.pawoon_test.presenter.callback.CallBackCreateImage;
import com.work.nalandya.pawoon_test.presenter.utils.PermissionMarshmellow;
import com.work.nalandya.pawoon_test.presenter.utils.camera.CreateImage;
import com.work.nalandya.pawoon_test.presenter.utils.camera.PickImageChooserIntent;
import com.work.nalandya.pawoon_test.presenter.utils.camera.UtilsCamera;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.camera_custom)
public class CameraCustom extends AppCompatActivity implements CallBackCameraUtils,
        CallBackCreateImage, Camera.AutoFocusCallback {
    private final static String TAG = "ERAGANO-CameraCustom";
    @ViewById(R.id.camera_preview)
    protected FrameLayout cameraPreview;
    @ViewById(R.id.image_show)
    protected CropImageView showImage;
    @ViewById(R.id.camera_view)
    protected FrameLayout cameraView;
    @ViewById(R.id.image_view)
    protected FrameLayout imageViewing;
    @ViewById
    protected Button galery;

    @Bean
    protected PermissionMarshmellow permissionMarshmellow;
    @Bean
    protected UtilsCamera utilsCamera;

    @Extra
    protected int position;
    @Extra
    protected boolean hideGalery;

    private static final int ACTION_LOAD_IMAGE = 100;
    private static int SIZE_W = 800;
    private static int SIZE_H = 600;
    private static final int FOCUS_AREA_SIZE = 300;

    private Bitmap cropped;

    private Camera camera;
    private CameraPreview cameraPreviewer;
    private PickImageChooserIntent pickImageChooserIntent;
    public static String positionImage = "position";
    private boolean flashOn = true;
    private Camera.Size size;
    private boolean cameraClick;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @AfterViews
    protected void afterView() {
        showImage.setFixedAspectRatio(true);
        showImage.setAspectRatio(SIZE_W, SIZE_H);
        showImage.setGuidelines(CropImageView.Guidelines.ON);
        showImage.setCropShape(CropImageView.CropShape.RECTANGLE);
        showImage.setScaleType(CropImageView.ScaleType.FIT_CENTER);

        galery.setBackground(hideGalery ? getResources().getDrawable(R.drawable.gallery_hover_disabled) : getResources().getDrawable(R.drawable.custom_gallery));
        galery.setEnabled(hideGalery ? false : true);
        //galery.setVisibility(hideGalery ? View.INVISIBLE : View.VISIBLE);

        pickImageChooserIntent = new PickImageChooserIntent(this);
        utilsCamera.initCallBack(this);
        if (!permissionMarshmellow.checkPermissionForCamera()) {
            permissionMarshmellow.requestPermissionForCamera();
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        camera = utilsCamera.getCameraInstance();
        setFlash();
        if (camera != null) {
            camera.setDisplayOrientation(90);
            cameraPreviewer = new CameraPreview(this, camera, this);
            cameraPreview.addView(cameraPreviewer);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == PermissionMarshmellow.CAMERA_PERMISSION_REQUEST_CODE)
                openCamera();
            else if (requestCode == PermissionMarshmellow.WRITE_PERMISSION_REQUEST_CODE)
                createImage();
        }
    }

    private void setFlash() {
        try {
            Camera.Parameters p = camera.getParameters();
            p.setFlashMode(flashOn ? Camera.Parameters.FLASH_MODE_ON : Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Click
    protected void capture() {
        if (camera.getParameters().getFocusMode().equals(Camera.Parameters.FLASH_MODE_AUTO)) {
            cameraClick = true;
            camera.autoFocus(this);
        } else
            takePicture();

    }

    private void takePicture() {
        camera.takePicture(null, null, utilsCamera.picture);
    }


    @Click
    protected void close() {
        camera.release();
        recreate();
    }

    @Click
    protected void exit() {
        camera.release();
        finish();
    }

    @Click
    protected void galery() {
        startActivityForResult(pickImageChooserIntent.resultPickImageChooser(), ACTION_LOAD_IMAGE);
    }

    @CheckedChange
    protected void flash(CompoundButton compoundButton, boolean check) {
        flashOn = check;
        setFlash();
    }

    @Touch(R.id.camera_preview)
    protected boolean cameraPreview(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            focusOnTouch(event);
        return true;
    }

    @Click
    protected void crop() {
        final Bitmap bitmap = showImage.getCroppedImage();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                cropped = Bitmap.createScaledBitmap(bitmap, SIZE_W, SIZE_H, true);
                if (!permissionMarshmellow.checkPermissionForWrite())
                    permissionMarshmellow.requestPermissionForWrite();
                else
                    createImage();

            }
        });
    }

    private void createImage() {
        new CreateImage(cropped, CameraCustom.this, CameraCustom.this).execute();
    }

    private void result(String path) {
        Intent intent = new Intent();
        intent.putExtra(UtilsCamera.resultTagCamera, path);
        intent.putExtra(positionImage, position);
        try {
            setResult(RESULT_OK, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        camera.release();
        onBackPressed();
    }

    @Override
    public void image(Bitmap bitmap) {
        imageViewing.setVisibility(View.VISIBLE);
        cameraView.setVisibility(View.GONE);
        showImage.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = pickImageChooserIntent.getPickImageResultUri(data);
            imageViewing.setVisibility(View.VISIBLE);
            cameraView.setVisibility(View.GONE);
            showImage.setImageUriAsync(imageUri);
        }
    }

    private Camera.Size getBestPreviewSize(int width, int height,
                                           Camera.Parameters parameters) {
        Camera.Size result = null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;

                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }

        return (result);
    }

    @Override
    public void absolutPath(String path) {
        result(path);
    }

    @Override
    public void onBackPressed() {
        if (cameraPreview.getVisibility() == View.GONE)
            close();
        else
            exit();
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success && cameraClick) {
            takePicture();
            cameraClick = false;
        }

    }

    private void focusOnTouch(MotionEvent event) {
        if (camera != null) {

            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getMaxNumMeteringAreas() > 0) {
                Rect rect = calculateFocusArea(event.getX(), event.getY());

                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
                meteringAreas.add(new Camera.Area(rect, 800));
                parameters.setFocusAreas(meteringAreas);

                camera.setParameters(parameters);
                camera.autoFocus(this);
            } else {
                camera.autoFocus(this);
            }
        }
    }

    private Rect calculateFocusArea(float x, float y) {
        int left = clamp(Float.valueOf((x / cameraPreview.getWidth()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
        int top = clamp(Float.valueOf((y / cameraPreview.getHeight()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);

        return new Rect(left, top, left + FOCUS_AREA_SIZE, top + FOCUS_AREA_SIZE);
    }

    private int clamp(int touchCoordinateInCameraReper, int focusAreaSize) {
        int result;
        if (Math.abs(touchCoordinateInCameraReper) + focusAreaSize / 2 > 1000) {
            if (touchCoordinateInCameraReper > 0) {
                result = 1000 - focusAreaSize / 2;
            } else {
                result = -1000 + focusAreaSize / 2;
            }
        } else {
            result = touchCoordinateInCameraReper - focusAreaSize / 2;
        }
        return result;
    }


}
