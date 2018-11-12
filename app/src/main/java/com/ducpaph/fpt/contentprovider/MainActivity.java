package com.ducpaph.fpt.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvContacts;
    private Button btload,btload2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContacts = findViewById(R.id.tvContacts);
        btload = findViewById(R.id.btload);
        btload2 = findViewById(R.id.btload2);
        btload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        isPermissionGranted();
        btload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadContacts();
            }
        });
    }

    public void loadContacts() {



        List<String> contacts = new ArrayList<>();


        Cursor c1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (c1 != null && c1.getCount() > 0) {
            c1.moveToFirst();
            while (c1.isAfterLast() == false) {
                String id = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));

                String name = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                contacts.add(id + "-" + name);

                c1.moveToNext();
            }
            c1.close();
            String text = "";
            for (int i = 0; i < contacts.size(); i++) {
                text = text + "\n" + contacts.get(i);
            }
            tvContacts.setText(text);
        }
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("TAG", "Permission is granted 11111");
                return true;
            } else {

                Log.e("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 2);
                return false;
            }
        } else {
            Log.e("TAG", "Permission is granted");
            return true;
        }
    }
}
