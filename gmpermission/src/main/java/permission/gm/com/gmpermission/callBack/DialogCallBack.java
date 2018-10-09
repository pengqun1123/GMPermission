package permission.gm.com.gmpermission.callBack;

import android.content.DialogInterface;

/**
 * Created by pq
 * on 2018/9/11.
 * dialog确定和取消的回调接口
 */

public interface DialogCallBack {

    void positiveClick(DialogInterface dialog);
    void negativeClick(DialogInterface dialog);

}
