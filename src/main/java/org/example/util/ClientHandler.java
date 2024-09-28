package org.example.util;

import commands.CommandInvoker;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Slf4j
public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final CommandInvoker commandInvoker;
    private final int timeOut;

    public ClientHandler(Socket clientSocket, int timeOut) {
        this.clientSocket = clientSocket;
        this.timeOut = timeOut;
        commandInvoker = new CommandInvoker();
    }


    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){

            try{
                SessionContext sessionContext = new SessionContext();
                clientSocket.setSoTimeout(timeOut*1000);
                out.println("200 Server Ready");
                String inputLine;

                while((inputLine = in.readLine())!=null){
                    if(!this.commandInvoker.executeCommand(ParseUtil.parseInput(inputLine),in,out,sessionContext)){
                        return;
                    }
                }

            } catch (SocketTimeoutException e) {
                log.warn("Client connection timed out: {}", clientSocket.getRemoteSocketAddress());
                out.println("408 Request Timeout");
            }

        }catch(IOException e) {
            log.error("Error in ClientHandler: {}", e.getMessage());
        }finally {
            try {
                clientSocket.close();
                log.info("Closed connection to client {}", clientSocket.getRemoteSocketAddress());
            } catch (IOException e) {
                log.error("Error closing client socket: {}", e.getMessage());
            }
        }
    }
}
