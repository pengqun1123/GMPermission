package permission.gm.com.gmpermission.GMClass;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import permission.gm.com.gmpermission.R;
import permission.gm.com.gmpermission.assist.GifSizeFilter;
import permission.gm.com.gmpermission.assist.Glide4Engine;
import permission.gm.com.gmpermission.component.PermissionC;

/**
 * Created by pq
 * on 2018/10/9.
 * 选择图片的API全部写在这个里边
 */

public class GMSelectImg {

    public GMSelectImg(){}

    /**
     * 此处imageEngine使用的Glide是V 3的版本
     * @param activity
     * @param resultCode
     */
    public void selectImg(Activity activity,Integer resultCode){
        Matisse.from(activity)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);
    }

    //取图片或视频   ---->此处Glide使用的是V 4 的版本
    public void picImgsOrVideo(Activity activity,Integer resultCode,int maxSelectable) {
        Matisse.from(activity)
                .choose(MimeType.ofAll())
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(maxSelectable)//可选的最大数
                .spanCount(3)//网格数
                .capture(true)//选择照片时，是否显示拍照
                //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .captureStrategy(new CaptureStrategy(true, "newmatch.zbmf.com.testapplication"))
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)//图片的缩放比例
                .imageEngine(new Glide4Engine())//图片加载引擎
                .theme(R.style.Matisse_MyZhihu /*| R.style.Matisse_Dracula*/)
                .forResult(PermissionC.PIC_IMG_VIDEO_CODE);
    }
}
