package io.codingbox.sparkapp;

import com.beust.jcommander.JCommander;
import java.util.logging.Logger;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = Logger.getLogger(App.class.getCanonicalName());

    public static void main( String[] args )
    {
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);

        logger.finest("Options.debug = " + options.debug);
        logger.finest("Options.servicePort = " + options.servicePort);

        port(options.servicePort);

        get("/hello", (req, res) -> "Hello World");

        get("/alive", (request, response) -> "ok");
    }
}
