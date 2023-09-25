package ben;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a chatbot named Ben.
 */
public class Ben {
    private boolean isActive = true;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Ben() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("src/main/java/data/ben.txt");

        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a response.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(tasks);

        try {
            Command command = parser.parse(input);
            return command.execute(tasks, ui);
        } catch (EmptyDescriptionException | InvalidCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return e.getParsedString();
        }
    }

    /**
     * Runs chatbot.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        Ben ben = new Ben();
    }
}


