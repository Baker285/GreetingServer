package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class LocationCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String value = String.join(" ",arguments);
        sessionContext.add("LOCATION",value);
        out.println("201 LOCATION OK");
        return true;
    }
}
