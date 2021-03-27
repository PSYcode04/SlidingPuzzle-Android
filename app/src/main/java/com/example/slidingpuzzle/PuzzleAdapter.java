package com.example.slidingpuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

public class PuzzleAdapter extends BaseAdapter {

    protected Bitmap[] gridImage; // 비트맵으로 자른 이미지 넣을 배열?
    private Context context; // 어플리케이션에 대한 정보를 담는 Context 객체
    private Bitmap image;   // 전달받을 bitmap 이미지
    private int num; // 3*3인지 4*4인지 전달받는다.
    private int gridSize;

    public PuzzleAdapter(Context c, Bitmap image, int num) {
        context = c;
        this.image = image;
        this.num = num;
        gridSize = image.getWidth() / num;
        gridImage = new Bitmap[num * num-1]; // 한 칸은 빈칸으로 만들기 위해 1을 빼준다.

        cutImage();
    }

    @Override
    public int getCount() {
        return gridImage.length;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 이미지 버튼으로 출력
        ImageButton imageButton;

        if(convertView == null){
            imageButton = new ImageButton(context);
            imageButton.setLayoutParams(new GridView.LayoutParams(gridSize, gridSize));
            imageButton.setPadding(0, 0, 0, 0);

        }else{
            imageButton  = (ImageButton) convertView;
        }
        //이미지버튼에 주어진 위치의 이미지를 설정함
        imageButton.setImageBitmap(gridImage[position]);
        imageButton.setId(position);

        return imageButton;
    }

    // 이미지 쪼개서 배열에 넣기
    public void cutImage() {
        int x, y;
        int i = 0;

        for (y = 0; y <= (image.getHeight() - image.getHeight() / num); y += image.getHeight() / num) {
            for (x = 0; x <= (image.getWidth() - image.getWidth() / num); x += image.getWidth() / num, i++) {
                if(i == num*num-1)
                    break;
                gridImage[i] = Bitmap.createBitmap(image, x, y, image.getWidth()/ num, image.getHeight() / num);
            }
        }
    }



}
