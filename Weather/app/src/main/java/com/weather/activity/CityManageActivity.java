package com.weather.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.weather.R;
import com.weather.bean.WeatherInfo;
import com.weather.utils.SharedUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityManageActivity extends AppCompatActivity {

    private MyListAdapter adapter;
    private ListView myListView;

    private ImageView addCity;
    private ImageView backImage;
    private ImageView locationEnable;
    private boolean autoLocationEnableFlag;

    private LinearLayout locolCityLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);

        addCity = (ImageView)findViewById(R.id.addCity);
        addCity.setEnabled(false);
        addCity.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityManageActivity.this, AddCityActivity.class);
                intent.putStringArrayListExtra("allCityNameList", (ArrayList<String>) MainActivity.allCityNameList);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        locolCityLL = (LinearLayout)findViewById(R.id.locolCityLL);
        locolCityLL.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });

        backImage = (ImageView)findViewById(R.id.backImage);
        backImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        autoLocationEnableFlag = getAutoLocationEnableFlag();
        locationEnable = (ImageView)findViewById(R.id.locationEnable);
        if(autoLocationEnableFlag){
            locationEnable.setImageResource(R.drawable.toggle_btn_unchecked);
        }else{
            locationEnable.setImageResource(R.drawable.toggle_btn_checked);
        }
        locationEnable.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoLocationEnableFlag) {//为假时表示要自动获取
                    locationEnable.setImageResource(R.drawable.toggle_btn_checked);
                    autoLocationEnableFlag = false;
                    setAutoLocationEnableFlag(autoLocationEnableFlag);
                }else{
                    locationEnable.setImageResource(R.drawable.toggle_btn_unchecked);
                    autoLocationEnableFlag = true;
                    setAutoLocationEnableFlag(autoLocationEnableFlag);
                }
            }
        });

        adapter = new MyListAdapter(getWeatherInfos());
        myListView = (ListView)findViewById(R.id.myListView);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                Intent intent = new Intent(CityManageActivity.this,MainActivity.class);
                intent.putExtra("postion",position);
                startActivity(intent);
            }
        });

        //新开线程获取城市列表为 下个界面做准备
        new Thread(){
            public void run(){
                while(MainActivity.allCityNameList == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addCity.setEnabled(true);
                    }
                });
            }

        }.start();

    }

    private List<Map<String,Object>> getDate(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i =0;i<8;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cityName", "杭州");
            map.put("cityTemp", "26°");
            map.put("cityRangeTemp", "26°～20°");
            list.add(map);
        }
        return list;
    }

    //设置所在城市
    private void setLocalCityName(String cityName){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("localCity","杭州");
        editor.commit();
    }
    //获取所在城市
    private String getLocalCityName(){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("localCity","");
    }
    //设置是否自动获取用户位置
    private void setAutoLocationEnableFlag(boolean flag){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("autoLocationEnableFlag", flag);
        editor.commit();
    }
    //获取是否自动获取用户位置
    private boolean getAutoLocationEnableFlag(){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean falg =false;
        return  sp.getBoolean("autoLocationEnableFlag",falg);
    }

    private List<WeatherInfo> getWeatherInfos(){
        String[] citys = getCitys();
        List<WeatherInfo> myWthInfos = new ArrayList<WeatherInfo>();
        for (int i=0;i<citys.length;i++) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setCity(citys[i]);
            weatherInfo.setWendu("26°");
            myWthInfos.add(weatherInfo);
        }
        return myWthInfos;
    }

    //获取保存的城市名称
    private String[] getCitys(){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String citys = sp.getString("savedCityNames",null);
        if(citys != null){
            return citys.split("##");
        }else{
            return new String[]{"北京","上海","深圳","厦门","福州","霞浦","杭州"};
        }
    }

    //保存城市列表
    private void saveCitysInfo(List<WeatherInfo> weatherInfos){
        String names = null;
        for(WeatherInfo weatherInfo:weatherInfos){
            if(names == null){
                names = weatherInfo.getCity();
            }else{
                names = names + "##" + weatherInfo.getCity();
            }
        }
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("savedCityNames",names);
        editor.commit();
    }

    public class MyListAdapter extends BaseAdapter{
        private List<WeatherInfo> myWthInfos;
        public MyListAdapter(List<WeatherInfo> myWthInfos){
            this.myWthInfos = myWthInfos;
        }

        @Override
        public int getCount() {
            if(myWthInfos !=null)
                return myWthInfos.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(myWthInfos != null)
                return myWthInfos.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view;
            if(convertView == null) {
                view = View.inflate(CityManageActivity.this,R.layout.item_citymanage,null);
            }else{
                view = convertView;
            }
            final WeatherInfo weatherInfo = myWthInfos.get(position);
            TextView cityName = (TextView) view.findViewById(R.id.cityName);
            TextView cityRangeTemp = (TextView) view.findViewById(R.id.cityRangeTemp);
            TextView cityTemp = (TextView) view.findViewById(R.id.cityTemp);
            cityName.setText(weatherInfo.getCity());
            cityRangeTemp.setText(weatherInfo.getWendu());
            cityTemp.setText(weatherInfo.getWendu());
            ImageView clearBtn = (ImageView)view.findViewById(R.id.clearBtn);
            clearBtn.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myWthInfos.remove(weatherInfo);
                    saveCitysInfo(myWthInfos);
                    notifyDataSetChanged();
                }
            });
            return view;
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
