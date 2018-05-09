package com.work.nalandya.pawoon_test.presenter.utils.camera;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;


import com.work.nalandya.pawoon_test.presenter.callback.CallBackCameraUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;


@EBean
public class UtilsCamera {
//    public static final int MEDIA_TYPE_IMAGE = 1;

    @RootContext
    Context context;

    public static String resultTagCamera = "camera";
    private Camera camera = null;
    private CallBackCameraUtils callBackCameraUtils;

    public void initCallBack(CallBackCameraUtils callBackCameraUtils) {
        this.callBackCameraUtils = callBackCameraUtils;
    }

    public Camera getCameraInstance() {
        try {
//            releaseCameraAndPreview();
            camera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return camera;
    }

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Camera.PictureCallback picture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            camera.release();
            callBackCameraUtils.image(rotatedBitmap);
/*
            CacheImage cacheImage = new CacheImage(rotatedBitmap);
            cacheImage.setCallBackCacheImage(UtilsCamera.this);
            cacheImage.execute();
*/

        }
    };


}
