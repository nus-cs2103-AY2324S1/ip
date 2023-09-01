package shiba;

import shiba.parsers.CommandParser;
import shiba.tasks.FilePersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents the main class of the Shiba chatbot.
 */
public class Shiba {
    private final String name;
    private final CommandParser parser;

    private Shiba(String name, String dataPath) {
        this.name = name;
        parser = new CommandParser(new FilePersistentTaskList(dataPath));
    }

    private void start() {
        Replier.printGreeting(name);
        parser.processUserInputs();
        Replier.printBye();
    }

    public static void main(String[] args) {
        Shiba shiba = new Shiba("SHIBA-BOT", "./shibaData/tasks.txt");
        shiba.start();
    }
}
