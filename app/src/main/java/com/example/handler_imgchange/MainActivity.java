package com.example.handler_imgchange;


import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    private ImageView img;
    private Button btn_change;
    String images[]=null;
    AssetManager assets=null;
    int currentImg=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        btn_change= (Button) findViewById(R.id.btn_change);


        try {
            assets=getAssets();//获取/Assets/目录下的所有文件
            images=assets.list("");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void click(View v){
        if(currentImg>=images.length){//如果发生数组越界
            currentImg=0;

        }
        //找到下一张图片
        while(!images[currentImg].endsWith(".png")&&!images[currentImg].endsWith(".gif")
                   &&!images[currentImg].endsWith(".jpg")){
            currentImg++;
            //如果已发生数组越界
            if(currentImg>=images.length){
                currentImg=0;
            }

        }
        InputStream assetfile=null;
        try {
            assetfile=assets.open(images[currentImg++]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapDrawable bitmapDrawable= (BitmapDrawable) img.getDrawable();

        if(bitmapDrawable!=null&&!bitmapDrawable.getBitmap().isRecycled()){
            bitmapDrawable.getBitmap().recycle();

        }
        img.setImageBitmap(BitmapFactory.decodeStream(assetfile));
    }

}
