package com.example.upload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.service.media.MediaBrowserService;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int select = 100;
    ImageView image;
    Button pick, convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.ivpic);
        convert = (Button)findViewById(R.id.btnconvert);
        pick = (Button)findViewById(R.id.btnpick);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, select);
            }
        });
    }

protected void OnActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == select){
            if (resultCode == RESULT_OK){
                Uri select =data.getData();
                InputStream inputStream = null;

                try{
                    assert select != null;
                    inputStream = getContentResolver().openInputStream(select);

                }catch (Exception e){
                    e.printStackTrace();
                }
                BitmapFactory.decodeStream(inputStream);
                image.setImageURI(select);
            }
        }
}

}