package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.List;

public class TimeZoneCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String value = String.join(" ",arguments);
        try{
            ZoneId.of(value);
            sessionContext.add("TIMEZONE",value);
            out.println("200 TIMEZONE OK\n");
            return true;
        }catch (ZoneRulesException e){
            out.println("400 BAD REQUEST");
            return false;
        }
    }
}
