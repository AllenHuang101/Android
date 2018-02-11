package com.asus.a07_rxjavalab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.asus.a07_rxjavalab.Model.Post;
import com.asus.a07_rxjavalab.Model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {
    public static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //巢狀連線
        rxNestCallApi();
        //合併連線
        rxConcurrentApiCall();
    }

    //巢狀連線
    //先呼叫api取得post資料，再呼叫api取得User資料
    private void rxNestCallApi() {
        getPostObserable("1").flatMap(new Function<Post, SingleSource<User>>() {
            @Override
            public SingleSource<User> apply(Post post) throws Exception {
                String userId = post.getUserId();
                return getUserObserable(userId);
            }
        }).observeOn(AndroidSchedulers.mainThread()) //設定observer在主執行緒
         .subscribe(new Consumer<User>() {
            @Override
            public void accept(User s) throws Exception {
                Log.d(TAG, "accept: " + new Gson().toJson(s));
                Toast.makeText(SecondActivity.this, new Gson().toJson(s), Toast.LENGTH_SHORT).show();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //Error Handle
            }
        });
    }

    //合併連線
    //合併多個Observable，全部執行完時才呼叫callback
    private void rxConcurrentApiCall() {
        Single.zip(getPostObserable("1"), getUserObserable("1"), new BiFunction<Post, User, Pair<Post, User>>() {
            @Override
            public Pair<Post, User> apply(Post post, User user) throws Exception {
                //合併 Post & User 結果並加工處理。
                return Pair.create(post, user);
            }
        }).observeOn(AndroidSchedulers.mainThread()) //設定observer在主執行緒
        .subscribe(new Consumer<Pair<Post, User>>(){
            @Override
            public void accept(Pair<Post, User> s) throws Exception {
                // 全部 API 執行完，才會呼叫Callback
                String post = new Gson().toJson(s.first);
                String user = new Gson().toJson(s.second);
                Log.d(TAG, "User:" + post);
                Log.d(TAG, "Post:" + user);
                Toast.makeText(SecondActivity.this, post, Toast.LENGTH_SHORT).show();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //Error Handle
            }
        });
    }

    private Post getPost(String id) {
        String url = String.format("https://jsonplaceholder.typicode.com/posts/%s", id);
        Post post;
        String data = getData(url);

        post = new Gson().fromJson(data.toString(), Post.class);
        return post;
    }

    private User getUser(String id) {
        String url = String.format("https://jsonplaceholder.typicode.com/users/%s", id);
        User user;
        String data = getData(url);

        user = new Gson().fromJson(data.toString(), User.class);
        return user;
    }

    private Single<Post> getPostObserable(final String id) {
        return Single.fromCallable(new Callable<Post>() {
            @Override
            public Post call() throws Exception {
                Post post = getPost(id);
                return post;
            }
        }).subscribeOn(Schedulers.io());
    }

    private Single<User> getUserObserable(final String id) {
        return Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                User user = getUser(id);
                return user;
            }
        }).subscribeOn(Schedulers.io());
    }

    private String getData(String urlStr) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setReadTimeout(60 * 1000);
            connection.connect();

            int reponse = connection.getResponseCode();

            if (reponse == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                reader.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }
}
