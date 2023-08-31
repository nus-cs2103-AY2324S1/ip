import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

/**
 * Chatbot class
 */
public class Duke {
    // Attribute
    Ui ui;
    Storage storage;
    TaskList tasks;

    // Constructor

    /**
     * The constructor of Duke
     * 
     * @param directoryName the name of directory
     * @param fileName the name of the file
     */
    public Duke(String directoryName, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(directoryName, fileName);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            this.tasks = new TaskList(new ArrayList<>());
            this.ui.printException(e);
        }
    }

    // Method

    /**
     * The main method
     * 
     * @param args the input argument
     * @throws IOException
     */
    public static void main(String[] args) {
        new Duke("./data", "storage.txt").run();
    }

    /**
     * Runs the program
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printIntro();
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            try{
                if(message.equals("list")) {
                    ui.printTasks(tasks);
                } else if(message.split(" ")[0].equals("mark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    Task markTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                    markTask.mark();
                    ui.printMarkTask(markTask);
                } else if(message.split(" ")[0].equals("unmark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    Task unmarkTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                    unmarkTask.unmark();
                    storage.updateTask(tasks);
                    ui.printUnmarkTask(unmarkTask);
                } else if(ToDo.isToDo(message)) {
                    Task newTask = new ToDo(message.substring(5));
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    ui.printAddTask(newTask, tasks.size());
                } else if(Deadline.isDeadline(message)) {
                    String name = message.substring(9, message.indexOf("/by "));
                    String deadlineString = message.substring(message.indexOf("/by ") + 4);
                    LocalDate deadlineDate = LocalDate.parse(deadlineString);
                    Task newTask = new Deadline(name, deadlineDate);
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    ui.printAddTask(newTask, tasks.size());
                } else if(Event.isEvent(message)) {
                    String name = message.substring(6, message.indexOf("/from "));
                    int fromIndex = message.indexOf("/from ");
                    int toIndex = message.indexOf(" /to ", fromIndex);
                    String fromString = message.substring(fromIndex + 6, toIndex);
                    String toString = message.substring(toIndex + 5);
                    LocalDate fromDate = LocalDate.parse(fromString);
                    LocalDate toDate = LocalDate.parse(toString);
                    Task newTask = new Event(name, fromDate, toDate);
                    tasks.addTask(newTask);
                    storage.addTask(newTask);
                    ui.printAddTask(newTask, tasks.size());
                } else if(message.split(" ")[0].equals("delete") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= tasks.size() && Integer.parseInt(message.split(" ")[1]) > 0) {
                    Task removedTask = tasks.getTask(tasks.size() - 1);
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
            message = scanner.nextLine();
        }
        ui.printEnd();
        scanner.close();
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