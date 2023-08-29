package duke;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private final String lineSeparator = "____________________________________________________________";

    public void showWelcomeMessage() {
        String chatbotName = "Gobble Gobble";
        System.out.println(this.lineSeparator + "\n" + "Hello! I'm " + chatbotName + "\n" +
                "What can I do for you?" + "\n" + this.lineSeparator);
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(lineSeparator + "\n" + message + "\n" + lineSeparator);
    }

    public void showMarkMessage(Task task) {
        System.out.println(this.lineSeparator + "\n" + "Nice! I've marked this task as done:" + "\n" +
                task + "\n" + this.lineSeparator);
    }

    public void showUnmarkMessage(Task task) {
        System.out.println(this.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                task + "\n" + this.lineSeparator);
    }

    public void showDeleteMessage(Task task, int size) {
        System.out.println(this.lineSeparator + "\n" + "Noted. I've removed this task:" + "\n"
                + task.getDescription() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + this.lineSeparator);
    }

    public void showAddTaskMessage(Task task, int size) {
        System.out.println(this.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + this.lineSeparator);
    }

    public void showTasks(TaskList taskList) {
        System.out.println(this.lineSeparator);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }
}
