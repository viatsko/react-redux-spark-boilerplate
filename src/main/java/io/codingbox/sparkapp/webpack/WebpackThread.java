package io.codingbox.sparkapp.webpack;

import java.io.File;
import java.io.IOException;

/**
 * Created by viatsko on 30/08/16.
 */
public class WebpackThread {
    public static void run() {
        ProcessBuilder pb = new ProcessBuilder("node", "server");

        pb.directory(new File(WebpackThread.class.getClassLoader().getResource("public/..").getPath()));

        try {
            Process p = pb.start();
            WebpackStreamReader lsr = new WebpackStreamReader(p.getInputStream());
            Thread thread = new Thread(lsr, "WebpackStreamReader");
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
