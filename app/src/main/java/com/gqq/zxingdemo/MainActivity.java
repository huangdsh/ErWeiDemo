package com.gqq.zxingdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ivCreate)
    ImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnCreate, R.id.btnScan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreate:

                // 生成
                Bitmap bitmap = encodeAsBitmap("123456");
                mView.setImageBitmap(bitmap);

                break;
            case R.id.btnScan:

                // 扫描

                break;
        }
    }

    public Bitmap encodeAsBitmap(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 200, 0, 0, w, h);

        return bitmap;

    }
}
