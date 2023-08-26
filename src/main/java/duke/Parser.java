package duke;

import task.Deadlines;
import task.Events;
import task.Task;
import task.Todos;

public class Parser {

    public static boolean parsable(String input) {
        return !input.equals("bye");
    }

    /**
     * Creates the tasks based on String input.
     *
     * @param input String input by user
     * @return Task Can be task.Events, task.Deadlines, Todos
     * @throws Exception
     */
    public static Task createTask(String input) throws Exception {
        // Splits based on white spaces, identifies based on the relevant /...
        String[] arrStrings = input.split("\\s+");
        String command = arrStrings[0];
        String name = "";
        if (command.equals("deadline")) {
            String deadline = "";
            boolean completedName = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/by")) {
                    completedName = true;
                    continue;
                }
                if (completedName) {
                    deadline += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            if (!completedName) {
                throw new Exception("Invalid deadline task!");
            }
            return new Deadlines(name.substring(0, name.length() - 1), deadline.substring(0, deadline.length() - 1));
        } else if (command.equals("todo")) {
            if (arrStrings.length == 1) {
                throw new Exception("OOPS!!! The description of a todo cannot be empty.");
            }
            for (int i = 1; i < arrStrings.length; i ++) {
                name += arrStrings[i] + " ";
            }
            return new Todos(name.substring(0, name.length() - 1));
        } else if (command.equals("event")) {
            String from = "";
            String to = "";
            boolean completedName = false;
            boolean completedFrom = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/from")) {
                    completedName = true;
                } else if (arrStrings[i].equals("/to")) {
                    completedFrom = true;
                } else if (!completedFrom && completedName) {
                    from += arrStrings[i] + " ";
                } else if (completedFrom && completedName) {
                    to += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            return new Events(name.substring(0, name.length() - 1), from.substring(0, from.length() - 1)
                    , to.substring(0, to.length() - 1));
        } else {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void parse(String input, Ui ui, TaskList taskList, Storage storage) {
        // Splits the input based on whitespaces.
        String command = input.split("\\s+")[0];
        int choice = -1;
        switch (command) {
        case "list":
            ui.listTask(taskList);
            break;
        case "mark":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            taskList.mark(choice);
            ui.displayMarkTask(taskList, choice);
            break;
        case "unmark":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            taskList.unmark(choice);
            ui.displayUnmarkTask(taskList, choice);
            break;
        case "delete":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            Task removedTask = taskList.delete(choice);
            ui.displayDeleteTask(removedTask, taskList);
            break;
        default:
            Task task = null;
            try {
                task = createTask(input);
            } catch (Exception e) {
                ui.showExceptionError(e);
            }
            if (task != null) {
                taskList.add(task);
                ui.displayAddTask(task, taskList);
            }
        }
        storage.updateTasks(taskList);
    }

}
