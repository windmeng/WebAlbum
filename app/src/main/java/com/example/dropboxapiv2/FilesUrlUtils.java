package com.example.dropboxapiv2;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by mengdroid on 2017/7/13.
 */

public class FilesUrlUtils {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     *保存ArrayList到本地。
     *
     * @param list
     *          传入的ArrayList。
     * @return
     *          数据是否保存成功。
     */
    public Boolean saveFilesUrl(Context context,ArrayList<String> list){
        sp = context.getSharedPreferences("filesUrlList",Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("UrlList_size",list.size());
        for(int i=0;i<list.size();){
            editor.remove("Url_"+i);
            editor.putString("Url_"+i,list.get(i));
        }
        return editor.commit();
    }

    /**
     *读取本地保存的ArrayList。
     *
     * @param list
     *             用来储存从本地读取URL的ArrayList。
     */
    public ArrayList<String> loadFilesUrl(Context context ,ArrayList<String> list){
        list.clear();
        sp = context.getSharedPreferences("filesUrlList",Context.MODE_PRIVATE);
        int size = sp.getInt("UrlList_size",0);
        for(int i=0;i<size;i++){
            list.add(sp.getString("Url_"+i,null));
        }
        return list;
    }
}
