package joi;

import java.util.Scanner;

import joi.utils.InvalidInputException;
import joi.utils.InvalidCommandException;
import joi.utils.Task;
import joi.utils.TaskList;

public class Joi {
    private static final String NAME = "Joi";
    private boolean isRunning;
    private final Scanner sc;
    private final TaskList taskList;

    // constructor for Duke
    public Joi() {
        this.isRunning = true;
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    // the event loop
    public void run() throws InvalidInputException, InvalidCommandException {
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

            } else if (input.startsWith("event") || input.startsWith("todo") || input.startsWith("deadline")){
                Task newTask;
                try {
                    newTask = Task.parseInputAsTask(input);
                    this.taskList.addTask(newTask);

                } catch (InvalidCommandException e) {
                    System.err.println("Cannot create a valid task.");
                    throw(e);
                }

            } else {
                throw new InvalidInputException(input);
            }
        }
    }

    private void greeting() {
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?\n");
    }

    private String getUserInput() {
        return this.sc.nextLine();
    }
}
