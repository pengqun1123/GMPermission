package permission.gm.com.gmpermission.GMClass;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import permission.gm.com.gmpermission.PermissionActivity;
import permission.gm.com.gmpermission.callBack.DialogCallBack;
import permission.gm.com.gmpermission.component.PermissionC;
import permission.gm.com.gmpermission.dialogs.MyDialogUtil;

/**
 * Created by **
 * on 2018/10/9.
 * 动态权限申请
 */

public class GMPermissions {
    private Context mContext;
    private Activity mActivity;
    private Integer requestCode;
    private PermissionCallBackExcute mPermissionCallBackExcute;
    //权限数组
    private String[] mPermissions;

    public GMPermissions() {
    }
    //单利
    private static GMPermissions sGMPermissions;
    public static GMPermissions instance(){
        if (sGMPermissions==null){
            synchronized (GMPermissions.class){
                if (sGMPermissions==null){
                    sGMPermissions=new GMPermissions();
                }
            }
        }
        return sGMPermissions;
    }

    public GMPermissions setParameter(Context context, Activity activity, Integer requestPermissionsCode){
        this.mContext = context;
        this.mActivity = activity;
        this.requestCode = requestPermissionsCode;
        return this;
    }

    public void setPermissionCallBackExcute(PermissionCallBackExcute permissionCallBackExcute) {
        this.mPermissionCallBackExcute = permissionCallBackExcute;
    }

    public GMPermissions(Context context, Activity activity, Integer requestPermissionsCode) {
        this.mContext = context;
        this.mActivity = activity;
        this.requestCode = requestPermissionsCode;
    }

    /**
     * 跳转到中介的activity申请权限
     * @param activity
     * @param permission
     */
    public static void skipPermissionActivity(Activity activity, String[] permission) {
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra("Permissions", permission);
        intent.putExtra("requestCode", PermissionC.WR_FILE_CODE);
        activity.startActivity(intent);
    }

    /**
     * 发起申请同意权限组时，数组中已同意的权限系统将不会再弹出该权限对话框，只会弹出未同意的权限对话框
     */
    public void checkMyPermissions(String[] permissions,Activity activity) {
        this.mPermissions = permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //当Android版本大于等于M时候
            for (int i = 0; i < permissions.length; i++) {
                int checkSelfPermission = ActivityCompat.checkSelfPermission(mContext, permissions[i]);
                if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                    //如果没有同意权限--->发起系统请求，同意权限
                    ActivityCompat.requestPermissions(mActivity, permissions, requestCode);
                }
                if (checkSelfPermission == PackageManager.PERMISSION_GRANTED && i == permissions.length - 1) {
                    //表示权限已经全部同意----->可执行需要权限的代码
                    if (mPermissionCallBackExcute != null) {
                        activity.finish();
                        mPermissionCallBackExcute.excutePermissionCodes();
                    }
                }
            }
        }
    }

    //展示dialog   --->说明权限申请的用途
    public void showPermissionDialog(String dialogTip) {
        MyDialogUtil.getInstance().setDialogCallBack(new DialogCallBack() {
            @Override
            public void positiveClick(DialogInterface dialog) {
                ActivityCompat.requestPermissions(mActivity, mPermissions, requestCode);
                dialog.dismiss();
            }

            @Override
            public void negativeClick(DialogInterface dialog) {
                dialog.dismiss();
            }
        }).showPermissionDialog(mContext, dialogTip);
    }

    public interface PermissionCallBackExcute {
        //可执行已通过权限的代码
        void excutePermissionCodes();
    }

    //对外暴露这个方法，最终将回调方法对外执行，以达到执行获取到权限后的代码
    public void transmitMethod(){
        if (mPermissionCallBackExcute!=null){
            mPermissionCallBackExcute.excutePermissionCodes();
        }
    }


}
