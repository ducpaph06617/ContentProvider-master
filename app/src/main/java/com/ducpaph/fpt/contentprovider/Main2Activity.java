package com.ducpaph.fpt.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<model> strings;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Button btload;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recycler_view);
        strings=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        btload = (Button) findViewById(R.id.btload);
        isPermissionGranted();


        btload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaStore();
            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            ImageView imageView = (ImageView) findViewById(R.id.imgContacts);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//
//        }


    //    }
    private void mediaStore() {

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] pro = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns._ID,
                MediaStore.Images.Media.DATA
        };
        Cursor cursor = getContentResolver().query(uri, pro, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String s = "";

            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(0);
                String id = cursor.getString(1);
                String urimg = cursor.getString(2);
                strings.add(new model(urimg, name, id));
                cursor.moveToNext();

            }
            cursor.close();

            adapter = new Adapter(this, strings);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }


    }


    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("TAG", "Permission is granted 11111");
                return true;
            } else {

                Log.e("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else {
            Log.e("TAG", "Permission is granted");
            return true;
        }
    }
}


