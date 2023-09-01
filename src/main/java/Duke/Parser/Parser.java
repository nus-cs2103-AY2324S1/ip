package Duke.Parser;

import Duke.DukeException.DukeException;
import Duke.Storage.Storage;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

public class Parser {

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void parse(String input, Ui ui, Storage storage, TaskList tasks) throws DukeException {
        String command = input.split(" ")[0].toUpperCase();
        Task targetTask;
        if (command.equals("BYE")) {
            ui.bye();
            storage.editStorage(tasks);
        } else if (command.equals("LIST")) {
            ui.listing(tasks);
        } else if (command.equals("TODO")) {
            if (ToDos.isTodo(input)) {
                targetTask = new ToDos(input.substring(5));
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask(targetTask);
            } else {
                throw new DukeException("This todo is invalid");
            }
        } else if (command.equals("DEADLINE")) {
            if (Deadlines.isDeadline(input)) {
                targetTask = new Deadlines(
                        input.substring(9, input.indexOf("/by ")),
                        input.substring(input.indexOf("/by ") + 4)
                );
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask(targetTask);
            } else  {
                throw  new DukeException("This deadline is invalid");
            }
        } else if (command.equals("EVENT")) {
            if (Events.isEvent(input)) {
                targetTask = new Events(
                        input.substring(6, input.indexOf("/from ")),
                        input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ") - 1),
                        input.substring(input.indexOf("/to ") + 4)
                );
                ui.addTask(targetTask, tasks.getNumberOfTask() + 1);
                tasks.addTask((targetTask));
            } else {
                throw new DukeException("This event is invalid");
            }
        } else if (command.equals("MARK")){
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    tasks.getTask(index).changeMarkStatus(true);
                    ui.markAsDone(tasks.getTask(index));
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else if (command.equals("UNMARK")) {
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    tasks.getTask(index).changeMarkStatus(false);
                    ui.markAsNotDone(tasks.getTask(index));
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else if (command.equals("DELETE")) {
            if (input.split(" ").length == 2 && isInteger(input.split(" ")[1])) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index <= tasks.getNumberOfTask() - 1) {
                    ui.deleteTask(tasks.getTask(index), tasks.getNumberOfTask() - 1);
                    tasks.deleteTask(index);
                } else {
                    throw new DukeException("You don't have that many tasks");
                }
            }
        } else {
            throw new DukeException("OOPS! I don't understand this command");
        }
    }
}
