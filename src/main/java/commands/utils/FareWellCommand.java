package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class FareWellCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String value = String.join(" ",arguments);
        sessionContext.add("FAREWELL",value);
        out.println("200 FAREWELL OK");
        return true;
    }
}
