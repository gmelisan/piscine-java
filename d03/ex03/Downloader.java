package ex03;

import java.io.*;
import java.net.URL;

public class Downloader implements Runnable {

    private final NumberedUrl nurl;

    public Downloader(NumberedUrl nurl) {
        this.nurl = nurl;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Thread-%d start download file number %d\n",
                    Thread.currentThread().getId(), nurl.number);
            BufferedInputStream bis = new BufferedInputStream(new URL(nurl.url).openStream());
            FileOutputStream fis = new FileOutputStream(getFilename());
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                fis.write(dataBuffer, 0, bytesRead);
            }
            bis.close();
            fis.close();
            System.out.printf("Thread-%d finish download file number %d\n",
                    Thread.currentThread().getId(), nurl.number);
        } catch (IOException e) {
            System.err.printf("Thread-%d: %s\n", Thread.currentThread().getId(), e.getMessage());
        }
    }

    private String getFilename() {
        String[] tokens = nurl.url.split("/");
        return tokens[tokens.length - 1];
    }
}
