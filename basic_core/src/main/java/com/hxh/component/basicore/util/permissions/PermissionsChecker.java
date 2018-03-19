package com.hxh.component.basicore.util.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2017/4/21.
 */
public class PermissionsChecker {
    private Context context;
    public PermissionsChecker(Context context)
    {
        this.context = context;
    }
    public PermissionsChecker()
    {
        this.context = Utils.getApplicationContext();
    }


    /**
     * true 代表没获取
     * 
     * @time 2017/12/21 11:46
     * 
     * @author 
     */
    public boolean checkPermissions(String... pers)
    {
        for (String item : pers) {
            //检查权限是否获取
            if(checkOnePermission(item))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查单个权限是否获取
     * @param per
     * @return  true代表没获取
     */
    private boolean checkOnePermission(String per)
    {
        return ContextCompat.checkSelfPermission(context,per)==  PackageManager.PERMISSION_DENIED;
    }
}
