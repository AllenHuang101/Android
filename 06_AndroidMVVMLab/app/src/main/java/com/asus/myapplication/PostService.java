package com.asus.myapplication;

import com.google.gson.Gson;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Allen on 2018/2/11.
 */

public class PostService {

    public PostService() {

    }
    public Single<Post> getPostObserable(final String id) {
        return Single.fromCallable(new Callable<Post>() {
            @Override
            public Post call() throws Exception {
                String url = String.format("https://jsonplaceholder.typicode.com/posts/%s", id);
                String data = ApiUtil.getData(url);
                Post post = new Gson().fromJson(data.toString(), Post.class);
                return post;
            }
        }).subscribeOn(Schedulers.io());
    }
}
