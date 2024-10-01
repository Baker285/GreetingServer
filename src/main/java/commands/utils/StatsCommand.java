package commands.utils;

import commands.Command;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class StatsCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        StringBuilder sb = new StringBuilder();
        int commandCount = 0;
        for (Map.Entry<String, String> entry : sessionContext.getAttributes().entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("  ");
            commandCount++;
        }
        sb.append("COMMAND:").append(commandCount);
        sessionContext.add("STATS",sb.toString().trim());
        out.printf("200 STATS \n%s\n",sessionContext.get("STATS"));
        return true;
    }
}
