package io.codingbox.sparkapp.webpack;

import java.io.File;
import java.io.IOException;

import static spark.Spark.staticFiles;

/**
 * Created by viatsko on 30/08/16.
 */
public class WebpackThread {
    public static void run() {
        ProcessBuilder pb = new ProcessBuilder("npm", "start");

        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/frontend";

        pb.directory(new File(projectDir + staticDir));

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
