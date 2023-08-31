package parser;

import exception.InvalidCommandException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidToDoException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

public class Parser {
    public static void parse(String message, Ui ui, TaskList tasks, Storage storage) {
        try{
            String messageType = message.split(" ")[0];
            if(message.equals("list")) {
                ui.printTasks(tasks);
            } else if(messageType.equals("mark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                    && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                Task markTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                markTask.mark();
                ui.printMarkTask(markTask);
            } else if(messageType.equals("unmark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                    && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                Task unmarkTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                unmarkTask.unmark();
                storage.updateTask(tasks);
                ui.printUnmarkTask(unmarkTask);
            } else if(messageType.equals("todo")) {
                Task newTask = ToDo.create(message);
                tasks.addTask(newTask);
                storage.addTask(newTask);
                ui.printAddTask(newTask, tasks.size());
            } else if(messageType.equals("deadline")) {
                Task newTask = Deadline.create(message);
                tasks.addTask(newTask);
                storage.addTask(newTask);
                ui.printAddTask(newTask, tasks.size());
            } else if(messageType.equals("event")) {
                Task newTask = Event.create(message);
                tasks.addTask(newTask);
                storage.addTask(newTask);
                ui.printAddTask(newTask, tasks.size());
            } else if(messageType.equals("delete") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                    && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                Task removedTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                tasks.removeTask(Integer.parseInt(message.split(" ")[1]) - 1);
                storage.updateTask(tasks);
                ui.printRemoveTask(removedTask, tasks.size());
            } else {
                throw new InvalidCommandException();
            }
        } catch(InvalidToDoException e) {
            ui.printException(e);
        } catch(InvalidEventException e) {
            ui.printException(e);
        } catch(InvalidDeadlineException e) {
            ui.printException(e);
        } catch(InvalidCommandException e) {
            ui.printException(e);
        }
    }

    /**
     * Merthod to check whether a string is integer
     * 
     * @param str the string that wanted to be checked
     * @return whether the string is integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
