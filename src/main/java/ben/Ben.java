package ben;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Ben {
    private boolean isActive = true;
    private final TaskList tasks = new TaskList();
    private final Storage storage;
    private final Ui ui;

    public Ben(String filePath) {
        ui = new Ui();
        File f = new File(filePath);
        storage = new Storage(f);
    }

    public void deactivate() {
        isActive = false;
    }

    public void run() {

        // Load tasks from data.txt
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ui.greeting();

        //Parser parser = new Parser();
        while (isActive) {
            String message = ui.nextLine();
            Parser parser = new Parser(tasks);

            try {
                Command command = parser.parse(message);
                command.execute(tasks, ui);
                isActive = !command.isExit();
            } catch (EmptyDescriptionException | InvalidCommandException e) {
                Ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError(e.getParsedString());
            }
        }

        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Ben ben = new Ben("src/main/java/data/ben.txt");
        ben.run();
    }
}


