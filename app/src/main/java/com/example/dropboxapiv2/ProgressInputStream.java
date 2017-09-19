package com.example.dropboxapiv2;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by mengdroid on 2017/7/19.
 */

public class ProgressInputStream extends InputStream  {
    private InputStream inputStream;
    private Listener listener;
    private long completed;
    private long totalSize;
    public ProgressInputStream(long totalSize,InputStream inputStream,Listener listener){
        this.inputStream = inputStream;
        this.listener = listener;
        this.completed = 0;
        this.totalSize = totalSize;
    }

    @Override
    public int read(@NonNull byte[] b, int off, int len) throws IOException {
        this.inputStream.read(b,off,len);
        check(len);
        return super.read(b, off, len);
    }

    @Override
    public int read(@NonNull byte[] b) throws IOException {
        this.inputStream.read(b);
        check(b.length);
        return super.read(b);
    }

    @Override
    public int read() throws IOException {
        this.inputStream.read();
        check(1);
        return 0;
    }

    private void check(int len){
        this.completed += len;
        this.listener.progress(this.completed,this.totalSize);

    }

    /**
     * InputStream的进度监听接口。
     */
    public interface Listener{
        public void progress(long completed,long totalsize);
    }
}
