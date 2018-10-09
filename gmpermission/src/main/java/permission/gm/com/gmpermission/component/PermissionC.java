package permission.gm.com.gmpermission.component;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by **
 * on 2018/9/7.
 */


public final class PermissionC {
    //位置权限
    public static final String [] LOCATION_PERMISSION={
            Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.ACCESS_COARSE_LOCATION
    };
    //读写文件的权限
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static final String[] WR_FILES_PERMISSION={
            Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final int LOCATION_CODE=2001;
    public static final int WR_FILE_CODE=2002;
    //选图片文件
    public static final int PIC_IMG_VIDEO_CODE=2003;
    public static final int USER_INFO_CITY_CODE=2004;
    public static final int CURRENT_CITY_CODE=2005;


    /*
    页面间传值的Key
     */
    public static final String USER_CITY="user_city";
    public static final String CURRENT_CIRT="current_city";
    public static final String USER_PIC="user_pic";


}
