package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command {
    private static final HashMap<String, Cmd> COMMAND_MAP = new HashMap<>(
            Map.of("todo", Cmd.TODO, "event", Cmd.EVENT, "mark", Cmd.MARK,
                    "unmark", Cmd.UNMARK, "list", Cmd.LIST, "bye", Cmd.EXIT,
                    "deadline", Cmd.DEADLINE, "delete", Cmd.DELETE)
    );

    private enum Cmd {
        MARK, UNMARK, LIST, EXIT, TODO, DEADLINE, EVENT, INVALID, DELETE
    }

    private String date1;
    private String date2;
    private String description;
    private Cmd action;

    public Command(List<String> parts) {
        if (!COMMAND_MAP.containsKey(parts.get(0))) {
            this.action = Cmd.INVALID;
            return;
        }
        this.action = COMMAND_MAP.get(parts.get(0));
        if (parts.size() > 1) {
            description = parts.get(1);
        }
        if (parts.size() > 2) {
            this.date1 = parts.get(2);
        }
        if (parts.size() > 3) {
            this.date2 = parts.get(3);
        }
    }

    // returns whether this command is to exit, if not continue to carry out the required command
    // and return false
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = null;
        switch (this.action) {
        case INVALID:
            throw new DukeException("Command given is invalid! You must start with a "
                    + "todo/event/deadline to add tasks, "
                    + "or list/mark/unmark/bye for other purposes");
        case EXIT:
            return true;
        case LIST:
            if (this.description != null) {
                // description field can only be used when we try to list tasks on certain date
                try {
                    String content = tasks.findTasksOnDate(LocalDate.parse(description));
                    ui.display(content);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
                }
            } else {
                ui.display(tasks.toString());
            }
            return false;
        // tasks related commands below
        case MARK:
            if (this.description == null) {
                throw new DukeException("You must enter a number to indicate the task to "
                        + "mark");
            }
            try {
                task = tasks.doTask(Integer.parseInt(this.description) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number. Please enter a valid number to "
                        + "mark/unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task number you are trying to mark "
                        + "does not exist yet.");
            }
            ui.display("    I've marked this task as done:");
            ui.display("    " + task);
            return false;
        case UNMARK:
            if (this.description == null) {
                throw new DukeException("You must enter a number to indicate the task to "
                        + "unmark");
            }
            try {
                task = tasks.undoTask(Integer.parseInt(this.description) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number. Please enter a valid number to "
                        + "mark/unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task number you are trying to unmark "
                        + "does not exist yet.");
            }
            ui.display("    I've marked this task as not done yet:");
            ui.display("    " + task);
            return false;
        case DELETE:
            if (this.description == null) {
                throw new DukeException("You must enter a number to indicate the task to "
                        + "delete");
            }
            try {
                task = tasks.deleteTask(Integer.parseInt(this.description) - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number. Please enter a valid number to "
                        + "mark/unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task number you are trying to delete "
                        + "does not exist yet.");
            }
            ui.display("    Noted. I've removed this task:");
            break;
        case TODO:
            if (this.description == null) {
                throw new DukeException("Description of todo cannot be empty");
            }
            task = new Todo(description);
            tasks.addTask(task);
            ui.display("    Added Todo to the list of tasks:");
            break;
        case EVENT:
            if (this.description == null || this.date1 == null || this.date2 == null) {
                throw new DukeException("Invalid format for adding an event! "
                        + "Please enter in this format:\n"
                        + "event [description] /from [date] /to [date]");
            }
            try {
                task = new Event(this.description, this.date1, this.date2);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
            }
            tasks.addTask(task);
            ui.display("    Added Event to the list of tasks:");
            break;
        case DEADLINE:
            if (this.description == null || this.date1 == null) {
                throw new DukeException("Invalid format for adding a deadline! "
                        + "Please enter in this format:\n"
                        + "deadline [description] /by [date]");
            }
            try {
                task = new Deadline(this.description, this.date1);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
            }
            tasks.addTask(task);
            ui.display("    Added Deadline to the list of tasks:");
            break;

        default:
            throw new DukeException("There seems to be some error here");
        }
        if (task == null) {
            throw new DukeException("There might be an error somewhere. The task was not "
                    + "extracted properly.");
        }
        ui.display("    " + task);
        ui.display("    You currently have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.saveToStorage());
        } catch (IOException e) {
            throw new DukeException(
                    "Something went wrong while trying to save the tasks to the disk!");
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Command)) {
            return false;
        }

        Command o = (Command) other;
        boolean result = true;
        if (this.date1 == null) {
            result &= (o.date1 == null);
        } else {
            result &= (this.date1.equals(o.date1));
        }
        if (this.date2 == null) {
            result &= (o.date2 == null);
        } else {
            result &= (this.date2.equals(o.date2));
        }
        if (this.description == null) {
            result &= (o.description == null);
        } else {
            result &= (this.description.equals(o.description));
        }
        result &= (this.action == o.action);

        return result;
    }
}
