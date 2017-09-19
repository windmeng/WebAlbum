package com.example.dropboxapiv2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

/**
 * Created by mengdroid on 2017/7/8.
 */

public class BaseActivity extends AppCompatActivity {
    public static Context context;
    public Activity activity;
    private static DbxRequestConfig dbxRequestConfig;
    private static DbxClientV2 dbxClientV2;
    private static final String ACCESS_TOKEN = "vXWlcNPRjMAAAAAAAAABsSxCAcWpSBjmDlLVYy0g1hiBI7LO3YqXqKK0ElKznO6v";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        activity = this;
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ToastMaster.cancelToast();
    }

    /**
     * 连接Dropbox账号。
     */

    public static void init(){
        dbxRequestConfig = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        dbxClientV2 = new DbxClientV2(dbxRequestConfig,ACCESS_TOKEN);
    }

    /**
     * Toast优化，退出页面同时关闭。
     */
    static class ToastMaster {
        private static Toast sToast = null;
        private ToastMaster() {
        }
        public static void setToast(Toast toast) {
            if (sToast != null)
                sToast.cancel();
            sToast = toast;
        }

        public static void cancelToast() {
            if (sToast != null)
                sToast.cancel();
            sToast = null;
        }

    }

    /**
     * @return
     *        返回 DbxClientV2实例。
     */
    public static DbxClientV2 getDbxClientV2(){
        return dbxClientV2;
    }

    public void showMessage(String message){
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        ToastMaster.setToast(toast);
        toast.show();
    }
}
