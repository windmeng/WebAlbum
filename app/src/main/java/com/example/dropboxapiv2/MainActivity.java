package com.example.dropboxapiv2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends BaseActivity {
    private ArrayList<String> urlList;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlList = new ArrayList<>();
        new getDropboxFileUrlTask().execute();
    }

    /**
     * 第一次运行时，获取Dropbox中所有文件的URL。
     * 异步请求URL的任务。
     */

    class getDropboxFileUrlTask extends AsyncTask<Void,Void,ArrayList<String>>{
        @Override
        protected void onPostExecute(ArrayList<String> list) {
            super.onPostExecute(list);

        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            try {
                ListFolderResult result = getDbxClientV2().files().listFolder("");
                for(Metadata metadata : result.getEntries()){
                    urlList.add(metadata.getPathLower());
                }
                Collections.reverse(urlList);
            } catch (DbxException e) {
                e.printStackTrace();
            }
            return urlList;
        }
    }

    }

