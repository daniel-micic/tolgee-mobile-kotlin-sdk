package io.tolgee.demo.exampleandroid;

import android.content.Context;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import io.tolgee.TolgeeAndroid;
import io.tolgee.Tolgee;
import io.tolgee.TolgeeContextWrapper;

import java.util.Locale;

public class MainJavaActivity extends ComponentActivity implements Tolgee.ChangeListener {

    private final TolgeeAndroid tolgee = Tolgee.getInstance();

    @Override
    protected void attachBaseContext(Context newBase) {
        // Wrapping base context will make sure getString calls will use tolgee
        // even for instances which cannot be replaced automatically by the compiler
        super.attachBaseContext(TolgeeContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tolgee.addChangeListener(this);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Make sure the translations are loaded
        // This function will initiate translations fetching in the background and
        // will trigger changeFlow whenever updated translations are available
        tolgee.preload(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tolgee.removeChangeListener(this);
    }

    @Override
    public void onTranslationsChanged() {
        // we want to reload activity after a language change
        recreate();
    }
}