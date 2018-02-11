package com.asus.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    public PostService _postService = null;
    public MainViewModel _mainViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setUIUpdate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        retriveData();
    }

    private void init() {
        _postService = new PostService();
        _mainViewModel = new MainViewModel(_postService);
    }

    private void retriveData(){
        _mainViewModel.getPost("1");
    }

    private void setUIUpdate(){
        _mainViewModel.postRxProp.observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Post>() {
                @Override
                public void accept(Post post) throws Exception {
                    //post 資料有異動，更新UI
                    Toast.makeText(MainActivity.this, new Gson().toJson(post), Toast.LENGTH_SHORT).show();
                }
            });
    }
}
