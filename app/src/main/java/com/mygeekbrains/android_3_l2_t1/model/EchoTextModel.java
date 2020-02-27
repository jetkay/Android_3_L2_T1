package com.mygeekbrains.android_3_l2_t1.model;

import io.reactivex.observers.DisposableObserver;

public abstract class EchoTextModel<T extends CharSequence> extends DisposableObserver<CharSequence> {

    private T value;

    public void setValue(T value) {
        this.value = value;
        this.onNext(value);
    }

}
