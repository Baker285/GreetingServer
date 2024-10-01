package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.*;
import java.util.List;

public class SaveCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String preferences = String.join(" ", arguments);
        String sessionId = sessionContext.get("IDENTIFY");

        if (sessionId.isEmpty()) {
            out.println("401 Session Id is not created");
            return true;
        }

        File newFile = new File(String.format("%s.txt", sessionId));

        if (preferences.isEmpty()) {
            writeIntoFile(newFile, "", sessionContext);
        } else {
            writeIntoFile(newFile, preferences, sessionContext);
        }

        out.println("200 Preferences saved\n");
        return true;
    }

    public static void writeIntoFile(File newFile, String preferences, SessionContext sessionContext) {
        try (PrintWriter writer = new PrintWriter(newFile)) {
            if (preferences.isEmpty()) {
                sessionContext.getAttributes().keySet().forEach(key -> writer.println(key + "=" + sessionContext.get(key)));
            } else {
                sessionContext.getAttributes().keySet().stream().filter(preferences::contains).forEach(key -> writer.println(key + "=" + sessionContext.get(key)));
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
