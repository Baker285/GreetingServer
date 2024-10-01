package commands;

import commands.utils.*;
import lombok.*;
import org.example.util.GreetingCommand;
import org.example.util.SessionContext;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
public class CommandInvoker {
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandInvoker() {
        commandMap.put("NAME", new NameCommand());
        commandMap.put("LOCATION", new LocationCommand());
        commandMap.put("GREETING", new GreetCommand());
        commandMap.put("QUIT", new QuitCommand());
        commandMap.put("TIMEZONE", new TimeZoneCommand());
        commandMap.put("DATE", new DateCommand());
        commandMap.put("WEATHER", new WeatherCommand());
        commandMap.put("FAREWELL", new FareWellCommand());
        commandMap.put("IDENTIFY", new IdentifyCommand());
        commandMap.put("STATS", new StatsCommand());
        commandMap.put("SAVE",new SaveCommand());
        commandMap.put("LOAD",new LoadCommand());
        commandMap.put("RESET",new ResetCommand());
    }

    public boolean executeCommand(GreetingCommand parsedCommand, Reader in, PrintWriter out, SessionContext sessionContext) {
        Command command = commandMap.getOrDefault(parsedCommand.getName(), new DefaultCommand());
        return command.execute(parsedCommand.getArguments(), in, out, sessionContext);
    }
}
