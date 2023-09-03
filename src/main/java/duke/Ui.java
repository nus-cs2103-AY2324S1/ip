package duke;

import duke.Exception.DukeException;
import duke.task.Task;

public class Ui {

    public void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("\t " + "Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n" +
                "\t " + "What can I do for you?");
        printHorizontalLine();
    }

    public void printGoodByeMessage() {
        printHorizontalLine();
        System.out.println("\t " + "Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    public void printMarkTasksAsDone(int index, TaskList tasks) {
        printHorizontalLine();
        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t" + index + "." + tasks.getTask(index - 1).toString());
        printHorizontalLine();

    }


    public void printMarkTasksAsNotDone(int index, TaskList tasks) {
        printHorizontalLine();
        System.out.println("\tOk! I've marked this task as not done yet:");
        System.out.println("\t" + index + "." + tasks.getTask(index - 1).toString());
        printHorizontalLine();

    }

    public void printListMessage(TaskList tasks) throws DukeException {
            printHorizontalLine();
            tasks.printTasks();
            printHorizontalLine();
    }

    public void printAddedTask() {
        System.out.println("\tNo problem! I have added this task:");
    }

    public void printHorizontalLine() {
        System.out.println("    __________________________________________________________________");
    }

    public void printDeleteTasks(int pos, TaskList tasks, Task element) throws DukeException {
        printHorizontalLine();
        System.out.println("\tOkie I've removed this task:\n\t" + element.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
        printHorizontalLine();
    }

    public void printAddTaskToList(TaskList tasks, Task task) {
        printHorizontalLine();
        printAddedTask();
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list");
        printHorizontalLine();
    }





}
