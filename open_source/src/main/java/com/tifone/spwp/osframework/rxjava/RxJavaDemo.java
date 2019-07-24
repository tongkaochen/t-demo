package com.tifone.spwp.osframework.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tifone.spwp.common.tab.log.LogUtil.logd;

public class RxJavaDemo {

    public void demo1() {
        // base demo
        // use observer to subscribe observable
        // observable is the event list
        Observable<String> observable = Observable.create(
                new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Event1");
                emitter.onNext("Event2");
                emitter.onNext("Event3");
                emitter.onNext("Event4");
                emitter.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logd("onSubscribe");
            }

            @Override
            public void onNext(String str) {
                logd(str);
            }

            @Override
            public void onError(Throwable e) {
                logd(e.getMessage());
            }

            @Override
            public void onComplete() {
                logd("onComplete");
            }
        };
        observable.subscribe(observer);

        Observable.just(1, 2, 3, 5, 9)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        logd("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        logd(integer+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
