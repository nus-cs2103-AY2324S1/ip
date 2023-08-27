import parsers.CommandParser;
import tasks.*;
import ui.Replier;

public class Shiba {
    private final String name;
    private final CommandParser parser;

    public static void main(String[] args) {
        Shiba shiba = new Shiba("SHIBA-BOT", "./shibaData/tasks.txt");
        shiba.start();
    }

    private Shiba(String name, String dataPath) {
        this.name = name;
        parser = new CommandParser(new TaskList(dataPath));
    }

    private void start() {
        Replier.printGreeting(name);
        parser.processUserInputs();
        Replier.printBye();
    }
}
