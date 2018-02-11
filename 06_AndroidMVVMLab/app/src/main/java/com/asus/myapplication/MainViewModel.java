package com.asus.myapplication;

import io.reactivex.functions.Consumer;
import jp.keita.kagurazaka.rxproperty.RxProperty;

/**
 * Created by Allen on 2018/2/11.
 */

public class MainViewModel {
    public final RxProperty<Post> postRxProp = new RxProperty<Post>();
    public PostService _postService = new PostService();

    public MainViewModel(PostService postService){
        this._postService = postService;
    }

    public void getPost(String id){
        _postService.getPostObserable(id)
        .subscribe(new Consumer<Post>() {
            @Override
            public void accept(Post post) throws Exception {
                postRxProp.set(post);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
