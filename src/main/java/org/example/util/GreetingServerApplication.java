package org.example.util;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingServerApplication {

    @Parameter(names = {"--port","-p"},description = "the port to listen on")
    private int port = 6666;

    @Parameter(names = {"--timeout","-t"},description = "idle timeout value for each connection")
    private int timeout = 5000;

    public static void main(String[] args) {
        GreetingServerApplication app = new GreetingServerApplication();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(app)
                .build();
        jCommander.parse(args);
        app.run();
    }

    public void run() {
        GreetingServer greetingServer = new GreetingServer(port,timeout);
        greetingServer.start();
    }
}