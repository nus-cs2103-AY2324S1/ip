
package duck;

import java.util.ArrayList;
import duck.exceptions.DuckException;
import duck.exceptions.IllegalDateFormatException;
import duck.task.Deadline;
import duck.task.Events;
import duck.task.Task;
import duck.task.TaskList;
import duck.task.ToDo;

/**
 * Deals in connecting with the user command.
 */
public class Parser {
    /**
     * Identifies the type of task.
     * @param command User's inputted text.
     * @return The type of task the user wants to do.
     */
    public String identify(String command) {
        String[] strings = command.split(" ");
        return strings[0];
    }

    /**
     * Parses and executes user command to manage tasks.
     * @param str User's inputed String (Command).
     * @param io IoHandler object for input and output
     * @param taskList List containing Tasks.
     * @param storage Acts as a storage object for saving tasks.
     * @return boolean value depicting whether application should run or should be exited.
     */

    public boolean parse(String str, IoHandler io, TaskList taskList, Storage storage) {
        String command = identify(str);
        try {
            switch (command) {
            case "deadline":
                Deadline tempDeadline = taskList.setDeadline(str.substring(8));
                taskList.addTask(tempDeadline);
                io.echoAdd(tempDeadline, taskList);
                storage.saveInFile(taskList);
                return true;
            case "event":
                Events tempEvent = taskList.setEvent(str.substring(5));
                taskList.addTask(tempEvent);
                io.echoAdd(tempEvent, taskList);
                storage.saveInFile(taskList);
                return true;
            case "todo":
                ToDo tempToDo = taskList.setToDo(str.substring(4));
                taskList.addTask(tempToDo);
                io.echoAdd(tempToDo, taskList);
                storage.saveInFile(taskList);
                return true;
            case "delete":
                Task tempDelete = taskList.deleteTask(str, taskList);
                storage.saveInFile(taskList);
                io.divider();
                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + tempDelete.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                io.divider();
                return true;
            case "mark":
                Task tempMark = taskList.setDone(str, taskList);
                io.divider();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tempMark);
                io.divider();
                return true;
            case "unmark":
                Task tempUnmark = taskList.setUndone(str, taskList);
                io.divider();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tempUnmark);
                io.divider();
                return true;
            case "list":
                io.display(taskList);
                return true;
            case "bye":
                io.exit();
                return false;
            case "find":
                ArrayList<Task> temp = taskList.find(str.substring(5));
                io.displaySearchResults(temp);
                return true;
            default:
                throw new DuckException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DuckException | IllegalDateFormatException e) {
            io.divider();
            System.out.println("ERROR : " + e.getMessage());
            io.divider();
        }
        return true;
    }

}
