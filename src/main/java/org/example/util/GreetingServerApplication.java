package org.example.util;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;
import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

@Slf4j
public class GreetingServerApplication {

    @Parameter(names = {"--port","-p"},description = "the port to listen on")
    private int port = 6666;

    @Parameter(names = {"--timeout","-t"},description = "idle timeout value for each connection")
    private int timeout = 5000;

    @Parameter(names = {"--motd","-m"},description = "the message that the user will see when server starts")
    private String message = "Welcome";

    public static void main(String[] args) throws IOException {
        GreetingServerApplication app = new GreetingServerApplication();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(app)
                .build();
        jCommander.parse(args);
        app.run();
    }

    public void run() throws IOException {
        String asciiArt = FigletFont.convertOneLine(message);
        System.out.println(asciiArt);
        GreetingServer greetingServer = new GreetingServer(port,timeout);
        greetingServer.start();
    }
}