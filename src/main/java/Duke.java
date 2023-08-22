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
        String horizontalLine = "\t____________________________________________________";
        ArrayList<Task> lst = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        while (true) {
            if (commands.equals("bye")) {
                return;
            } else if (commands.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("\tHere are the task in your list:");
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + lst.get(i).getStatusIcon() + " " + lst.get(i).taskDescription);
                }
                System.out.println(horizontalLine);
            } else if (commands.indexOf("mark") == 0) {
                int index = java.lang.Integer.parseInt(commands.substring(5)) - 1;
                lst.get(index).changeStatus();
                System.out.println(horizontalLine);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + lst.get(index).getStatusIcon() + " " + lst.get(index).taskDescription);
                System.out.println(horizontalLine);
            } else if (commands.indexOf("unmark") == 0) {
                int index = java.lang.Integer.parseInt(commands.substring(7)) - 1;
                lst.get(index).changeStatus();
                System.out.println(horizontalLine);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t  " + lst.get(index).getStatusIcon() + " " + lst.get(index).taskDescription);
                System.out.println(horizontalLine);
            } else {
                lst.add(new Task(commands, false));
                System.out.println(horizontalLine);
                System.out.println("\t added: " + commands);
                System.out.println(horizontalLine);
            }

            commands = input.nextLine();
        }
    }
}
