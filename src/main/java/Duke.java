import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String line = "\n_____________________________________________________\n";
    private final ArrayList<Task> lst = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke();
        bot.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println(line);

        System.out.println("Hello! I'm Gerald_Bot\n" + "What can I do for you?");
        System.out.println(line);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    this.exit();
                    break;
                } else if (input.equals("list")) {
                    this.printList();
                } else if (input.startsWith("mark")) {

                    if (input.replaceAll("\\s", "").equals(input)) {
                        throw new DukeInvalidCommandException("mark");
                    }

                    String[] parsedString = input.split(" ");
                    try {
                        int num = Integer.parseInt(parsedString[1]);
                        if (num > lst.size() || num <= 0) {
                            throw new DukeInvalidIndexException(lst.size());
                        }
                        Task selectedTask = lst.get(num - 1);
                        this.markCompletion(selectedTask);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidIndexException(lst.size());
                    }
                } else if (input.startsWith("unmark")) {

                    if (input.replaceAll("\\s", "").equals(input)) {
                        throw new DukeInvalidCommandException("unmark");
                    }

                    String[] parsedString = input.split(" ");
                    try {
                        int num = Integer.parseInt(parsedString[1]);
                        if (num > lst.size() || num <= 0) {
                            throw new DukeInvalidIndexException(lst.size());
                        }
                        Task selectedTask = lst.get(num - 1);
                        this.unmarkCompletion(selectedTask);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidIndexException(lst.size());
                    }
                } else {
                    if (input.startsWith("todo")) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("todo");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String description = input.substring(input.indexOf(' ') + 1).trim();
                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        }
                        this.addTodo(description);
                    } else if (input.startsWith("deadline")) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("deadline");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String task = input.substring(input.indexOf(' ') + 1);
                        String[] parsedTask = task.split("/");
                        String description = parsedTask[0].trim();
                        String by = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        } else if ( by.equals("")) {
                            throw new DukeEmptyParametersException();
                        } else {
                            this.addDeadline(description, by);
                        }
                    } else if (input.startsWith("event")) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("event");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String task = input.substring(input.indexOf(' ') + 1);
                        String[] parsedTask = task.split("/");
                        String description = parsedTask[0].trim();
                        String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
                        String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();
                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        } else if (start.equals("") || by.equals("")) {
                            throw new DukeEmptyParametersException();
                        } else {
                            this.addEvent(description, start, by);
                        }
                    } else {
                        throw new DukeInvalidCommandException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e);
                System.out.println(line);
            }
        }
    }

    public void addTodo(String input) {
        Todo newTask = new Todo(input);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + newTask + "\n");
        lst.add(newTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        System.out.println(line);
    }

    public void addDeadline(String input, String by) {
        Deadline newTask = new Deadline(input, by);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + newTask + "\n");
        lst.add(newTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        System.out.println(line);
    }

    public void addEvent(String input, String start, String end) {
        Event newTask = new Event(input, start, end);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + newTask + "\n");
        lst.add(newTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        System.out.println(line);
    }

    public void printList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i).toString() + "\n");
        }
        System.out.println(line);
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void markCompletion(Task task) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n");
            System.out.println("\t" + task);
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n");
            task.toggleCompletion();
            System.out.println("\t" + task);
            System.out.println(line);
        }
    }

    public void unmarkCompletion(Task task) {
        if (task.getStatusIcon().equals(" ")) {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n");
            System.out.println("\t" + task);
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n");
            task.toggleCompletion();
            System.out.println("\t" + task);
            System.out.println(line);
        }
    }
}