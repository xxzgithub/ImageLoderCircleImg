package bwie.com.imagelodercircleimg;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).writeDebugLogs().build();
        // 初始化
        ImageLoader.getInstance().init(configuration);
        initView();
        displayImage();
    }

    private void displayImage() {
        // 网络图片路径
        String uri = ("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=dce088c1a3ec8a1300175fa39f6afbfa/622762d0f703918f2b2091e45b3d269759eec42f.jpg");

        //本地图片路径
        String path = "/sdcard/DCIM/Camera/1459234769714.jpg";
//        String uri = ImageDownloader.Scheme.FILE.wrap(path);
        // 图片大小
        ImageSize mImageSize = new ImageSize(300, 300);
        // 图片的配置
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance().displayImage(uri, mImageView, mOptions, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {

            @Override
            public void onProgressUpdate(String arg0, View arg1, int current, int total) {
                mProgressBar.setProgress(current);
            }
        });
        //不需要设置监听,也不需要手动设置mImageView,还带有错误加载显示
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
    }
}
