package com.example.handler_imgchange;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by Wakesy on 2016/4/7.
 */
public class SecondMainactivity extends Activity{
    private TextView textView;



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("UI--->"+Thread.currentThread());
        }
    };

    class MyThread extends  Thread {
        public Handler handler1;
        public void run() {
            Looper.prepare();
           handler1=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("subThread---->"+Thread.currentThread());
                    super.handleMessage(msg);
                }
            };
            Looper.loop();

        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView=new TextView(this);
        textView.setText("hello handler");
        setContentView(textView);
        MyThread thread=new MyThread();
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.handler1.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);

    }
}
