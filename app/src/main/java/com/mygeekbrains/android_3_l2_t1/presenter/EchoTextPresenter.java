package com.mygeekbrains.android_3_l2_t1.presenter;

import com.mygeekbrains.android_3_l2_t1.model.EchoTextModel;
import com.mygeekbrains.android_3_l2_t1.view.IEchoText;

import io.reactivex.disposables.CompositeDisposable;

public class EchoTextPresenter {

    private IEchoText view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private EchoTextModel<CharSequence> model;

    public EchoTextPresenter(IEchoText view) {
        this.view = view;
    }

    public void start() {
        this.model = new EchoTextModel<CharSequence>() {
            @Override
            public void onNext(CharSequence charSequence) {
                view.updateTextView(charSequence);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }

        };

        compositeDisposable.add(this.model);
    }

    public void finish() {
        compositeDisposable.clear();
    }

    public void setModelValue(CharSequence charSequence) {
        model.setValue(charSequence);
    }

}
