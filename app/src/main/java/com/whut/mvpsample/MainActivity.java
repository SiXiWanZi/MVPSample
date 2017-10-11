package com.whut.mvpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.whut.mvpsample.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.img)
    ImageView mIvPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mIvPicture = (ImageView) findViewById(R.id.img);
        init();
        initData();
        setListener();
    }

    private void setListener() {

    }

    private void initData() {
        ImageLoader loader=new ImageLoader();
        String url="http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg";
        loader.displayImage(url,mIvPicture);
    }

    private void init() {
    }
}
