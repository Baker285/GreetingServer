package commands;

import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public interface Command {
    boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext);
}
