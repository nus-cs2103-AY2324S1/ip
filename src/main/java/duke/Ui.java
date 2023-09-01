package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    public void greet() {
        display("Hello! I'm Max\n" + "What can I do for you?");
    }

    public void exit() {
        display("Bye. Hope to see you again soon!");
    }

    public void getUserInput(TaskList taskList, Storage storage) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        taskList = storage.readFromFile();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                display("Here are the tasks in your list:\n" + taskList.toString());
            } else if (userInput.startsWith("mark")) {
                // get index by splitting user input and get task at that index from list
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("marked");
                }

                Task toBeMarked = taskList.getTaskAt(index - 1);
                toBeMarked.mark();
                display("Nice! I've marked this task as done:\n" + toBeMarked.toString());
            } else if (userInput.startsWith("unmark")) {
                // get index by splitting user input and get task at that index from list
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("unmarked");
                }

                Task toBeUnmarked = taskList.getTaskAt(index - 1);
                toBeUnmarked.unmark();
                display("OK, I've marked this task as not done yet:\n" + toBeUnmarked.toString());
            } else if (userInput.startsWith("delete")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("deleted");
                }

                Task toBeDeleted = taskList.getTaskAt(index - 1);
                taskList.deleteTaskAt(index - 1);
                display("Noted. I've removed this task:\n" + toBeDeleted.toString()
                        + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.");
            } else if (userInput.startsWith("find")) {
                TaskList filtered = parser.getTaskList(userInput, taskList);
                display("Here are the matching tasks in your list:\n" + filtered.toString());
            } else {
                Task add = parser.getTask(userInput);
                try {
                    taskList.addToList(add);
                    display("Got it. I've added this task:\n" + add.toString()
                            + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.");
                } catch (NullPointerException e) {
                    throw new DukeException("OOPS!!! Could not add task to the list");
                }
            }

            storage.writeToFile(taskList);
        }
    }

    public static void display(String message) {
        lines();
        System.out.println(message);
        lines();
    }

    public static void lines() {
        System.out.println("");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
