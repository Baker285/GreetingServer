package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class NameCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String value = String.join(" ",arguments);
        sessionContext.add("NAME",value);
        out.println("201 NAME OK");
        return true;
    }
}
