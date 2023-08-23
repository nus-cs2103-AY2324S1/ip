import java.util.ArrayList;
import java.util.Scanner;
public class CheeChat {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm CheeChat");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String cmd = input.nextLine();
        int counter = 0;
        while(!cmd.equals("bye")){


            if (cmd.equals("list")) {
                for (int x = 0; x < counter; x++) {
                    int index = x + 1;
                    System.out.println(index + "." + tasks.get(x).toString());
                }
            } else {
                if (cmd.startsWith("mark")) {
                    int index = cmd.length() - 1;
                    char c = cmd.charAt(index);
                    int number = c - 48 - 1;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(number).description(0));
                } else if (cmd.equals("unmark")) {
                    int index = cmd.length() - 1;
                    char c = cmd.charAt(index);
                    int number = c - 48 - 1;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(number).description(1));
                } else {
                    System.out.println("added: " + cmd);
                    Task instance = new Task(cmd);
                    tasks.add(instance);
                    counter++;
                }
            }
            cmd = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}

