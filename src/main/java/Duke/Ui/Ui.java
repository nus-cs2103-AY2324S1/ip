package Duke.Ui;

import Duke.DukeException.DukeException;
import Duke.Task.Task;
import Duke.TaskList.TaskList;

public class Ui {
    private final String Border = "____________________________________________________________";
    public void printChat(String chat) {
        System.out.println(Border + "\n" + chat + "\n" + Border);
    }

    public void Hello() {
        printChat("Hello! I'm Chrainx\n" + " What can I do for you?" );
    }

    public void addTask(Task task, int numberOfTask) {
        printChat("Got it, I have added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list"
        );
    }

    public void deleteTask(Task task, int numberOfTask) {
        printChat("Noted, I have removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list"
        );
    }

    public void markAsDone(Task task) {
        printChat("You have marked this task as done\n" + task.toString());
    }

    public void markAsNotDone(Task task) {
        printChat("You have marked this task as not done\n" + task.toString());
    }

    public void bye() {
        printChat("Bye. Hope to see you again soon!\n" + "Wish You a wonderful day");
    }

    public void error(DukeException e) {
        printChat(e.toString());
    }

    public void listing(TaskList tasks) {
        String listOfTask = "Here are the list of your task:\n";
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            listOfTask = listOfTask + tasks.getTask(i).toString() + "\n";
        }
        printChat(listOfTask);
    }
}
