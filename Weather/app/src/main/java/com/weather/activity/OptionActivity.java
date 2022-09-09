package com.weather.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.weather.R;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout cityManageLL;
    private LinearLayout warnManageLL;
    private ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        cityManageLL = (LinearLayout)findViewById(R.id.cityManageLL);
        warnManageLL = (LinearLayout)findViewById(R.id.warnManageLL);
        backImage = (ImageView)findViewById(R.id.backImage);

        cityManageLL.setOnClickListener(this);
        warnManageLL.setOnClickListener(this);
        backImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cityManageLL:
                startActivityForResult(new Intent(this, CityManageActivity.class), 1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            case R.id.warnManageLL:
                break;
            case R.id.backImage:
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode ==1) {//resultcode 返回值为1，直接后台关闭
            setResult(1,new Intent());
            finish();
        }
    }
}
