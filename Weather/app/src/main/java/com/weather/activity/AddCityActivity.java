package com.weather.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weather.R;
import com.weather.utils.CharacterParser;
import com.weather.utils.SharedUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddCityActivity extends AppCompatActivity {

    private ImageView backImage;
    private ListView myAllCityList;

    private String[] letters = new String[]{
            "A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N",
            "O","P","Q","R","S","T",
            "U","V","W","X","Y","Z",
    };
    private List<String> lettersList = Arrays.asList(letters);

    private List<String> listCityNamesWithLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        //初始化获取城市列表 包括 英文首字母 由于列表数据比较大，在上一个界面获取后才可以打开这个界面并获取数据
        Intent intent = getIntent();
        listCityNamesWithLetters=intent.getStringArrayListExtra("allCityNameList");

        //初始化界面
        initView();
    }

    //初始化界面
    private void initView(){
        backImage = (ImageView)findViewById(R.id.backImage);
        backImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        myAllCityList = (ListView)findViewById(R.id.myAllCityList);
        CityNameAdapter adapter = new CityNameAdapter(listCityNamesWithLetters);
        myAllCityList.setAdapter(adapter);

        myAllCityList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!listCityNamesWithLetters.get(position).matches("[A-Z]")) {
                    saveCitysInfo(listCityNamesWithLetters.get(position));
                    setResult(1, new Intent());
                    finish();
                    startActivity(new Intent(AddCityActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            }
        });
    }

    private void saveCitysInfo(String cityName){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String citys = sp.getString("savedCityNames", null);
        if(citys == null){
            citys = cityName;
            editor.putString("savedCityNames",citys);
        }else{
            citys = citys + "##" +cityName;
            editor.putString("savedCityNames",citys);
        }
        editor.commit();
    }

    public class CityNameAdapter extends BaseAdapter{
        private List<String> cityList;

        private String isLetters = "1";
        private String isCityName = "2";
        public CityNameAdapter(List<String> cityList){
            this.cityList = cityList;
        }


        @Override
        public int getCount() {
            if(cityList != null)
                return cityList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            if(convertView != null){
                view =convertView;
                if(cityList.get(position).matches("[A-Z]")){
                    if(convertView.getTag() == isCityName){
                        view.setBackgroundColor(getResources().getColor(R.color.gray));
                        view.setTag(isLetters);
                    }
                }else{
                    if(convertView.getTag() == isLetters){
                        view.setBackgroundColor(getResources().getColor(R.color.white));
                        view.setTag(isCityName);
                    }
                }
                view =convertView;
            }else{
                view = View.inflate(AddCityActivity.this, R.layout.item_cityname, null);
                if(cityList.get(position).matches("[A-Z]")) {
                    view.setBackgroundColor(getResources().getColor(R.color.gray));
                    view.setTag(isLetters);
                }else{
                    view.setTag(isCityName);
                }
            }
            TextView cityName = (TextView) view.findViewById(R.id.cityName);
            cityName.setText(cityList.get(position));
            return view;
        }
    }
}
