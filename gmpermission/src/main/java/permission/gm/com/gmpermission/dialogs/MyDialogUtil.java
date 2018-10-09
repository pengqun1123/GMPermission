package permission.gm.com.gmpermission.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import permission.gm.com.gmpermission.R;
import permission.gm.com.gmpermission.callBack.DialogCallBack;

/**
 * Created by pq
 * on 2018/9/11.
 */

public class MyDialogUtil {
    private static MyDialogUtil sMyDialogUtil;

    public static MyDialogUtil getInstance() {
        if (sMyDialogUtil == null) {
            synchronized (MyDialogUtil.class) {
                if (sMyDialogUtil == null) {
                    sMyDialogUtil = new MyDialogUtil();
                }
            }
        }
        return sMyDialogUtil;
    }

    private static DialogCallBack mDialogCallBack;

    public MyDialogUtil setDialogCallBack(DialogCallBack dialogCallBack) {
        mDialogCallBack = dialogCallBack;
        return this;
    }

    public void showPermissionDialog(Context context, String permissionTip) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(permissionTip)
                .setPositiveButton(context.getString(R.string.resume), (dialog, which) -> {
                    //确定，调用确定的回调
                    if (mDialogCallBack != null) {
                        mDialogCallBack.positiveClick(dialog);
                    }
                    dialog.dismiss();
                })
                .setNegativeButton(context.getString(R.string.cancel), (dialog, which) -> {
                    if (mDialogCallBack != null) {
                        mDialogCallBack.negativeClick(dialog);
                    }
                    dialog.dismiss();
                    //退出应用
                    //activity.onBackPressed();
                })
                .create();
        alertDialog.show();
    }
}
