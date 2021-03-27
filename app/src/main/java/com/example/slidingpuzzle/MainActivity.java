package com.example.slidingpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 퍼즐 초기값은 3*3
    public int num = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        // 기본 3*3 설정
        GridView gridView = (GridView) findViewById(R.id.GridView);
        PuzzleAdapter adapter = new PuzzleAdapter(this, image, num);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(num);


        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.shuffle);


        // 버튼 클릭하면 나눌 숫자 변경
        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                num = 3;
                GridView gridView = (GridView) findViewById(R.id.GridView);
                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);

            }
        });

        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                num = 4;
                GridView gridView = (GridView) findViewById(R.id.GridView);
                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);
            }
        });
    }
}



