package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    private final String chatbotName = "Gobble Gobble";
    public final String lineSeparator = "____________________________________________________________";


    public void printWelcome() {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + this.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError(String message) {
        System.out.println(lineSeparator + "\n" + message + "\n" + lineSeparator);
    }

    public void mark(Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "Nice! I've marked this task as done:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    public void unmark(Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    public void delete(Task task, int size) {
        System.out.println(Duke.lineSeparator + "\n" + "Noted. I've removed this task:" + "\n"
                + task.getDescription() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    public void addTask(Task task, int size) {
        System.out.println(Duke.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    public void printList(ArrayList<Task> list) {
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, list.get(i).toString());
        }
    }
}
