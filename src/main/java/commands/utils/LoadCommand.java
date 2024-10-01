package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.*;
import java.util.List;

public class LoadCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String sessionId = String.join(" ",arguments);

        File existingFile = new File(String.format("%s.txt", sessionId));

        if(!existingFile.exists()){
            out.println("404 Preferences file not found");
        }
        else{
            loadPreferences(existingFile,out,sessionContext);
            out.println("200 Preferences loaded");
        }
        return true;
    }

    public void loadPreferences(File existingFile,PrintWriter out,SessionContext sessionContext){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(existingFile))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split("=");
                sessionContext.add(keyValue[0], keyValue[1]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sessionContext.getAttributes().keySet().forEach(key-> out.println(key + "=" + sessionContext.get(key)));
    }
}
