package didier;

import didier.exception.DidierException;
import didier.exception.TaskNumberException;
import didier.task.Task;
import didier.task.TaskList;

import java.util.Scanner;

public class UI {

    private Scanner scanner;
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    public void botPrintMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line break to the standard output.
     */
    public void botPrintBr() {
        String lineBreak = "-----------------------------------------------------------------------------";
        botPrintMessage(lineBreak);
    }

    public void botPrintTaskMarkedDone(Task task, boolean isDone) {
        if (isDone) {
            botPrintMessage("Okay! I've marked the following didier.task as done:");
        } else {
            botPrintMessage("Okay! I've marked the following didier.task as undone:");
        }
        botPrintMessage(task.toString());
    }

    public void botPrintTaskList(TaskList taskList) throws TaskNumberException {
        botPrintMessage("The tasks in your list are as follows:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            botPrintMessage(String.format("%d.%s", i, taskList.getTask(i)));
        }
    }

    public void botPrintTaskRemoved(Task task, int size) {
        botPrintMessage("Okay! I've removed this didier.task:");
        botPrintMessage(task.toString());
        botPrintMessage(String.format("There are now %d tasks in your list", size));
    }

    public void botGreet() {
        botPrintBr();
        botPrintMessage("Greetings user, I'm didier.Didier. How can I help you?");
        botPrintBr();
    }

    public void botPrintTaskAdded(Task task, int size) {
        botPrintMessage("Okay! I've added your didier.task:");
        botPrintMessage(task.toString());
        botPrintMessage(String.format("There are now %d tasks in your list", size));
    }

    public void botEndCommand(int size) {
        botPrintBr();
    }

    public void botPrintError(DidierException exception) {
        botPrintMessage(exception.getMessage() + "Please try again.");
    }

    public void botGoodBye() {
        botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        botPrintBr();
        this.scanner.close();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
