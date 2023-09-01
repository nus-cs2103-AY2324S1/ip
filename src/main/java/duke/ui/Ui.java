package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String MESSAGE = LINE + "\n"
            + " Hello! I'm ChatBot\n"
            + " What can I do for you?\n"
            + LINE + "\n";

    public void greet() {
        System.out.println(MESSAGE);
    }

    public void taskPrint(Task input, int count) {
        System.out.println(LINE + "\n"
                + "Got it. I've added this task" + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE);
    }

    public void deletePrint(Task input, int count) {
        System.out.println(LINE + "\n"
                + "Noted. I've removed this task: " + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE);
    }

    public void markDonePrint(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: " + "\n" + task);
        System.out.println(LINE);
    }

    public void unmarkDonePrint(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet: " + "\n" + task);
        System.out.println(LINE);
    }

    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void listOfTasks(TaskList tasksList, int count) {
        System.out.println(LINE);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasksList.getTask(i));
        }
        System.out.println(LINE);
    }

    public void errorPrint(DukeException e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

}
