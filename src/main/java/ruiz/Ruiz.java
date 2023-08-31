package ruiz;

import ruiz.command.Command;
import ruiz.exception.BotException;
import ruiz.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Ruiz is a task management chatbot.
 */
public class Ruiz {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Ruiz class.
     * @param filePath path to the file that the tasks are saved in.
     */
    public Ruiz(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.unableToLoadFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        Scanner inputObj = new Scanner(System.in);
        ui.greet();
        while (true) {
            try {
                String input = inputObj.nextLine();
                Command command = parser.getCommand(input);
                switch (command) {
                case BYE:
                    ui.bye();
                    return;
                case LIST:
                    ui.getTasks(this.tasks.getTaskList());
                    break;
                case MARK:
                    this.tasks.markTask(input);
                    break;
                case UNMARK:
                    this.tasks.unmarkTask(input);
                    break;
                case DELETE:
                    this.tasks.deleteTask(input);
                    break;
                case DEADLINE:
                    this.tasks.addDeadline(input);
                    break;
                case TODO:
                    this.tasks.addTodo(input);
                    break;
                case EVENT:
                    this.tasks.addEvent(input);
                    break;
                case UNKNOWN:
                    throw new BotException(ui.botErrorMsg());
                }
                this.storage.saveTasks(this.tasks.getTaskList());
            } catch (BotException e) {
                System.out.println(e);
            } catch (IOException e) {
                ui.unableToSaveTask();
            } catch (DateTimeParseException e) {
                ui.wrongFormat();
            }
        }
    }

    public static void main(String[] args) {
        new Ruiz("tasks.txt").run();
    }
}
