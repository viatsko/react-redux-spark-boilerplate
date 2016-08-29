package io.codingbox.sparkapp;

import com.beust.jcommander.Parameter;

/**
 * Created by viatsko on 29/08/16.
 */
public class CommandLineOptions {
    @Parameter(names = "--debug")
    boolean debug = true;

    @Parameter(names = {"--service-port"})
    Integer servicePort = 5000;
}
