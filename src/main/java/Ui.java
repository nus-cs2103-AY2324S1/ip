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
        printMessage("Hello I'm " + chatbotName + "\n\nPlease call for `help` if you need anything!");
    }

    void promptUser() {
        printMessage("Input your command below:");
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

    void printAllTasks(TaskList tasks) {
        String allTasksString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            Task newTask = tasks.getAllTasks().get(i);
            allTasksString += (i + 1) + "." + newTask.toString() + "\n";
        }
        printMessage(allTasksString);
    }

    void printInputSpecifications(String fileSavePath) {
        String message = "Please enter your commands:\n";
        message += "Adding Tasks:\n";
        message += "1. Todo tasks: todo <description>\n";
        message += "2. Deadline tasks: deadline <description> /by <date>\n";
        message += "3. Event tasks: event <description> /from <date> /to <date>\n\n";

        message += "Marking and Unmarking Tasks:\n";
        message += "1. Marking tasks as completed: mark <integer>\n";
        message += "2. Unmarking tasks are uncompleted: unmark <integer>\n\n";

        message += "Deleting Tasks:\n";
        message += "1. delete <integer>\n\n";

        message += "Others:\n";
        message += "1. Quit: bye\n";
        message += "2. Manual: help\n\n";

        message += "FYI: Your data is saved locally in: " + fileSavePath;
        printMessage(message);
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
        printMessage("OK, I've marked this task as not done yet:\n" + taskToUnmark);
    }

    void printSuccessfulAddTaskResponse(Task newTask, int numTotalTasks) {
        printMessage("Got it. I've added this task:\n  " + newTask + "\nNow you have " + numTotalTasks + " task(s) in the list.");
    }

    void printInvalidMarkOrUnmarkResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a mark/unmark must be between 1 and " + numTotalTasks + ".");
    }

    void printEmptyByExceptionResponse() {
        printMessage("By clause cannot be empty!");
    }

    void printEmptyToExceptionResponse() {
        printMessage("To clause cannot be empty!");
    }

    void printEmptyFromExceptionResponse() {
        printMessage("From clause cannot be empty!");
    }

    void printInvalidByExceptionResponse() {
        printMessage("By clause must be in the following format: YYYY-MM-DD");
    }

    void printInvalidToExceptionResponse() {
        printMessage("To clause must be in the following format: YYYY-MM-DD");
    }

    void printInvalidFromExceptionResponse() {
        printMessage("From clause must be in the following format: YYYY-MM-DD");
    }

    void printEmptyTodoDescriptionResponse() {
        printMessage("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    void printInvalidDeleteResponse(int numTotalTasks) {
        printMessage("☹ OOPS!!! The description of a delete must be between 1 and " + numTotalTasks + ".");
    }

    void printNotAnIntegerExceptionResponse() {
        printMessage("Please enter a valid integer (E.g. mark 1, unmark 8, delete 3)");
    }

    void printFileNotFoundResponse(String filePath) {
        printMessage("No previous datafile found in " + filePath + ". Creating new task list for you!");
    }

    void printTaskNotFoundExceptionResponse() {
        printMessage("☹ OOPS!!! No such task exists in your task list");
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
