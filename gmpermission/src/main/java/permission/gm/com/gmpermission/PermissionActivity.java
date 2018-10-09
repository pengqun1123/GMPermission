package permission.gm.com.gmpermission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import permission.gm.com.gmpermission.GMClass.GMPermissions;
import permission.gm.com.gmpermission.component.PermissionC;

public class PermissionActivity extends AppCompatActivity {

    private GMPermissions mGmPermissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        String[] permissions = getIntent().getStringArrayExtra("Permissions");

        //申请权所需要的对象
        mGmPermissions= GMPermissions.instance().setParameter(this, this,
                PermissionC.WR_FILE_CODE);
        //申请权限
        if (permissions!=null){
            mGmPermissions.checkMyPermissions(permissions,PermissionActivity.this);
        }

    }

    /*//这是获取权限后执行代码接受到的结果，需要的也可以封装进来。这里不做封装，只封装动态权限请求部分
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取结果
        switch (requestCode) {
            case PermissionC.PIC_IMG_VIDEO_CODE:
                //选择图片的结果
                if (resultCode== Activity.RESULT_OK){
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    //设置选择的图片
                   *//* GlideUtil.loadCircleImage(UserDetailActivity.this,
                            R.drawable.touxiang_icon, mSelected.get(0), userAvatarIv);
                    GlideUtil.loadCircleImage(UserDetailActivity.this,
                            R.drawable.touxiang_icon, mSelected.get(0), mUserAvatarRv);*//*
                    // TODO: 2018/10/9 上传选择的图片 --->用户图片

                }
                break;
        }
    }*/

    //权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionC.WR_FILE_CODE:
                for (int i = 0; i < grantResults.length; i++) {
                    //如果某一个权限用户没有同意--->申请权限
                    if (grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                        //向用户展示该权限的dialog--->权限用途
                        mGmPermissions.showPermissionDialog(getString(R.string.get_img_tip));
                    }
                    if (i==grantResults.length-1&&grantResults[i]==PackageManager.PERMISSION_GRANTED){
                        //表示用户已经同意所有的权限--->执行需要权限后的操作
                        mGmPermissions.transmitMethod();
                        //关闭该activity
                        finish();
                    }
                }
                break;
        }
    }

}
