package com.study.oauth.oauth.utils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class ProgressTrackingOutputStream extends OutputStream {
    private final OutputStream outputStream;
    private long bytesWritten;

    public ProgressTrackingOutputStream(File file) throws IOException {
        this.outputStream = new FileOutputStream(file);
        this.bytesWritten = 0;
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
        bytesWritten++;
        reportProgress();
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
        bytesWritten += b.length;
        reportProgress();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
        bytesWritten += len;
        reportProgress();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    private void reportProgress() {
        log.info("Progress: " + bytesWritten / 1000 /1000 + "MB");
    }
}
