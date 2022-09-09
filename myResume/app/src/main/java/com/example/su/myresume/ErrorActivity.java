package com.example.su.myresume;

/**
 * Created by su on 2016/12/10.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;


public class ErrorActivity extends Activity {
    private ImageButton imageButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        imageButton =(ImageButton)findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener(){
            Intent intent = new Intent();
            boolean networkState = NetworkDetector.detect(ErrorActivity.this);
            public void onClick(View v) {
                if (networkState) {
                    intent.setClass(ErrorActivity.this, MainActivity.class);
                    ErrorActivity.this.startActivity(intent);
                    finish();
                }
                else{
                    intent.setClass(ErrorActivity.this, MainActivity.class);
                    ErrorActivity.this.startActivity(intent);
                    finish();
                }
            }
        });

    }
}
