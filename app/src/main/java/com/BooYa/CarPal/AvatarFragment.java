package com.BooYa.CarPal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rony on 13/11/2014.
 */
public class AvatarFragment extends Fragment {
    protected static final String ARG_KEY = "key";
    private static final String NEW_IMAGE_URI = "new_image_uri";
    private static final int GALLERY_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private Page mPage;

    private ImageView imageView;

    private Uri mNewImageUri;

    public static AvatarFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        AvatarFragment f = new AvatarFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = mCallbacks.onGetPage(mKey);

        if (savedInstanceState != null) {
            String uriString = savedInstanceState.getString(NEW_IMAGE_URI);
            if (!TextUtils.isEmpty(uriString)) {
                mNewImageUri = Uri.parse(uriString);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNewImageUri != null) {
            outState.putString(NEW_IMAGE_URI, mNewImageUri.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment_page_image,
                container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage
                .getTitle());

        imageView = (ImageView) rootView.findViewById(R.id.profile_image_view);

        String imageData = mPage.getData().getString(Page.SIMPLE_DATA_KEY);
        if (!TextUtils.isEmpty(imageData)) {
            Uri imageUri = Uri.parse(imageData);
            imageView.setImageURI(imageUri);
        } else {
            imageView.setImageResource(R.drawable.avatar);
        }

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment pickPhotoSourceDialog = new DialogFragment() {
                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                getActivity());
                        builder.setItems(com.tech.freak.wizardpager.R.array.image_photo_sources,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (which) {
                                            case 0:
                                                // Gallery
                                                Intent photoPickerIntent = new Intent(
                                                        Intent.ACTION_PICK,
                                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                getActivity().startActivityForResult(
                                                        photoPickerIntent,
                                                        GALLERY_REQUEST_CODE);
                                                break;

                                            default:
                                                // Camera
                                                mNewImageUri = getActivity()
                                                        .getContentResolver()
                                                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                                new ContentValues());
                                                Intent photoFromCamera = new Intent(
                                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                                photoFromCamera.putExtra(
                                                        MediaStore.EXTRA_OUTPUT,
                                                        mNewImageUri);
                                                photoFromCamera
                                                        .putExtra(
                                                                MediaStore.EXTRA_VIDEO_QUALITY,
                                                                0);
                                                getActivity().startActivityForResult(
                                                        photoFromCamera,
                                                        CAMERA_REQUEST_CODE);
                                                break;
                                        }

                                    }
                                });
                        return builder.create();
                    }
                };

                pickPhotoSourceDialog.show(getFragmentManager(),
                        "pickPhotoSourceDialog");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException(
                    "Activity must implement PageFragmentCallbacks");
        }

        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    imageView.setImageURI(mNewImageUri);
                    writeResult();
                }
                break;
            case GALLERY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    mNewImageUri = data.getData();
                    //imageView.setImageURI(mNewImageUri);
                    setPic(getRealPathFromURI(getActivity(), mNewImageUri), imageView);
                    writeResult();
                }
                break;
        }
    }

    private void writeResult() {
        mPage.getData().putString(Page.SIMPLE_DATA_KEY,
                (mNewImageUri != null) ? mNewImageUri.toString() : null);
        mPage.notifyDataChanged();

    }

    private void setPic(String imagePath, ImageView destination) {
        int targetW = destination.getWidth();
        int targetH = destination.getHeight();
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    break;
                default:
                    break;
            }
            destination.setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
