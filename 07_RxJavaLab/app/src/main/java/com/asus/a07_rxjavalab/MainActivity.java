package com.asus.a07_rxjavalab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observableLab();

        //Lab 2: 利用 RxJava 將耗時的工作包進 Observable，下游透過訂閱取得執行結果。
//        getAQIData().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG, "onNext: " + s);
//                TextView tvMsg = findViewById(R.id.tvMsg);
//                tvMsg.setText(s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        //Lab 3: 先呼叫登入API，登入成功後，再呼叫getDept API取得部門資料
//        login().flatMap(new Function<LoginResponse, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(LoginResponse loginResponse) throws Exception {
//                return getDeptData("A00001");
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d(TAG, "accept: " + s);
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });

        zipLab();
        //flowableLab();
    }

    private void zipLab() {
        final String FUN_TAG = "ZipLab";

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(FUN_TAG, "emit 1");
                emitter.onNext(1);
                Log.d(FUN_TAG, "emit 2");
                emitter.onNext(2);
                Log.d(FUN_TAG, "emit 3");
                emitter.onNext(3);
                Log.d(FUN_TAG, "emit 4");
                emitter.onNext(4);
                Log.d(FUN_TAG, "emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(FUN_TAG, "emit A");
                emitter.onNext("A");
                Log.d(FUN_TAG, "emit B");
                emitter.onNext("B");
                Log.d(FUN_TAG, "emit C");
                emitter.onNext("C");
                Log.d(FUN_TAG, "emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());;

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(FUN_TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(FUN_TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(FUN_TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(FUN_TAG, "onComplete");
            }
        });
    }
    private void observableLab() {
        final String tag = "ObservableLab";

        //建立一個上游，被觀察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        //建立一個下游，觀察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(tag, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(tag, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "error");
            }

            @Override
            public void onComplete() {
                Log.d(tag, "complete");
            }
        };

        //建立連結，訂閱
        observable.subscribe(observer);
    }

    private Observable<LoginResponse> login() {
        return Observable.create(new ObservableOnSubscribe<LoginResponse>() {
            @Override
            public void subscribe(ObservableEmitter<LoginResponse> emitter) throws Exception {
                //Call 登入API，
                boolean isLoginSuccess = true;
                if (isLoginSuccess){
                    emitter.onNext(new LoginResponse());
                    emitter.onComplete();
                }else{
                    emitter.onError(new Exception());
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<String> getDeptData(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //Call Get Detp API
                Log.d("GetDeptData", "id: " + id);
                //.....
                emitter.onNext("電腦中心");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    private Observable<String> getAQIData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String aqiData = getData("https://quality.data.gov.tw/dq_download_json.php?nid=40448&md5_url=70703ad79148849bc3c043a5106fd847");
                emitter.onNext(aqiData);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }



    private void flowableLab() {
        //被觀察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("test1");
                e.onNext("test2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //建立觀察者
        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.i("flowableLab", "onSubscribe");
                //設定拋出多少事件，此表示不限制
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                //onNext事件處理
                Log.i("flowableLab", s);
            }
            @Override
            public void onError(Throwable t) {
                //onError事件處理
                Log.i("flowableLab", "onError");
            }
            @Override
            public void onComplete() {
                //onComplete事件處理
                Log.i("flowableLab", "onComplete");
            }
        };

        flowable.subscribe(subscriber);


        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                //相當於onNext事件處理
                Log.i("flowableLab", s);
            }
        });

        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                //相當於onNext事件處理
                Log.i("flowableLab", s);
            }
        }, new Consumer<Throwable>() {
            //相當於oonError
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("flowableLab", "onError");
            }
        }, new Action() {
            //相當於oonComplete
            @Override
            public void run() throws Exception {
                Log.i("flowableLab", "onComplete");
            }
        });

    }

    private String getData(String urlStr) {
        URL url;
        HttpURLConnection connection;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(60 * 1000);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
