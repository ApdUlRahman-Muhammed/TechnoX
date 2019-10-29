package com.coder.technox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class AndroidActivity extends AppCompatActivity {
  //  private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   mCheckBox=findViewById(R.id.alone);
     //   boolean hasWhipedCreem=mCheckBox.isChecked();

      // Log.e("AndroidActivity", "isChecked " , hasWhipedCreem);
   //     mCheckBox.isChecked();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("tel:01022342098")));

                Snackbar.make(view, "make a call developer ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}

