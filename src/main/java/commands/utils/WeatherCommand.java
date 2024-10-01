package commands.utils;

import com.github.javafaker.Faker;
import commands.Command;
import org.example.util.SessionContext;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

public class WeatherCommand implements Command {
    @Override
    public boolean execute(List<String> arguments, Reader in, PrintWriter out, SessionContext sessionContext) {
        if(!sessionContext.get("LOCATION").isEmpty()){
            String value = String.join(" ",arguments);
            sessionContext.add("WEATHER",value);
            out.printf("200 WEATHER %s\n",generateWeather());
            return true;
        }
        else{
            out.println("400 BAD REQUEST");
            return false;
        }

    }

    public static String generateWeather(){
        Faker faker = new Faker();
        String weather = faker.weather().description();
        double temperatureFahrenheit = faker.number().numberBetween(32, 100);
        double temperatureCelsius = (temperatureFahrenheit - 32) * 5.0/9.0;
        String humidity = faker.number().numberBetween(0, 100) + "%";
        return String.join(weather,temperatureCelsius+"%C",humidity);
    }
}
