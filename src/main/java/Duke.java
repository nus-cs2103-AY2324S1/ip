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
    public void processInput() throws InvalidCommandException, InvalidVarException {
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
            processDeadline(input.substring(9));
        } else {
            throw new InvalidCommandException();
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

    public void markTask(String input) throws InvalidVarException {
        int number = -1;
        try {
            number = Integer.parseInt(input.substring(5));
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        try {
            list[number - 1].markDone();
        } catch (Exception e) {
            throw new InvalidVarException("Task not found");
        }
    }

    public void unmarkTask(String input) throws InvalidVarException {
        int number = -1;
        try {
            number = Integer.parseInt(input.substring(7));
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        try {
            list[number - 1].markUndone();
        } catch (Exception e) {
            throw new InvalidVarException("Task not found");
        }
    }

    public void processToDo(String input) throws InvalidVarException {
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
            try {
                luke.processInput();
            }
            catch (InvalidCommandException e) {
                System.out.println("Unknown command given!!!");
            }
            catch (InvalidVarException e) {
                System.out.println("Invalid input; " + e.getMessage());
            }
        }
    }
}
