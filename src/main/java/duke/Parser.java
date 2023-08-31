package duke;

import java.util.EnumSet;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    private enum Command {
        invalid, bye, list, mark, unmark, delete, todo, deadline, event;

        /*
        If more task types are added in the future can just add here so I don't have to change in main
        when checking if it's an adding task command
         */
        public static EnumSet<Command> taskTypes() {
            return EnumSet.of(todo, deadline, event);
        }
    }

    public boolean parseCommand(String input) {
        Command cmd = Command.invalid;
        for (Command c : Command.values()) {
            if (input.startsWith(c.toString())) {
                cmd = c;
            }
        }
        if (cmd.equals(Command.bye)) {
            ui.showByeMessage();
            return false;
        } else if (cmd.equals(Command.list)) {
            taskList.listTasks();
            return true;
        } else if (cmd.equals(Command.mark)) {
            try {
                taskList.markTask(Integer.parseInt(input.split(" ")[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showInvalidIndexError();
            }
            return true;
        } else if (cmd.equals(Command.unmark)) {
            try {
                taskList.unmarkTask(Integer.parseInt(input.split(" ")[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showInvalidIndexError();
            }
            return true;
        } else if (cmd.equals(Command.delete)) {
            try {
                taskList.deleteTask(Integer.parseInt(input.split(" ")[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showInvalidIndexError();
            }
            return true;
        } else if (cmd.equals(Command.invalid)) {
            ui.showInvalidCommandError();
            return true;
        } else if (Command.taskTypes().contains(cmd)) {
            try {
                createTask(cmd, input);
            } catch (DukeInvalidDateException e) {
                ui.showAddTaskError(e.getMessage());
            }
            return true;
        }
        return true;
    }

    private String getTaskInfo(String input) {
        for (Command cmd : Command.taskTypes()) {
            if (input.startsWith(cmd.toString())) {
                return input.replace(cmd + " ", "");
            }
        }
        // Will never happen
        return null;
    }

    private void parseAndAndEvent(String info) throws DukeInvalidDateException {
        if (!info.matches(".*\\b /by \\b.*") || !info.matches(".*\\b /to \\b.*")) {
            ui.showAddTaskError("An event must contain a description," +
                    " start and end specified with `/by` and `/to`!");
        } else {
            // In case the user does /to before /by, split /by and /to and vice versa to get by and to
            try {
                String by = info.split(" /by ")[1];
                by = by.split(" /to ")[0];
                String to = info.split(" /to ")[1];
                to = to.split(" /by ")[0];
                // Get index 0 to get before the /by and /to, so either way you'll get only the task
                String task = info.split(" /to ")[0].split(" /by ")[0];
                task = task.replaceFirst("event ", "");
                Event event = new Event(task, by, to);
                taskList.addTask(event);
            } catch (IndexOutOfBoundsException e) {
                ui.showAddTaskError("Description, /by and /to cannot be empty!");
            }
        }
    }
    private void createTask(Command cmd, String input) throws DukeInvalidDateException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            ui.showAddTaskError("Task description cannot be empty!");
            return;
        }
        String info = getTaskInfo(input);
        if (cmd.equals(Command.todo)) {
            taskList.addTask(new Todo(info));
        } else if (cmd.equals(Command.deadline)) {
            String[] splitInfo = info.split(" /by ", 2);
            if (splitInfo.length == 2) {
                taskList.addTask(new Deadline(splitInfo[0], splitInfo[1]));
            } else {
                ui.showAddTaskError("Description and /by cannot be empty!");
            }
        } else if (cmd.equals(Command.event)) {
            parseAndAndEvent(info);
        }
    }
}
