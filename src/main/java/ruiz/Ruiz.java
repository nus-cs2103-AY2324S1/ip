package ruiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import ruiz.command.Command;
import ruiz.exception.BotException;
import ruiz.task.TaskList;
import ruiz.ui.Ui;
import ruiz.utils.Parser;
import ruiz.utils.Storage;

/**
 * Ruiz is a task management chatbot.
 */
public class Ruiz {
    private static String filePath = "tasks.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Ruiz class.
     */
    public Ruiz() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public String getResponse(String input) {
        while (true) {
            String message = "";
            try {
                Command command = parser.getCommand(input);
                switch (command) {
                case BYE:
                    message = ui.printBye();
                    break;
                case LIST:
                    message = ui.getTasks(this.tasks.getTaskList());
                    break;
                case MARK:
                    message = this.tasks.markTask(input);
                    break;
                case UNMARK:
                    message = this.tasks.unmarkTask(input);
                    break;
                case DELETE:
                    message = this.tasks.deleteTask(input);
                    break;
                case DEADLINE:
                    message = this.tasks.addDeadline(input);
                    break;
                case TODO:
                    message = this.tasks.addTodo(input);
                    break;
                case EVENT:
                    message = this.tasks.addEvent(input);
                    break;
                case FIND:
                    message = this.tasks.findTasksWithKeyword(input);
                    break;
                case UNKNOWN:
                    throw new BotException(ui.botErrorMsg());
                default:
                }
                this.storage.saveTasks(this.tasks.getTaskList());
            } catch (BotException e) {
                System.out.println(e);
            } catch (IOException e) {
                message = ui.unableToSaveTask();
            } catch (DateTimeParseException e) {
                message = ui.wrongFormat();
            }
            return message;
        }
    }
}
