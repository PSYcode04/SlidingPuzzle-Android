package com.example.slidingpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public int num = 3; // 퍼즐 초기값은 3*3
    protected Bitmap[] oriPuzzlePiece; // 이미지 비트맵 배열
    protected PuzzlePiece[] puzzlePiece; // 퍼즐 정답 형태 배열
    protected PuzzlePiece[] shufflePiece; // shuffle했을 때 배열
    private int[] puzzleOrder;
    int[][] board; // 3*3, 4*4 형태 위치를 찾기 위한 보드판
    int loc = 0;
    int x = 0, y = 0; // 보드판에서 좌표
    private boolean answer = false; // 정답 체크
    private boolean click = false; // shuffle버튼 눌렀는지 체크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        cutImage(image);


        // 초기값 3*3
        puzzlePiece = new PuzzlePiece[num*num];
        shufflePiece = new PuzzlePiece[num*num];

        board = new int[num][num];
        for(int i = 0; i < num; i++) {
            for(int j = 0; j < num; j++, loc++)
                board[i][j] = loc;
        }


        for(int i = 0; i < num*num; i++) {
            puzzlePiece[i] = new PuzzlePiece(oriPuzzlePiece[i], i);
        }

        GridView gridView = (GridView) findViewById(R.id.GridView);
        PuzzleAdapter adapter = new PuzzleAdapter(this, image, num, puzzlePiece);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(num);


        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.shuffle);


        // 버튼 클릭하면 나눌 숫자 변경
        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                num = 3;
                loc = 0;
                click = false;
                cutImage(image);
                puzzlePiece = new PuzzlePiece[num*num];
                shufflePiece = new PuzzlePiece[num*num];

                for(int i = 0; i < num*num; i++) {
                    puzzlePiece[i] = new PuzzlePiece(oriPuzzlePiece[i], i);
                }

                board = new int[num][num];
                for(int i = 0; i < num; i++) {
                    for(int j = 0; j < num; j++, loc++)
                        board[i][j] = loc;
                }

                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, puzzlePiece);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);
            }
        });

        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                num = 4;
                loc = 0;
                click = false;
                cutImage(image);
                puzzlePiece = new PuzzlePiece[num*num];
                shufflePiece = new PuzzlePiece[num*num];

                for(int i = 0; i < num*num; i++) {
                    puzzlePiece[i] = new PuzzlePiece(oriPuzzlePiece[i], i);
                }

                board = new int[num][num];
                for(int i = 0; i < num; i++) {
                    for(int j = 0; j < num; j++, loc++)
                        board[i][j] = loc;
                }

                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, puzzlePiece);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);
            }
        });

        btn3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                click = true; // shuffle을 눌렀음을 표시

                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, puzzlePiece);
                Collections.shuffle(Arrays.asList(adapter.newPuzzlePiece));
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);

                // shuffle 눌렀을 때의 퍼즐 순서 저장
                for(int i = 0; i < adapter.newPuzzlePiece.length; i++) {
                    shufflePiece[i] = new PuzzlePiece(adapter.newPuzzlePiece[i].getImagePiece(), adapter.newPuzzlePiece[i].getIndex());
                }
            }
        });


        // 퍼즐을 클릭했을 때의 동작
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int clickLocation; // 클릭한 퍼즐 위치
            int blank; // 빈칸 위치

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickLocation = position;
                blank = shufflePiece.length - 1;

                // shuffle버튼을 누르고, 정답이 아닐 경우에만 클릭 가능
                if(click && !answer) {
                    // 클릭한 퍼즐 좌표 찾기
                    for(int h = 0; h < num; h++) {
                        for(int w = 0; w < num; w++){
                            if(board[h][w] == clickLocation) {
                                y = h;
                                x = w;
                                break;
                            }
                        }
                    }

                    // 퍼즐 초기값은 3*3
                    if(y - 1 >= 0 && shufflePiece[board[y-1][x]].getIndex() == blank){
                        swapPiece(clickLocation, board[y-1][x]);
                        checkAnswer();
                    }
                    // 아래쪽이 빈칸인 경우
                    else if(y + 1 < num  && shufflePiece[board[y+1][x]].getIndex() == blank) {
                        swapPiece(clickLocation, board[y+1][x]);
                        checkAnswer();
                    }
                    // 오른쪽이 빈칸인 경우
                    else if(x + 1 < num && shufflePiece[board[y][x+1]].getIndex() == blank){
                        swapPiece(clickLocation, board[y][x+1]);
                        checkAnswer();
                    }
                    // 왼쪽이 빈칸인 경우
                    else if(x - 1 >= 0 && shufflePiece[board[y][x-1]].getIndex() == blank){
                        swapPiece(clickLocation, board[y][x-1]);
                        checkAnswer();
                    }
                    // 주변에 빈칸이 없는 공간을 클릭했을 경우는 동작 안함

                    // 클릭한 후, 이동한 퍼즐을 화면에 표시
                    PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, shufflePiece);
                    gridView.setAdapter(adapter);
                    gridView.setNumColumns(num);

                    // 정답이라면 Toast메시지 출력후, 더 이상 클릭 불가!
                    if(answer)
                        Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // 이미지 비트맵 분할
    public void cutImage(Bitmap image) {
        int x, y;
        int i = 0;
        oriPuzzlePiece = new Bitmap[num * num];
        System.out.println(oriPuzzlePiece.length);

        for (y = 0; y <= (image.getHeight() - image.getHeight() / num); y += image.getHeight() / num) {
            for (x = 0; x <= (image.getWidth() - image.getWidth() / num); x += image.getWidth() / num, i++) {
                if(i == num*num-1)
                    break;
                oriPuzzlePiece[i] = Bitmap.createBitmap(image, x, y, image.getWidth()/ num, image.getHeight() / num);
            }
        }
    }

    // 정답인지 체크
    private void checkAnswer() {

        for(int i = 0; i < num*num; i++) {
            if(shufflePiece[i].getIndex() == i)
                continue;
            else {
                answer = false;
                return;
            }
        }
        answer = true;
    }

    // 퍼즐 위치 swap
    public void swapPiece(int loc1, int loc2){
        PuzzlePiece tmp1 = shufflePiece[loc1]; // 클릭한 위치
        PuzzlePiece tmp2 = shufflePiece[loc2]; // 변경할 위치?

        System.out.println("tmp1: " + tmp1.getIndex());
        System.out.println("tmp2: " + tmp2.getIndex());

        shufflePiece[loc1] = tmp2;
        shufflePiece[loc2] = tmp1;
    }
}