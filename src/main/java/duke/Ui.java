package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    String logo = "    ___    _   ___   ________  __      ____      __________  ____  _   __\n"
            + "   /   |  / | / / | / / __ \\ \\/ /     / __ \\    /_  __/ __ \\/ __ \\/ | / /\n"
            + "  / /| | /  |/ /  |/ / / / /\\  /_____/ / / /_____/ / / /_/ / / / /  |/ / \n"
            + " / ___ |/ /|  / /|  / /_/ / / /_____/ /_/ /_____/ / / _, _/ /_/ / /|  /  \n"
            + "/_/  |_/_/ |_/_/ |_/\\____/ /_/      \\____/     /_/ /_/ |_|\\____/_/ |_/   \n";
    String horizontalLine = "__________________________________________________________________________";
    String byeMessage = "Bye. Hope to see you again soon!";

    public void showWelcome() {
        System.out.println(horizontalLine + logo + "Hello! I'm ANNOY-O-TRON!\nWhat can I do for you?\n"
                + horizontalLine);
    }
    public void showBye() {
        System.out.println(byeMessage);
    }
    public void showLine() {
        System.out.println(horizontalLine);
    }
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
    public void showError(String message) {
        System.out.println(message);
    }
    public void showAddedTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }
    public void showDeletedTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task);
            index++;
        }
    }
    public void showNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getListSize() + " tasks in the list.");
    }
    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }
}
