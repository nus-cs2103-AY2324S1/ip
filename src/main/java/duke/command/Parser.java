package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

public class Parser {
    public void processCommand(String command, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        if (command.equals("bye")) {
            ui.bye();
        }
        else if (command.equals("list")) {
            ui.printList(taskList);
        }
        else if (command.startsWith("mark")) {
            int index = 0;
            for (int i = 5; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            taskList.getTask(index - 1).markAsDone();
            ui.markTaskDone(taskList.getTask(index - 1));
        }
        else if (command.startsWith("unmark")) {
            int index = 0;
            for (int i = 7; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            taskList.getTask(index - 1).markAsNotDone();
            ui.markTaskNotDone(taskList.getTask(index - 1));
        }
        else if (command.startsWith("delete")) {
            int index = 0;
            for (int i = 7; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            Task task = taskList.deleteTask(index - 1);
            ui.deleteTask(task, taskList.size());
        }
        else {
            addTask(command, ui, taskList);
        }
        try {
            if (!command.equals("list")) {
                storage.writeFile(taskList);
            }
        } catch (IOException ioe) {
            ui.printError(ioe);
        }
    }

    public void addTask(String task, Ui ui, TaskList taskList) throws DukeException {
        if (task.startsWith("todo")) {
            if (task.length() < 6) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            Todo todo = new Todo(task.substring(5));
            taskList.addTask(todo);
            ui.addTask(todo, taskList.size());
        }
        else if (task.startsWith("deadline")) {
            if (task.length() < 10) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            String description = "";
            String by = "";
            for (int i = 9; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    description = task.substring(9, i - 1);
                    by = task.substring(i + 4);
                    break;
                }
            }
            Deadline deadline = new Deadline(description, by);
            taskList.addTask(deadline);
            ui.addTask(deadline, taskList.size());
        }
        else if (task.startsWith("event")) {
            if (task.length() < 7) throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            int slash1 = -1;
            int slash2 = -1;
            for (int i = 0; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    if (slash1 == -1) slash1 = i;
                    else slash2 = i;
                }
            }
            String description = task.substring(6, slash1 - 1);
            String from = task.substring(slash1 + 6, slash2 - 1);
            String to = task.substring(slash2 + 4);
            Event event = new Event(description, from, to);
            taskList.addTask(event);
            ui.addTask(event, taskList.size());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
