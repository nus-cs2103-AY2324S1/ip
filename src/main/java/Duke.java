import java.util.*;
public class Duke {
    Scanner reader = new Scanner(System.in);
    Task[] list = new Task[100];
    int counter = 0;
    boolean shutdownCommand = false;
    String logo = " _           _        \n"
            + "| |    _   _| | _____ \n"
            + "| |   | | | | |/ / _ \\\n"
            + "| |___| |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public void introDuke() {
        System.out.println("Hi, I'm \n" + logo);
    }
    public void shutdownDuke() {
        System.out.println("Ok byeee\n");
        shutdownCommand = true;
    }
    public void processInput() {
        String input = reader.nextLine();
        if (input.equals("bye")) {
            shutdownDuke();
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark ")) {
            markTask(input);
        } else if (input.startsWith("unmark ")) {
            unmarkTask(input);
        } else if (input.startsWith("todo ")) {
            processToDo(input.substring(5));
        } else if (input.startsWith("event ")) {
            processEvent(input.substring(6));
        } else if (input.startsWith("deadline ")) {
            processDeadline(input.substring(8));
        } else {
            System.out.println("Error, unknown command!?!");
        }
    }

    public void listTasks() {
        if (counter == 0) {
            System.out.println("No list, silly!");
        } else {
            System.out.println("Here's the list so far.");
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
        }
    }

    public void markTask(String input) {
        int number = Integer.parseInt(input.substring(5));
        list[number - 1].markDone();
    }

    public void unmarkTask(String input) {
        int number = Integer.parseInt(input.substring(7));
        list[number - 1].markUndone();
    }

    public void processToDo(String input) {
        ToDo task = new ToDo(input);
        addTask(task);
    }

    public void processEvent(String input) {
        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        String name = input.substring(0, split1 - 1);
        String start = input.substring(split1 + 6, split2 - 1);
        String end = input.substring(split2 + 4);
        Event task = new Event(name, start, end);
        addTask(task);
    }

    public void processDeadline(String input) {
        int split = input.indexOf("/by");
        String name = input.substring(0, split - 1);
        String deadline = input.substring(split + 4);
        Deadline task = new Deadline(name, deadline);
        addTask(task);
    }

    public void addTask(Task task) {
        list[counter] = task;
        counter += 1;
        System.out.println("added: " + task.toString());
        System.out.println("current task count: " + counter);
    }

    public static void main(String[] args) {
        Duke luke = new Duke();
        luke.introDuke();
        while (!luke.shutdownCommand) {
            luke.processInput();
        }
    }
}
