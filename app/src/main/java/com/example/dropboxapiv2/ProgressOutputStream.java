package com.example.dropboxapiv2;

import android.net.sip.SipAudioCall;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mengdroid on 2017/7/19.
 */

public class ProgressOutputStream extends OutputStream {
    private OutputStream outputStream;
    private Listener listener;
    private long completed;
    private long totalSize;
    public ProgressOutputStream(long totalSize, OutputStream outputStream, Listener listener){
        this.outputStream = outputStream;
        this.listener = listener;
        this.completed = 0;
        this.totalSize = totalSize;
    }

    @Override
    public void write(int b) throws IOException {
        this.outputStream.write(b);
        check(1);
    }

    @Override
    public void write(@NonNull byte[] b) throws IOException {
        this.outputStream.write(b);
        check(b.length);
    }

    @Override
    public void write(@NonNull byte[] b, int off, int len) throws IOException {
        this.outputStream.write(b,off,len);
        check(len);
    }

    private void check(int len){
        this.completed += len;
        this.listener.progress(this.completed,this.totalSize);
    }

    /**
     * outputstream的进度监听接口
     */
    public interface Listener{
        public void progress(long completed,long totalsize);
    }
}
