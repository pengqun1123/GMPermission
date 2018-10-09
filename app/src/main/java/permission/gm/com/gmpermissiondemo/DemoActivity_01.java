package permission.gm.com.gmpermissiondemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.zhihu.matisse.Matisse;

import java.util.List;

import permission.gm.com.gmpermission.GMClass.GMPermissions;
import permission.gm.com.gmpermission.GMClass.GMSelectImg;
import permission.gm.com.gmpermission.assist.GlideUtil;
import permission.gm.com.gmpermission.component.PermissionC;

public class DemoActivity_01 extends AppCompatActivity implements GMPermissions.PermissionCallBackExcute {
    private ImageView mShowIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_01);

        Button selectImgBtn = findViewById(R.id.selectImgBtn);
        mShowIv = findViewById(R.id.showIv);

        //获取申请权限的对象
        GMPermissions gmPermissions = GMPermissions.instance();
        gmPermissions.setPermissionCallBackExcute(this);

        selectImgBtn.setOnClickListener(v -> {
            //检查权限，没有则申请权限   --->从Android 4.2开始向上兼容
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                /**
                 * 例如读取文件权限,将权限数组作为参数传进去
                 */
                GMPermissions.skipPermissionActivity(DemoActivity_01.this,
                        PermissionC.WR_FILES_PERMISSION);
            }
        });


    }

    @Override
    public void excutePermissionCodes() {
        //这里执行  获取权限后的操作

        //选择图片
        new GMSelectImg().picImgsOrVideo(this, PermissionC.PIC_IMG_VIDEO_CODE, 1);
    }

    //这是获取权限后执行代码接受到的结果，需要的也可以封装进来。这里不做封装，只封装动态权限请求部分
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取结果
        switch (requestCode) {
            case PermissionC.PIC_IMG_VIDEO_CODE:
                //选择图片的结果
                if (resultCode == Activity.RESULT_OK) {
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    //设置选择的图片
                    GlideUtil.loadImageWithLocation(DemoActivity_01.this,
                            mSelected.get(0), mShowIv);
                }
                break;
        }
    }
}
