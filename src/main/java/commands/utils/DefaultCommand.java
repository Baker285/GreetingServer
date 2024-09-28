package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class DefaultCommand implements Command {

    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        out.println("400 BAD REQUEST");
        return true;
    }
}
