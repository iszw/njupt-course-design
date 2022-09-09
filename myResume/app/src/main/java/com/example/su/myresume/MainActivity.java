package com.example.su.myresume;

        import android.graphics.Color;
        import android.media.MediaPlayer;
        import android.support.v4.view.PagerTabStrip;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.TypedValue;
        import android.view.View;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Button;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean IsPlay = false;
    private List<View> viewList;
    private ViewPager pager;
    private PagerTabStrip tab;
    private List<String> titleList;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewList = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.view1, null);
        View view2 = View.inflate(this, R.layout.view2, null);
        View view3 = View.inflate(this, R.layout.view3, null);

        final Button button = (Button) view2.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!IsPlay) {
                    play();
                    IsPlay = true;
                    button.setText("Music Stop");

                } else {
                    mediaPlayer.pause();
                    IsPlay = false;
                    button.setText("Music Begin");
                }

            }
        });

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        titleList = new ArrayList<String>();
        titleList.add("我的简介");
        titleList.add("自带BGM");
        titleList.add("微博互FO");

        tab = (PagerTabStrip) findViewById(R.id.tab);
        tab.setBackgroundColor(Color.argb(200, 54, 54, 54));
        tab.setTextColor(Color.argb(255, 80, 238, 188));
        tab.setDrawFullUnderline(false);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
        tab.setTabIndicatorColor(Color.argb(255, 80, 238, 188));

        pager = (ViewPager) findViewById(R.id.pager);
        MyPageAdapter adapter = new MyPageAdapter(viewList, titleList);
        pager.setAdapter(adapter);

        final WebView webview = (WebView) view3.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowContentAccess(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.loadUrl("https://www.baidu.com/");
        webview.setWebViewClient(new HelloWebViewClient ());
    }

    private void play() {
        mediaPlayer = MediaPlayer.create(this, R.raw.my);
        mediaPlayer.start();
        IsPlay = true;
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}