package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        String value = String.join("",arguments);
        Set<String> allFormats = getAllDateTimeFormats();
        for (String availableFormat : allFormats) {
            if(value.equals(availableFormat)){
                sessionContext.add("DATE",value);
                out.printf("200 DATE %s\n",value);
            }
        }
        out.println("400 BAD REQUEST");
        return true;
    }

    private static Set<String> getAllDateTimeFormats() {
        Set<String> allFormats = new HashSet<>();
        allFormats.add("yyyy-MM-dd HH:mm:ss");
        allFormats.add("yyyy-MM-dd HH:mm");
        allFormats.add("yyyy-MM-dd");
        allFormats.add("MM/dd/yyyy HH:mm:ss");
        allFormats.add("MM/dd/yyyy HH:mm");
        allFormats.add("MM/dd/yyyy");
        allFormats.add("dd/MM/yyyy HH:mm:ss");
        allFormats.add("dd/MM/yyyy HH:mm");
        allFormats.add("dd/MM/yyyy");
        allFormats.add("yyyy/MM/dd HH:mm:ss");
        allFormats.add("yyyy/MM/dd HH:mm");
        allFormats.add("yyyy/MM/dd");
        return allFormats;
    }
}
