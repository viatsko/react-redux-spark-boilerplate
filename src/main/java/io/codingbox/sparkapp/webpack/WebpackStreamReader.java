package io.codingbox.sparkapp.webpack;

import io.codingbox.sparkapp.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Created by viatsko on 30/08/16.
 */
public class WebpackStreamReader implements Runnable {

    private BufferedReader reader;

    private static final Logger logger = Logger.getLogger(WebpackStreamReader.class.getCanonicalName());

    public WebpackStreamReader(InputStream is) {
        this.reader = new BufferedReader(new InputStreamReader(is));
    }

    @Override
    public void run() {
        try {
            String line = reader.readLine();
            while (line != null) {
                logger.info(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
