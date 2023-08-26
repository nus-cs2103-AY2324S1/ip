import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main entry point of the bot
 */
public class Duke {

    /**
     * User Interface for generating chats.
     */
    private Ui ui = new Ui();

    /**
     * Task list to store the tasks.
     */
    private TaskList tasks = new TaskList();

    /**
     * File path to store tasks
     */
    private String filePath = "./data/duke.txt";

    /**
     * The main entry point of the application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Continuously reads user input,
     * parses it into commands, and executes the commands
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        readTask();

        while (!isExit) {
            try {
                String userInput = ui.readInput();
                ui.showLine();
                Command command = Parser.parse(userInput);
                isExit = command.execute(this.tasks, ui);
                writeTask();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Read the task from the file.
     */
    public void readTask() {
        try {
            File directory = new File("./data");
            File file = new File(filePath);

            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    ui.showError("Error creating new file: " + e.getMessage());
                }
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task;

                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("true");
                String description = parts[2];

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String date = parts[3];
                    task = new Deadline(description, date);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.mark();
                }

                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }


    /**
     * Write the task to the file.
     */
    public void writeTask() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.getRaw() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.showError("An error occurred while writing to file.");
        }
    }
}
