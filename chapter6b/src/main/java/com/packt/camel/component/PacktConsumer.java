package com.packt.camel.component;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PacktConsumer extends DefaultConsumer {

    private ServerSocket serverSocket;

    public PacktConsumer(Endpoint endpoint, Processor processor) {
        super(endpoint, processor);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStop();
        serverSocket = new ServerSocket(4444);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.println("Echoing " + inputLine);
            if (inputLine.trim().equalsIgnoreCase("quit")) {
                break;
            }
        }
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        serverSocket.close();
    }

}
