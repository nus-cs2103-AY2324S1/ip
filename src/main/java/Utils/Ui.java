package Utils;

import Exceptions.DukeException;
import Tasks.Task;

import java.util.List;

public class Ui {

    String greeting = "Hello! I'm Toothless.\n" +
            "What can I do for you today?\n" +
            "---------------------------------";
    String farewell = "Goodbye. Hope to see you soon!\n" +
            "---------------------------------";
    String addTask = "A new task has been added!\n ";

    public void greet() {
        System.out.println(greeting);
    }

    public void farewell() {
        System.out.println(farewell);
    }

    public void showDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showDateError() {
        System.out.println("Date cannot be recognised :( please input a valid date format yyyy-mm-dd !");
    }

    public void showGeneralError() {
        System.out.println("There has been an internal error. Please try again!");
    }

    public void showTaskAdded(String taskDescription) {
        System.out.println(addTask + taskDescription);
    }

    public void showNoTasks() {
        System.out.println("You have no tasks! Yay :)");
    }

    public void showAllTasks(List<String> tasksDescriptions) {
        System.out.println("Here's your list of tasks!\n");
        for (int i = 0; i < tasksDescriptions.size(); i++) {
            System.out.println((i + 1) + ": " + tasksDescriptions.get(i));
        }
    }

    public void showStatusChanged(String status) {
        System.out.println(status);
    }

    public void separator() {
        System.out.println("---------------------------------");
    }

}
