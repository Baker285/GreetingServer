package org.example.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
@Slf4j
public class GreetingServer {
    private int port;
    private int timeout;

    public void start() {
        log.info("Starting server on port {} with timeout {}", port, timeout);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                log.info("New connection from {}", clientSocket.getRemoteSocketAddress());
                Thread thread = new Thread(new ClientHandler(clientSocket,timeout));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}