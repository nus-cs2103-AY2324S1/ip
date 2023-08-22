package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.utils.Task;
import duke.utils.TaskList;

public class Duke {
    private static final String NAME = "Joi";
    private boolean isRunning;
    private final Scanner sc;
    private final TaskList taskList;

    // constructor for Duke
    public Duke() {
        this.isRunning = true;
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    // the event loop
    public void run() {
        this.greeting();

        while (this.isRunning) {
            String input = this.getUserInput();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                this.isRunning = false;

            } else if (input.equals("list")) {
                this.taskList.listTasks();

            } else if (input.startsWith("mark")){
                int taskIdx = Integer.parseInt(input.substring(5)) - 1;
                this.taskList.markAsDone(taskIdx);

            } else if (input.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(input.substring(7)) - 1;
                this.taskList.unmarkAsDone(taskIdx);

            } else {
                Task newTask = Task.parseInputAsTask(input);
                if (newTask != null) {
                    this.taskList.addTask(newTask);
                }
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
