import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final String line = "____________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Fong!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void explainException(DukeException e) {
        System.out.println(line);
        System.out.println(e.getMessage());
        System.out.println(line);
    }

    public void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            System.out.println(index + "." + this.tasks.get(i).toString());
        }
        System.out.println(line);
    }

    public void handleTask(String task) throws DukeException {
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            String[] preprocessedTask = task.split("todo ");

            if (preprocessedTask.length <= 1) {
                throw new DukeException("Please enter a valid todo description.");
            }


            Todo nextTodo = new Todo(preprocessedTask[1]);
            this.tasks.add(nextTodo);
            nextTaskString = nextTodo.toString();
        } else if (task.startsWith("deadline")) {
            String[] temp = task.split("deadline ");

            if (temp.length <= 1) {
                throw new DukeException("You are missing both a valid deadline description and a deadline. Please enter a valid deadline description and deadline.");
            }

            String[] preprocessedTask = preprocessTask(temp[1]);

            if (preprocessedTask.length <= 1) {
                throw new DukeException("You are missing either a valid deadline description or deadline. Please enter a valid deadline description or deadline.");
            }

            Deadline nextDeadline = new Deadline(preprocessedTask[0], preprocessedTask[1]);
            this.tasks.add(nextDeadline);
            nextTaskString = nextDeadline.toString();
        } else if (task.startsWith("event")) {
            String[] temp = task.split("event ");

            if (temp.length <= 1) {
                throw new DukeException("You are missing both a valid event description and a start and end time. Please enter a valid event description and start and end time.");
            }

            String[] preprocessedTask = preprocessTask(temp[1]);

            if (preprocessedTask.length <= 2) {
                throw new DukeException("You are missing either a valid event description or start and end time. Please enter a valid event description or start and end time.");
            }

            Event nextEvent = new Event(preprocessedTask[0], preprocessedTask[1], preprocessedTask[2]);
            this.tasks.add(nextEvent);
            nextTaskString = nextEvent.toString();
        } else if (task.startsWith("delete")) {
            handleDelete(task);
            return;
        } else {
            throw new DukeException("You did not enter a valid task. Please enter either a todo, deadline or event.");
        }

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTaskString);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private String[] preprocessTask(String temp) throws DukeException {
        String[] preprocessedTask = temp.split(" /by | /from | /to ");

        return preprocessedTask;
    }

    public void handleDelete(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to delete.");
        }

        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1; // .parseInt throws a NumberFormatExeception if index does not contain a parsable integer
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to delete.");
        }

        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        Task taskToRemove = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);

        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void handleMark(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to mark or unmark.");
        }

        String action = split[0];
        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1; // .parseInt throws a NumberFormatExeception if index does not contain a parsable integer
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to mark or unmark.");
        }


        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        if (action.equals("mark")) {
            this.markTask(taskIndex);
        } else if (action.equals("unmark")) {
            this.unmarkTask(taskIndex);
        }
    }

    public void markTask(int taskIndex) {
        this.tasks.get(taskIndex).doTask();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    public void unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).undoTask();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    public void acceptTasks() {
        Scanner scanner = new Scanner(System.in);
        String nextTask = scanner.nextLine();

        while (!nextTask.equals("bye")) {
            if (nextTask.equals("list")) {
                this.printTasks();
            } else if (nextTask.startsWith("mark") || nextTask.startsWith("unmark")) {
                try {
                    this.handleMark(nextTask);
                } catch (DukeException e) {
                    this.explainException(e);
                }
            } else {
                try {
                    this.handleTask(nextTask);
                } catch (DukeException e) {
                    this.explainException(e);
                }
            }

            nextTask = scanner.nextLine();
        }

        scanner.close();

        this.bye();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greet();
        chatBot.acceptTasks();
    }
}
