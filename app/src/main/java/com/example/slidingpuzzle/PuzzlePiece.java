package com.example.slidingpuzzle;

import android.graphics.Bitmap;

public class PuzzlePiece {
    private Bitmap imagePiece;
    private int index;

    public PuzzlePiece(Bitmap imagePiece, int num) {
        this.imagePiece= imagePiece;
        this.index = num;
    }

    public Bitmap getImagePiece() {
        return imagePiece;
    }

    public void setImagePiece(Bitmap imagePiece) {
        this.imagePiece = imagePiece;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
