package com.asus.a05_handlerthreadlab;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//reference: http://j796160836.pixnet.net/blog/post/28766165-%5Bandroid%5D-%E5%A4%9A%E5%9F%B7%E8%A1%8C%E7%B7%92-handler%E5%92%8Cthread%E7%9A%84%E9%97%9C%E4%BF%82

public class MainActivity extends AppCompatActivity {


    private Handler mHandler;
    private HandlerThread mhandlerThread;

    //透過他指派任務給 Main Thread 執行 UI 更新
    //Main Thread 本身也是個 HandlerThread
    private Handler mUIHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 特殊的 Thread，會一直 Standby，只要 Handler 給他任務他就會去執行
        mhandlerThread = new HandlerThread("HandlerThread");
        mhandlerThread.start();

        mHandler = new Handler(mhandlerThread.getLooper());

        // 指派任務給 HandlerThread 執行
        mHandler.post(new Runnable(){
            @Override
            public void run(){
                // 執行繁重的任務
                SystemClock.sleep(5000);
                mUIHandler.post(updateUI);
            }
        });
    }

    @Override

    protected void onDestroy() {

        super.onDestroy();

        mHandler = null;

        if (mhandlerThread != null) {
            mhandlerThread.quit();
            mhandlerThread = null;
        }

    }

    private Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            TextView tvMsg = findViewById(R.id.tvMsg);
            tvMsg.setText("繁重工作執行完成!!!");
        }
    };
}
