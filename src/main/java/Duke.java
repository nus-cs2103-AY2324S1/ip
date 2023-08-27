import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Duke {
    private final String line = "_____________________________________________________";
    private final ArrayList<Task> lst;

    private final Storage storage;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

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

    public Duke() {
        this.storage = new Storage("./data/data.txt");
        this.lst = this.storage.read();
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
                        this.markCompletion(selectedTask, num);
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
                        this.unmarkCompletion(selectedTask, num);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidIndexException(lst.size());
                    }
                } else if (input.startsWith("delete")) {

                    if (input.replaceAll("\\s", "").equals(input)) {
                        throw new DukeInvalidCommandException("delete");
                    }

                    String[] parsedString = input.split(" ");
                    try {
                        int num = Integer.parseInt(parsedString[1]);
                        if (num > lst.size() || num <= 0) {
                            throw new DukeInvalidIndexException(lst.size());
                        }
                        this.deleteTask(num);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidIndexException(lst.size());
                    }

                } else {
                    TaskType taskType;
                    if (input.startsWith("todo")) {
                        taskType = TaskType.TODO;
                    } else if (input.startsWith("deadline")) {
                        taskType = TaskType.DEADLINE;
                    } else if (input.startsWith("event")) {
                        taskType = TaskType.EVENT;
                    } else {
                        taskType = null;
                    }

                    if (taskType == TaskType.TODO) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("todo");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String description = input.substring(input.indexOf(' ') + 1).trim();
                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        }
                        this.addTodo(description, false);
                    } else if (taskType == TaskType.DEADLINE) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("deadline");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String task = input.substring(input.indexOf(' ') + 1);
                        String[] parsedTask = task.split("/", 2);
                        String description = parsedTask[0].trim();

                        if (parsedTask.length < 2) {
                            throw new DukeEmptyParametersException();
                        }

                        String by = parsedTask[1].trim();
                        LocalDateTime deadlineDate = parseDate(by);

                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        } else if (deadlineDate == null) {
                            throw new DukeInvalidDateException();
                        } else {
                            this.addDeadline(description, false, deadlineDate);
                        }
                    } else if (taskType == TaskType.EVENT) {

                        if (input.replaceAll("\\s", "").equals(input)) {
                            throw new DukeInvalidCommandException("event");
                        }

                        String command = input.substring(0, input.indexOf(' '));
                        String task = input.substring(input.indexOf(' ') + 1);
                        String[] parsedTask = task.split("/");

                        if (parsedTask.length < 3) {
                            throw new DukeEmptyParametersException();
                        }

                        String description = parsedTask[0].trim();
                        String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
                        String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();
                        if (description.equals("")) {
                            throw new DukeInvalidCommandException(command);
                        } else if (start.equals("") || by.equals("")) {
                            throw new DukeEmptyParametersException();
                        } else {
                            this.addEvent(description, false, start, by);
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

    private LocalDateTime parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("\\s+", 2);
            String dateString = parts.length > 1 ? parts[1] : parts[0]; // Use the second part if available

            LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return dateTime;
        } catch (Exception e) {
            return null;
        }
    }

    public void addTodo(String input, boolean isDone) {
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void addDeadline(String input, boolean isDone, LocalDateTime by) {
        Deadline newTask = new Deadline(input, isDone, by);
        String newTaskString = newTask.fileFormat();

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void addEvent(String input, boolean isDone, String start, String end) {
        Event newTask = new Event(input, isDone, start, end);
        String newTaskString = newTask.fileFormat();

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);

        lst.add(newTask);
        storage.addTask(newTaskString);

        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void printList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i).toString());
        }
        System.out.println(line);
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void markCompletion(Task task, int num) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + task);
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
            System.out.println(line);
        }
    }

    public void unmarkCompletion(Task task, int num) {
        if (task.getStatusIcon().equals(" ")) {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + task);
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:");

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            System.out.println("\t" + task);
            System.out.println(line);
        }
    }

    public void deleteTask(Integer num) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");

        Task selectedTask = lst.remove(num - 1);
        this.storage.updateTask(num - 1, null);

        System.out.println("\t" + selectedTask);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        System.out.println(line);
    }
}