package duke;

import java.util.ArrayList;
public class Ui {

    public void welcomeMessage() {
        System.out.println("WEEWOOWEEWOO WELCOME! I'm Siren");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    public static void print(String string) {
        System.out.println(string);
    }
    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void goodbyeMessage() {
        System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
    }

    public static void showExceptionError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public static void savedFileFound() {
        horizontalLine();
        System.out.println("BLINGBLING! You have a saved file! Displaying the contents to you (if any)!");
    }

    public static void savedFileNotFound() {
        horizontalLine();
        System.out.println("OH NO! I couldn't find a \"duke.txt\" file in your data directory, "
                + "I'll be creating one for you!");
        horizontalLine();
    }

    public static void directoryNotFound() {
        horizontalLine();
        System.out.println("OH NO! I couldn't find a \"data\" directory in your project root directory, "
                + "I'll be creating one for you!");
    }

    public static void deleteTaskOutput(String string, int arraySize) {
        System.out.println("ALRIGHTY! I've removed this task:");
        System.out.println(string);
        System.out.println("Now you have " + arraySize + " tasks in the list.");
    }

    public static void addedTaskOutput(TaskList taskList) {
        System.out.println("DINGDONG GOT IT! I've added this task:");
        System.out.println(taskList.taskArray.get(taskList.taskArray.size() - 1));
        System.out.println("Now you have " + taskList.taskArray.size() + " tasks in the list.");
    }

    public static void taskAlreadyMarked(boolean marked) {
        if (marked) {
            System.out.println("WEEYA! Task was already marked as done!");
        } else {
            System.out.println("GOTCHYA! I've marked this task as done!");
        }
    }

    public static void taskAlreadyNotMarked(boolean notMarked) {
        if (notMarked) {
            System.out.println("OOPSIE! Task was already marked as not done!");
        } else {
            System.out.println("HONKHONK! I've marked this task as not done yet!");
        }
    }

    public static void taskListEmpty() {
        System.out.println("HEYYYYYYYY! There's nothing to show in your list!");
    }

    public static void taskListNotEmpty(ArrayList<Task> taskArray) {
        System.out.println("WHEET WHEET WHEET! Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println(i + 1 + "." + taskArray.get(i));
        }
    }
}
