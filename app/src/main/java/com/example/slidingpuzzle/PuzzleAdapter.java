package com.example.slidingpuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PuzzleAdapter extends BaseAdapter {

    private Context context;
    private int gridSize;
    protected PuzzlePiece[] newPuzzlePiece;


    public PuzzleAdapter(Context c, Bitmap image, int num, PuzzlePiece[] puzzlePiece) {
        context = c;
        gridSize = image.getWidth() / num;
        newPuzzlePiece = new PuzzlePiece[num * num];
        newPuzzlePiece = puzzlePiece.clone();
    }

    @Override
    public int getCount() {
        return newPuzzlePiece.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(gridSize, gridSize));
            imageView.setPadding(0, 0, 0, 0);

        }else{
            imageView  = (ImageView) convertView;
        }

        imageView.setImageBitmap(newPuzzlePiece[position].getImagePiece());
        imageView.setId(newPuzzlePiece[position].getIndex());

        return imageView;
    }

}