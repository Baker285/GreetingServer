package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

public class IdentifyCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        sessionContext.add("IDENTIFY", UUID.randomUUID().toString());
        out.printf("200 IDENTIFY %s\n",sessionContext.get("IDENTIFY"));
        return true;
    }
}
