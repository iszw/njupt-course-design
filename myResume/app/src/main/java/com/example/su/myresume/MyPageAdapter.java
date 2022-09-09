package com.example.su.myresume;

/**
 * Created by su on 2016/12/10.
 */

        import android.support.v4.view.PagerAdapter;
        import android.view.View;
        import android.view.ViewGroup;
        import java.util.List;

public class MyPageAdapter extends PagerAdapter{

    private List<View>viewList;
    private List<String>titleList;

    public MyPageAdapter(List<View>viewList,List<String>titleList){
        this.viewList=viewList;
        this.titleList=titleList;
    }

    @Override
    public int getCount(){
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0,Object arg1){
        return arg0==arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
