package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class GreetCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String name = sessionContext.get("NAME");
        String location = sessionContext.get("LOCATION");
        out.printf("Hello %s of %s\n",name,location);
        return true;
    }
}
