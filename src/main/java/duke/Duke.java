package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.utils.Task;
public class Duke {
    private static final String NAME = "Joi";
    private boolean isRunning;
    private final Scanner sc;
    private final ArrayList<Task> itemList;

    // constructor for Duke
    public Duke() {
        this.isRunning = true;
        this.sc = new Scanner(System.in);
        this.itemList = new ArrayList<>();
    }

    // the event loop
    private void run() {
        this.greeting();

        while (this.isRunning) {
            String input = this.getUserInput();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                this.isRunning = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < this.itemList.size(); i++) {
                    System.out.println((i+1) + ". " + this.itemList.get(i));
                }
                System.out.println();
            } else if (input.startsWith("mark")){
                int taskIdx = Integer.parseInt(input.substring(5)) - 1;

                if (taskIdx < itemList.size()) {
                    itemList.get(taskIdx).setDone();
                }

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(itemList.get(taskIdx) + "\n");
            } else if (input.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(input.substring(7)) - 1;

                if (taskIdx < itemList.size()) {
                    itemList.get(taskIdx).setNotDone();
                }

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(itemList.get(taskIdx) + "\n");
            } else {
                Task newTask = new Task(input);

                itemList.add((newTask));
                System.out.println("added: " + input + "\n");
            }
        }
    }

    private void greeting() {
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?\n");
    }

    private String getUserInput() {
        return this.sc.nextLine();
    }

    public static void main(String[] args) {
        Duke joi = new Duke();
        joi.run();
    }
}
