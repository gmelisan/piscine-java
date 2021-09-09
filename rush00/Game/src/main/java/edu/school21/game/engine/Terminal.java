package edu.school21.game.engine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Terminal {

    public static void init() {
        exec(new String[] {"/bin/sh", "-c", "stty -icanon min 1 </dev/tty"});
        exec(new String[] {"/bin/sh", "-c", "stty -echo </dev/tty"});
    }

    public static void restore() {
        exec(new String[] {"/bin/sh", "-c", "stty sane < /dev/tty"});
    }

    private static void exec(String[] args) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            Process p = Runtime.getRuntime().exec(args);
            int c;
            InputStream in = p.getInputStream();
            while ((c = in.read()) != -1) {
                bout.write(c);
            }
            in = p.getErrorStream();
            while ((c = in.read()) != -1) {
                bout.write(c);
            }
            int code = p.waitFor();
            if (code != 0) {
                System.out.println(bout.toString().trim() + " (code " + code + ")");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}