package duke;

import java.util.ArrayList;

import duke.tasks.TaskList;

import duke.tasks.Task;

public class UI {
    public String name;

    public UI(String name) {
        this.name = name;
    }

    public void welcome() {
        String name = "DukeKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?";
        printLine();
        System.out.println(welcome);
        printLine();
    }

    public void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printLine() {
        System.out.println("_".repeat(40));
    }

    public void markTask(Task task) {
        String markingTask = "Nice! I've marked this task as done:";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
    }

    public void unMarkTask(Task task) {
        String markingTask = "Nice! I have unmarked the task :";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
    }

    public void deleteTask(TaskList list, int taskNumber) {
        String deletingTask = "Noted. I've removed this task:";
        int taskInArray = list.size() - 1;
        Task removedTask = list.deleteTask(taskNumber);
        String numberOfTask = "Now you have " + taskInArray + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", deletingTask, removedTask, numberOfTask);
        printLine();
        System.out.println(output);
    }

    public void addTask(Task task, ArrayList<Task> list) {
        String addingTask = "Got it. I've added this task:";
        String numberOfTask = "Now you have " + list.size() + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", addingTask, task, numberOfTask);
        printLine();
        System.out.println(output);
    }

    public void noFile() {
        printLine();
        System.out.println("OOPS!!! There is no file to load.");
    }

    public void findTask() {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
    }
}
