// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
public class Ui {
    private static final String separators = "____________________________________________________________";
    String text1 = " Hello! I'm Novo\n" + " What can I do for you?\n" + separators + "\n";
    String text2 = " Bye. Hope to see you again soon!";

    public void displayWelcomeText() {
        System.out.println(separators + "\n" + text1);
    }

    public void displayGoodbyeText() {
        System.out.println(separators + "\n" + text2 + "\n" + separators);
    }

    public void showErrorMessage(String message) {
        System.out.println(separators + "\n" + message + "\n" + separators);
    }

    public void displayDeleteTask(Task deletedTask, int remainingNumberTasks) {
        System.out.println(separators);
        System.out.println("Noted. I've removed this task:" + "\n" + deletedTask.toString());
        System.out.println("Now you have " + remainingNumberTasks + " tasks in the list.");
        System.out.println(separators);
    }

    public void displayAddedTask(Task newTask, int newNumber) {
        System.out.println(separators);
        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString());
        System.out.println("Now you have " + newNumber + " tasks in the list.");
        System.out.println(separators);
    }

    public void displayTaskList(TaskList taskList) {
        System.out.println(separators);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.numTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
        System.out.println(separators);
    }

    public void displayMarked(Task markedTask) {
        System.out.println(separators);
        System.out.println("Nice! I've marked this task as done:" + "\n" + markedTask.toString());
        System.out.println(separators);

    }

    public void displayUnmarked(Task markedTask) {
        System.out.println(separators);
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + markedTask.toString());
        System.out.println(separators);
    }

    public void printToDoException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printNoSuchElementException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    public void printEventException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printDeadlineException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    public void printMarkException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The task to mark must be specified.");
        System.out.println("____________________________________________________________");
    }

    public void printUnmarkException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The task to unmark must be specified.");
        System.out.println("____________________________________________________________");
    }

    public void printDeadlineFormatException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! Enter in the format: deadline (task) /by dd/MM/yyyy HHmm");
        System.out.println("____________________________________________________________");
    }

    public void printEventFormatException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! Enter in the format: \n event (task) /from yyyy/MM/dd HHmm /to yyyy/MM/dd HHmm");
        System.out.println("____________________________________________________________");
    }
}