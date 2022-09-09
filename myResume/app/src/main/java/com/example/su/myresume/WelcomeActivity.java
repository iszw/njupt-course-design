package com.example.su.myresume;

/**
 * Created by su on 2016/12/10.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
public class WelcomeActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                boolean networkState = NetworkDetector.detect(WelcomeActivity.this);
                if (networkState) {
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    finish();
                } else {
                    intent.setClass(WelcomeActivity.this, ErrorActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    finish();
                }

            }
        }, 1000);
    }
}
