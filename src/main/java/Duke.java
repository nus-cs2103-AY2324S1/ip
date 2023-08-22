import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "\t____________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(horizontalLine);
        echo();
        System.out.println(horizontalLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void echo() {
        ArrayList<Task> lst = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        while (true) {
            if (commands.equals("bye")) {
                return;
            } else if (commands.equals("list")) {
                showList(lst);
            } else if (commands.indexOf("mark") == 0) {
                mark(lst, commands);
            } else if (commands.indexOf("unmark") == 0) {
                unmark(lst, commands);
            } else if (commands.indexOf("deadline") == 0) {
                addDeadline(lst, commands);
            } else if (commands.indexOf("todo") == 0) {
                addToDo(lst, commands);
            } else if (commands.indexOf("event") == 0) {
                addEvent(lst, commands);
            } else {
                addTask(lst, commands);
            }

            commands = input.nextLine();
        }
    }

    public static void showList(ArrayList<Task> lst) {
        String horizontalLine = "\t____________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("\tHere are the task in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.get(i).toString());
        }
        System.out.println(horizontalLine);
    }

    public static void addTask(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";
        lst.add(new Task(commands));
        System.out.println(horizontalLine);
        System.out.println("\tadded: " + commands);
        System.out.println(horizontalLine);
    }

    public static void addDeadline(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";

        int byIndex = commands.indexOf("/by");
        int commandIndex = commands.indexOf(" ");

        String by = commands.substring(byIndex + "/by".length() + 1);
        String command = commands.substring(commandIndex + 1, byIndex - 1);

        lst.add(new Deadline(command, by));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void addToDo(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";

        int commandIndex = commands.indexOf(" ");
        String command = commands.substring(commandIndex + 1);

        lst.add(new ToDo(command));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void addEvent(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";

        int fromIndex = commands.indexOf("/from");
        int toIndex = commands.indexOf("/to");
        int commandIndex = commands.indexOf(" ");

        String from = commands.substring(fromIndex + "/from".length() + 1, toIndex - 1);
        String to = commands.substring(toIndex + "/to".length() + 1);
        String command = commands.substring(commandIndex + 1, fromIndex - 1);

        lst.add(new Event(command, from, to));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);

    }

    public static void mark(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring(5)) - 1;
        lst.get(index).changeStatus();
        System.out.println(horizontalLine);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }

    public static void unmark(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t____________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring(7)) - 1;
        lst.get(index).changeStatus();
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }
}
