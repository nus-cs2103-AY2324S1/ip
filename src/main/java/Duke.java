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
            } else {
                ls.add(new Task(cmd));
                System.out.println("added: " + cmd + "\n");
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
