package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.File;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class ResetCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String sessionId = String.join(" ", arguments);

        File existingFile = new File(String.format("%s.txt", sessionId));

        if (!existingFile.exists()) {
            out.println("404 Preferences file not found");
        } else {
            boolean deleted = existingFile.delete();
            out.printf("%d Preferences reset %s\n",
                    deleted ? 200 : 400,
                    deleted ? "successful" : "failed");
        }
        return true;
    }
}
