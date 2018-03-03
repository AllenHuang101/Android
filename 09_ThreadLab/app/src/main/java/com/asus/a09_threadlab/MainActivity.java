package com.asus.a09_threadlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.asus.a09_threadlab.ThreadManager.Priority;
import com.asus.a09_threadlab.ThreadManager.PriorityRunnable;
import com.asus.a09_threadlab.ThreadManager.PriorityThreadPoolManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ThreadPoolManager.getInstance()
//                .getThreadPoolExector().execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Tag", "run: ");
//            }
//        });


        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                Log.d("Task", "run: Task1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                Log.d("Task", "run: Task1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                Log.d("Task", "run: Task1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                Log.d("Task", "run: Task1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.HIGH) {
            @Override
            public void run() {
                Log.d("Task", "run: Task1...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                Log.d("TaskPriority", "run: Task1");
            }
        });

        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.HIGH) {
            @Override
            public void run() {
                Log.d("TaskPriority", "run: Task2");
            }
        });

        PriorityThreadPoolManager.getInstance().getThreadPoolExector()
                .submit(new PriorityRunnable(Priority.HIGH) {
            @Override
            public void run() {
                Log.d("TaskPriority", "run: Task3");
            }
        });

        PriorityThreadPoolManager.getInstance().getThreadPoolExector().submit(new PriorityRunnable(Priority.MEDIUM) {
            @Override
            public void run() {
                Log.d("TaskPriority", "run: Task4");
            }
        });
    }
}
