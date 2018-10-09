package permission.gm.com.gmpermission.assist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import permission.gm.com.gmpermission.R;

/**
 * Created by pq
 * on 2018/9/11.
 */

public class GlideUtil {

    /**
     * 加载网络图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    @SuppressLint("CheckResult")
    public static void loadImage(Context mContext, Integer placeHolder, String path, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolder);
        options.centerCrop();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    @SuppressLint("CheckResult")
    public static void loadImage(Context mContext, Integer placeHolder, Integer path, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolder);
        options.centerCrop();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 加载带尺寸的图片
     *
     * @param mContext
     * @param path
     * @param Width
     * @param Height
     * @param imageview
     */
    @SuppressLint("CheckResult")
    public static void loadImageWithSize(Context mContext, Integer placeHolder, String path,
                                         int Width, int Height, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolder);
        options.override(Width, Height);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    @SuppressLint("CheckResult")
    public static void loadImageWithLocation(Context mContext, Object path, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 带圆形外框的图片加载
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    @SuppressLint("CheckResult")
    public static void loadCircleImage(Context mContext, Integer placeHolder, Uri path, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(placeHolder);
        options.transform(new GlideCircleTransform(mContext, 2,
                mContext.getResources().getColor(R.color.white)));
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    @SuppressLint("CheckResult")
    public static void loadCircleImage(Context mContext, Integer placeHolder, Drawable drawable, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(placeHolder);
        options.transform(new GlideCircleTransform(mContext, 2,
                mContext.getResources().getColor(R.color.white)));
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(drawable).apply(options).into(imageview);
    }

    /**
     * 设置圆形ImageView
     * @param mContext  上下文
     * @param placeHolder   占位图
     * @param path   资源
     * @param imageview   图片空间
     */
    @SuppressLint("CheckResult")
    public static void loadCircleImage(Context mContext, Integer placeHolder, Integer path, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(placeHolder);
        options.transform(new GlideCircleTransform(mContext, 2,
                mContext.getResources().getColor(R.color.white)));
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

}
