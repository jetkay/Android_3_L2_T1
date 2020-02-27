package com.mygeekbrains.android_3_l2_t1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.mygeekbrains.android_3_l2_t1.R;
import com.mygeekbrains.android_3_l2_t1.presenter.EchoTextPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

public class EchoTextActivity extends AppCompatActivity implements IEchoText {

    private EchoTextPresenter presenter = new EchoTextPresenter(this);
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // nothing
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                presenter.setModelValue(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // nothing
//            }
//        });

        Observable<CharSequence> objectObservable = Observable.create(this::subscribe);
        objectObservable.subscribe(charSequence -> textView.setText(charSequence));
    }

    public void updateTextView(CharSequence charSequence) {
        textView.setText(charSequence);
    }

    private void subscribe(final ObservableEmitter<CharSequence> emitter) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!emitter.isDisposed()) {
                    emitter.onNext(editable.toString());
                }
            }
        };

        emitter.setCancellable(() -> editText.removeTextChangedListener(watcher));
        editText.addTextChangedListener(watcher);
    }
}
