import java.util.Scanner;

public class Ui {
    static String horizontalLine = "--------------------";
    String chatbotName;
    Scanner sc;

    Ui(String chatbotName) {
        this.chatbotName = chatbotName;
        this.sc = new Scanner(System.in);
    }

    String nextLine() {
        return sc.nextLine();
    }

    void greetUser() {
        printMessage("Hello I'm " + chatbotName + "\nWhat can I do for you?");
    }

    void farewellUser() {
        printMessage("Bye. Hope to see you again soon!");
    }

    void printMessage(String text) {
        // prints text with horizontal lines above and below it
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }


    void printInvalidCommandTypeExceptionResponse() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    void printSuccessfulDeleteResponse(Task taskToDelete, int numTotalTasks) {
        printMessage("Noted. I've removed this task:\n  " + taskToDelete.toString() + "\nNow you have " + numTotalTasks + " task(s) in the list.");
    }

    void printSuccessfulMarkResponse(Task taskToMark) {
        printMessage("Nice! I've marked this task as done:\n  " + taskToMark);
    }

    void printSuccessfulUnmarkResponse(Task taskToUnmark) {
        printMessage("OK, I've marked this task as not done yet:\n[ ] " + taskToUnmark);
    }

    void printSuccessfulAddTaskResponse(Task newTask, int numTotalTasks) {
        printMessage("Got it. I've added this task:\n  " + newTask + "\nNow you have " + numTotalTasks + " task(s) in the list.");
    }

    void printInvalidMarkOrUnmarkResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a mark/unmark must be between 1 and " + numTotalTasks + ".");
    }

    void printEmptyTodoDescriptionResponse() {
        printMessage("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    void printInvalidDeleteResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a delete must be between 1 and " + numTotalTasks + ".");
    }

    void printFileNotFoundResponse(String filePath) {
        printMessage("No previous datafile found in " + filePath + ". Creating new task list for you!");
    }

    void printNoDataResponse() {
        /*
        Prints message for when the file exists in the user's data directory. However, no data is found there.
         */
        printMessage("Datafile located. However, it is empty. Creating a new task list for you!");
    }

    void printSaveFailResponse(String savePath) {
        System.out.println("Failed to write to " + savePath);
    }
}
