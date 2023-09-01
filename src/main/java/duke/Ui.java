package duke;

import java.util.Scanner;

public class Ui {
    private static Scanner prompt = new Scanner(System.in);

    public static void greet(TaskList tasks) throws DukeException {
        System.out.println("Hello! I'm Oranges.");
        System.out.println("What can I do for you?");
        echo(tasks);
    }

    public static void echo(TaskList tasks) throws DukeException {
        Parser parser = new Parser(tasks);
        String promptText = prompt.nextLine();
        if (promptText.equals("bye")) {
            exit();
        }
        else if (promptText.equals("list")) {
            if (tasks.size() == 0) {
                System.out.println("Your task list is empty!");
            }
            list(tasks);
        }
        else if (promptText.startsWith("todo") || promptText.startsWith("deadline") || promptText.startsWith("event")) {
            parser.createTask(promptText);
        }
        else if (promptText.startsWith("mark") || promptText.startsWith("unmark")){
            parser.markTask(promptText);
        }
        else if (promptText.startsWith("delete")) {
            deleteTask(tasks,Integer.valueOf(promptText.substring(7)) - 1);
        }
        else if (promptText.startsWith("find")) {
            parser.findTask(promptText);
        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        echo(tasks);
    }

    public static void deleteTask(TaskList tasks, int i) throws DukeException {
        try {
            tasks.delete(tasks.get(i));
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! This task doesn't exist!");
        }
    }



    public static void list(TaskList tasks) throws DukeException {
        tasks.list();
        echo(tasks);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public void showLoadingError() {
        System.out.println("OOPS! Loading error.");
    }
}
