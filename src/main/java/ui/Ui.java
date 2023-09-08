package ui;

import java.util.Scanner;

import tasklist.TaskList;
import task.Task;

public class Ui {
    private Scanner scanner;
    final String SPACE = "------------------------------------"; // for spacing purposes
    String name = "Adam's Bot"; // name of bot

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();// remove trailing spaces and get use input
    }

    public void showError(String errorMessage) {
        System.out.println(SPACE);
        System.out.println(errorMessage);
        System.out.println(SPACE);
    }

    public void showWelcome() {
        System.out.println(SPACE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(SPACE);
    }

    public void showGoodbye() {
        System.out.println(SPACE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACE);
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(SPACE);

        // iterate through ArrayList to print tasks
        for (int i = 0; i < taskList.size(); i++) {
            int currentNumber = i + 1;
            System.out.println(currentNumber + ". " + taskList.get(currentNumber).toString());
        }
        System.out.println(SPACE);
    }

    public void showMarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    public void showUnmarkText(Task task) {
        System.out.println(SPACE);
        System.out.println("Nice! I've marked this task as undone:");
        System.out.println("  " + task.toString());
        System.out.println(SPACE);
    }

    public void showAddText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }


    public void showDeleteText(Task task, int size) {
        System.out.println(SPACE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(SPACE);
    }

    /**
     * Displays the tasks within the taskList that contains the key word.
     * If there isn't any task that contains the key word, display an error message.
     *
     * @param tasks The task list we are finding the key word from.
     * @param keyWord The key word that we are searching for in the task list.
     */
    public void showFindText(TaskList tasks, String keyWord) {
        boolean isKeyWordFound = false;
        int counter = 1;
        System.out.println(SPACE);

        // iterate through taskList to check if they contain the keyWord. If yes, print them.
        for (int i = 0; i < tasks.size(); i++) {
            int currentNumber = i + 1;
            Task task = tasks.get(currentNumber);
            boolean hasKeyWord = task.findKeyWord(keyWord);

            if (hasKeyWord) {
                isKeyWordFound = true;
                System.out.println(counter + ". " + task.toString());
                counter = counter + 1;
            }
        }

        if (!isKeyWordFound) {
            System.out.println("☹ OOPS!!! The key word you are finding for is not present.");
        }
        System.out.println(SPACE);
    }
}

