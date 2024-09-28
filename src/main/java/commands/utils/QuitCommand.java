package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class QuitCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        out.println("202 BYE");
        return false;
    }
}
