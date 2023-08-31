package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

public class Parser {

    public Parser() {
    }

    public static boolean parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] parsedCommand = userInput.split(" ", 2);
        String command = parsedCommand[0];

        String task = "";
        if (parsedCommand.length > 1) {
            task = parsedCommand[1];
        }

        if (command.equals("bye")) {
            return false;
        } else if (command.equals("list")) {
            tasks.printList(ui);
        } else if (command.equals("mark")) {
            tasks.toggleDone(task, "mark", ui);
        } else if (command.equals("unmark")) {
            tasks.toggleDone(task, "unmark", ui);
        } else if (command.equals("delete")) {
            tasks.removeItem(task, ui);
        } else if (command.equals("todo")) {
            tasks.addItem(new ToDos(task, "0"), ui);
        } else if (command.equals("deadline")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! There are missing deadline details.");
            } else {
                tasks.addItem(new Deadlines(parsedCommand[0], parsedCommand[1],"0"), ui);
            }
        } else if (command.equals("event")) {
            parsedCommand = task.split("/");
            if (parsedCommand.length <= 2) {
                throw new DukeException("☹ OOPS!!! There are missing event details.");
            } else {
                tasks.addItem(new Events(parsedCommand[0], parsedCommand[1], parsedCommand[2], "0"), ui);
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        
        return true;
    }
}
