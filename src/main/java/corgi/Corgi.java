package corgi;
import java.util.Scanner;

import corgi.commands.Command;
import corgi.commands.CommandExecutionException;
import corgi.parsers.CommandParser;
import corgi.parsers.InvalidCommandFormatException;
import corgi.parsers.InvalidCommandTypeException;
import corgi.parsers.TaskParser;
import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.Ui;

public class Corgi {
    private TaskList tasks;
    private Storage<Task> storage;
    private Ui ui;

    public static void main(String[] args) {
        Corgi bot = new Corgi();
        bot.start();
    }

    /**
     * Constructs new Corgi chatbot with an empty task list.
     */
    public Corgi() {
        this.ui = new Ui();
        this.storage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        this.tasks = new TaskList(storage.load());

        if (tasks.size() > 0) {
            this.ui.showTasksLoaded(tasks.size());
        }
    }

    /**
     * Starts the chatbot - Corgi.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        this.ui.setScanner(sc);

        this.ui.showIntro();

        boolean isExit = false;

        while(!isExit) {
            String userInput = this.ui.readCommand();

            if (userInput.equals("")) {
                continue;
            }

            this.ui.showStartLine();

            Command cmd = null;

            try {
                cmd = new CommandParser().parse(userInput);
                cmd.execute(this.tasks, this.ui, this.storage);
                isExit = cmd.isExit();
            } catch (InvalidCommandFormatException e) {
                this.ui.showError(e.getClass().getSimpleName(), e.getMessage());
            } catch (InvalidCommandTypeException e) {
                // Todo: Print all valid commands
                this.ui.showError(e.getClass().getSimpleName(), e.getMessage());
            } catch (CommandExecutionException e) {
                this.ui.showError(e.getClass().getSimpleName(), e.getMessage());
            }

            this.ui.showEndLine();
        }

        sc.close();
    }
}
