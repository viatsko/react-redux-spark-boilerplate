package io.codingbox.sparkapp;

import com.beust.jcommander.JCommander;
import java.util.logging.Logger;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

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

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(App.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

        get("/hello", (req, res) -> "Hello World");

        get("/alive", (request, response) -> "ok");
    }
}
