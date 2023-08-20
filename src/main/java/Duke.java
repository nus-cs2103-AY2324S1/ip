import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hey summoner! I'm Three Wolves B.\n" + "What do you want me to do?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();
        String cmd = sc.nextLine();
        while (true) {
            if (cmd.equals("")) {
                System.out.println("What happened? You said nothing at all!");
            } else if (cmd.matches("list")) {
                System.out.println("Here are the tasks in your list");
                lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x));
            } else if (cmd.matches("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.matches("mark \\d*")) {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                Task now = lst.get(index);
                if (now != null) {
                    System.out.println("Ok, I will mark this task as done:");
                    now.mark();
                } else {
                    System.out.println("There is no such a task.");
                }
            } else if (cmd.matches("unmark \\d*")) {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                Task now = lst.get(index);
                if (now != null) {
                    System.out.println("Ok, I will mark this task as not done:");
                    now.unmark();
                } else {
                    System.out.println("There is no such a task.");
                }
            } else {
                lst.add(new Task(cmd));
                System.out.println("added: " + cmd);
            }
            System.out.println("");
            cmd = sc.nextLine();
        }
        sc.close();
    }
}
