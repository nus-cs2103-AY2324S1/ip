package duke;



import duke.Exception.DukeException;
import duke.task.Task;

///**
// * The class that handles the UI aspects of the chatbot like the messages
// * that will be printed.
// */
//public class Ui {
//
//    /**
//     * Prints the welcome message.
//     */
//    public void printWelcomeMessage() {
//        printHorizontalLine();
//        System.out.println("\t " + "Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n"
//                + "\t " + "What can I do for you?");
//        printHorizontalLine();
//    }
//
//    /**
//     * Prints the goodbye message.
//     */
//    public void printGoodByeMessage() {
//        printHorizontalLine();
//        System.out.println("\t " + "Bye! Hope to see you again soon!");
//        printHorizontalLine();
//    }
//
//    /**
//     * Prints the task as being marked as done.
//     * @param index the index of the task that is to be marked.
//     * @param tasks the TaskList being used.
//     */
//    public void printMarkTasksAsDone(int index, TaskList tasks) {
//        printHorizontalLine();
//        System.out.println("\tGreat! I've marked this task as done:");
//        System.out.println("\t" + (index + 1) + "." + tasks.getTask(index).toString());
//        printHorizontalLine();
//
//    }
//
//    /**
//     * Prints the task as being marked as not done yet.
//     * @param index the index of the task is to be marked as not done.
//     * @param tasks the TaskList being used.
//     */
//
//    public void printMarkTasksAsNotDone(int index, TaskList tasks) {
//        printHorizontalLine();
//        System.out.println("\tOk! I've marked this task as not done yet:");
//        System.out.println("\t" + (index + 1) + "." + tasks.getTask(index).toString());
//        printHorizontalLine();
//
//    }
//
//    /**
//     * Prints the TaskList.
//     * @param tasks the TaskList being used.
//     * @throws DukeException throws a duke exception.
//     */
//    public void printListMessage(TaskList tasks) throws DukeException {
//        printHorizontalLine();
//        tasks.printTasks();
//        printHorizontalLine();
//    }
//
//    /**
//     * Prints message that the task is added.
//     */
//
//    public void printAddedTask() {
//        System.out.println("\tNo problem! I have added this task:");
//    }
//
//    /**
//     * Prints the horizontal line.
//     */
//    public void printHorizontalLine() {
//        System.out.println("    __________________________________________________________________");
//    }
//
//    /**
//     * Prints that a task has been deleted.
//     * @param tasks the TaskList being used.
//     * @param element the Task that is to be deleted.
//     * @throws DukeException throws a duke exception.
//     */
//    public void printDeleteTasks(TaskList tasks, Task element) throws DukeException {
//        printHorizontalLine();
//        System.out.println("\tOkie I've removed this task:\n\t" + element.toString());
//        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
//        printHorizontalLine();
//    }
//
//    /**
//     * Prints that a task has been added.
//     * @param tasks the TaskList being used.
//     * @param task the task that is to be added.
//     */
//
//    public void printAddTaskToList(TaskList tasks, Task task) {
//        printHorizontalLine();
//        printAddedTask();
//        System.out.println("\t" + task.toString());
//        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list");
//        printHorizontalLine();
//    }
//
//    /**
//     * * prints what tasks have been found.
//     * @param foundTasks the array used to store the tasks that match.
//     */
//    public void printFindTask(TaskList foundTasks) {
//        System.out.println("\tSure, I can do that! What are buddies for afterall?");
//        System.out.println("\tHere are the matching tasks in your list:");
//        for (int i = 0; i < foundTasks.getSize(); i++) {
//            Task task = foundTasks.getTask(i);
//            System.out.println("\t" + (i + 1) + ". " + task.toString());
//        }
//    }
//    /**
//     * Prints error message of any Exceptions.
//     * @param e Error message of exception
//     */
//    public void printError(String e) {
//        System.out.println(e);
//    }
//
//
//
//}

public class Ui {

    /**
     * Prints the welcome message.
     */
    public String printWelcomeMessage() {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n");
        message.append("What can I do for you?\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * Prints the goodbye message.
     */
    public String printGoodByeMessage() {
        printHorizontalLine();
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Bye! Hope to see you again soon!\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * Prints the task as being marked as done.
     * @param index the index of the task that is to be marked.
     * @param tasks the TaskList being used.
     */
    public String printMarkTasksAsDone(int index, TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Great! I've marked this task as done:\n");
        message.append(index).append(".").append(tasks.getTask(index - 1).toString()).append("\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * Prints the task as being marked as not done yet.
     * @param index the index of the task is to be marked as not done.
     * @param tasks the TaskList being used.
     */

    public String printMarkTasksAsNotDone(int index, TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Ok! I've marked this task as not done yet:\n");
        message.append(index).append(".").append(tasks.getTask(index - 1).toString()).append("\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();

    }

    /**
     * Prints the TaskList.
     * @param tasks the TaskList being used.
     * @throws DukeException throws a duke exception.
     */
    public String printListMessage(TaskList tasks) throws DukeException {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append(tasks.printTasks()).append("\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * Prints message that the task is added.
     */

    public String printAddedTask() {
        return "\tNo problem! I have added this task:";
    }

    /**
     * Prints the horizontal line.
     */
    public String printHorizontalLine() {
        return "    __________________________________________________________________";
    }

    /**
     * Prints that a task has been deleted.
     * @param tasks the TaskList being used.
     * @param element the Task that is to be deleted.
     * @throws DukeException throws a duke exception.
     */
    public String printDeleteTasks(TaskList tasks, Task element) throws DukeException {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Okie I've removed this task:\n").append(element.toString()).append("\n");
        message.append("Now you have ").append(tasks.getSize()).append(" tasks in the list.\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * Prints that a task has been added.
     * @param tasks the TaskList being used.
     * @param task the task that is to be added.
     */

    public String printAddTaskToList(TaskList tasks, Task task) {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("No problem! I have added this task:\n").append(task.toString()).append("\n");
        message.append("Now you have ").append(tasks.getSize()).append(" tasks in the list.\n");
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
     * * prints what tasks have been found.
     * @param foundTasks the array used to store the tasks that match.
     */
    public String printFindTask(TaskList foundTasks) {
        StringBuilder message = new StringBuilder();
        message.append(printHorizontalLine()).append("\n");
        message.append("Sure, I can do that! What are buddies for after all?\n");
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.getSize(); i++) {
            Task task = foundTasks.getTask(i);
            message.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        message.append(printHorizontalLine()).append("\n");
        return message.toString();
    }

    /**
          * Prints error message of any Exceptions.
          * @param e Error message of exception
          */
    public void printError(String e) {
        System.out.println(e);
    }



}
