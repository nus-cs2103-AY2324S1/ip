import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
    static final String name = "4F5DA2";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);
    static final int maxNumberOfTasks = 100;

    static ArrayList<Task> taskList = new ArrayList<>(maxNumberOfTasks);

    public static void greet() {
        System.out.println(line);
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal ChatBot, " + name + ".");
        System.out.println("\tWhat can I do for you today?");
        System.out.println(line);
    }

    public static void exit() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        output(String.format("\tGot it. I've added this task:\n\t\t%s\n\tNow you have %d tasks in the list",
                task.toString(),
                taskList.size()
                ));
    }

    public static void markAs(boolean isDone, int index) {
        Task task = taskList.get(index-1);
        task.markAs(isDone);
        output(String.format("\t%s\n\t%s",
                isDone ? "Nice! I've marked this task as done:"
                       : "OK, I've marked this task as not done yet:",
                task.toString()));
    }

    public static void listTasks() {
        System.out.println(line);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("\t%d.%s",
                    i+1,
                    task.toString()));
        }
        System.out.println(line);
    }

    private static Task parseTodoTask(String command) {
        return new ToDoTask(command.substring(5));
    }

    private static Task parseDeadlineTask(String command) {
        int idOfBy = command.indexOf("/by");
        String name = command.substring(9, idOfBy);
        String deadline = command.substring(idOfBy + 4);
        return new DeadlineTask(name, deadline);
    }

    private static Task parseEventTask(String command) {
        int idOfFrom = command.indexOf("/from");
        int idOfTo = command.indexOf("/to");
        String name = command.substring(6, idOfFrom);
        String from = command.substring(idOfFrom + 6, idOfTo);
        String to = command.substring(idOfTo + 4);
        return new EventTask(name, from, to);
    }

    public static void nextCommand() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            exit();
            return;
        } else if (command.equals("list")) {
            listTasks();
        } else {
            String[] words = command.split(" ");
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                markAs(words[0].equals("mark"), Integer.parseInt(words[1]));
            } else if (words[0].equals("todo")) {
                addTask(parseTodoTask(command));
            } else if (words[0].equals("deadline")) {
                addTask(parseDeadlineTask(command));
            } else if (words[0].equals("event")) {
                addTask(parseEventTask(command));
            }
            else {
                output("\tSorry, I'm not sure what that means...");
            }
        }
        nextCommand();
    }

    public static void main(String[] args) {
        greet();
        nextCommand();

    }
}
