package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    enum COMMAND_TYPE {
        TODO,
        DEADLINE,
        EVENT
    };

    private COMMAND_TYPE type;
    private String message;
    private String deadline;
    private String from;
    private String to;

    public AddCommand(String message) {
        this.type = COMMAND_TYPE.TODO;
        this.message = message;
        this.deadline = null;
        this.from = null;
        this.to = null;
    }

    public AddCommand(String message, String deadline) {
        this.type = COMMAND_TYPE.DEADLINE;
        this.message = message;
        this.deadline = deadline;
        this.from = null;
        this.to = null;
    }

    public AddCommand(String message, String from, String to) {
        this.type = COMMAND_TYPE.EVENT;
        this.message = message;
        this.deadline = null;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask;
            switch (this.type) {
                case TODO:
                    newTask = new Todo(message);
                    break;
                case DEADLINE:
                    newTask = new Deadline(message, LocalDateTime.parse(deadline));
                    break;
                case EVENT:
                    newTask = new Event(message, LocalDateTime.parse(from), LocalDateTime.parse(to));
                    break;
                default:
                    throw new DukeException("Task type is invalid!");
                    // break not needed as exception is thrown
            }
            tasks.add(newTask);
            ui.print("Got it. I've added this task:");
            ui.print("  " + newTask);
            ui.print("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.saveTasks(tasks);
        } catch (DateTimeParseException e) {
            throw new DukeException("Dates should be formatted yyyy-mm-ddThh:mm:ss,\n" +
                    "\t e.g. 2023-09-12T12:06:53");
        }
    }

    @Override
    public String getCommandType() {
        String typeStr;
        switch (this.type) {
            case TODO:
                typeStr = "Todo";
                break;
            case DEADLINE:
                typeStr = "Deadline";
                break;
            case EVENT:
                typeStr = "Event";
                break;
            default:
                typeStr = "";
        }
        return "Add " + typeStr;
    }
}
