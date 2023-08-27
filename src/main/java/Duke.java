import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        List<Task> ls = new ArrayList<>();

        while (true) {
            if (cmd.equals("bye")) {
                sc.close();
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            } else if (cmd.equals("list")) {
                int size = ls.size();
                for(int i = 0; i < size;i++) {
                    int j = i + 1;
                    System.out.println(j + "." + ls.get(i));
                }
                cmd = sc.nextLine();
            } else if (cmd.contains("unmark")) {
                //int index = sc.nextInt();
                int index = Integer.parseInt(cmd.substring(7, 8));
                Task task = ls.get(index-1);
                ls.remove(index-1);
                task.unmark();
                ls.add(index-1, task);
                //ls.get(index-1);
                System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
                cmd = sc.nextLine();
            } else if (cmd.contains("mark")) {
                int index = Integer.parseInt(cmd.substring(5, 6));
                Task task = ls.get(index - 1);
                ls.remove(index-1);
                task.markAsDone();
                ls.add(index - 1, task);
                System.out.println("Nice! I've marked this task as done:\n" + task.toString());
                cmd = sc.nextLine();
            } else if (cmd.contains("delete")) {
                int index = Integer.parseInt(cmd.substring(7, 8));
                Task task = ls.get(index - 1);
                ls.remove(index - 1);
                int size = ls.size();
                System.out.println("Noted. I've removed this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.");
                cmd = sc.nextLine();
            } else if (cmd.contains("deadline")) {
                String[] parts = cmd.split("/by");
                if (parts.length == 2) {
                    String description = parts[0].replace("deadline", "").trim(); // Remove "deadline"
                    String deadline = parts[1].trim();
                    Task task = new Deadline(description, deadline);
                    ls.add(task);
                    int size = ls.size();
                    System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.\n");
                } else {
                    DukeException exp = new DukeException("deadline");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else if (cmd.contains("todo")){
                String[] parts = cmd.split(" ", 2);
                if (parts.length == 2) {
                    String desc = parts[1].trim();
                    Task task = new ToDo(desc);
                    ls.add(task);
                    int size = ls.size();
                    System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.\n");
                } else {
                    DukeException exp = new DukeException("todo");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else if (cmd.contains("event")) {
                String[] parts = cmd.split("/from");
                if (parts.length == 2) {
                    String desc = parts[0].replace("event", "").trim();
                    String rest = parts[1].trim();
                    String[] restParts = rest.split("/to");
                        String from = restParts[0].trim();
                        String till = restParts[1].trim();
                        Task task = new Event(desc, from, till);
                        ls.add(task);
                        int size = ls.size();
                        System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.\n");

                } else {
                    DukeException exp = new DukeException("event");
                    System.out.println(exp.toString());
                }
                cmd = sc.nextLine();
            } else {
                DukeException exp = new DukeException("");
                System.out.println(exp.nothing());
                cmd = sc.nextLine();
            }
        }
    }
}
//    String logo = " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
