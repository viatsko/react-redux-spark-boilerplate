package io.codingbox.sparkapp;

import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
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

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    private static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

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

        get("/", (request, response) -> {
            if (shouldReturnHtml(request)) {
                response.status(200);
                response.type("text/html");
                Map<String, Object> attributes = new HashMap<>();
                return freeMarkerEngine.render(new ModelAndView(attributes, "index.ftl"));
            } else {
                response.status(200);
                response.type("application/json");
                return dataToJson(null);
            }
        });

        get("/alive", (request, response) -> "ok");
    }
}
