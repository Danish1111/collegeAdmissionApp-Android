package com.example.abc.itmcollegealigarh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abc.itmcollegealigarh.R;

public class CustomAdapter extends PagerAdapter {
    private Integer[] imagesArray;
    private LayoutInflater inflater;
    private Context context;
    public CustomAdapter(Context context, Integer[] imagesArray) {

        this.context = context;
        this.imagesArray = imagesArray;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        final ImageView imageView = (ImageView) viewItem.findViewById(R.id.image);
        Resources resources = context.getResources();
        imageView.setImageDrawable(resources.getDrawable(imagesArray[position]));
        ((ViewPager) container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

}
