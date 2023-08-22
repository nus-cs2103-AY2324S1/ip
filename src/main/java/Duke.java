import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________\n");
        System.out.println("Hello! I 'm Jarvis.\n");
        System.out.println("What can I do for you?\n");
        System.out.println("_____________________________________\n");

        List<Task> task = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("_____________________________________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("_____________________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("_____________________________________\n");
                for (int i = 0; i < task.size(); i++) {
                    int tem_order = i + 1;
                    System.out.println(tem_order + "." + task.get(i));
                }
                System.out.println("_____________________________________\n");
            } else if (input.startsWith("mark ")) {
                String[] tem = input.split(" ");
                int input_num = Integer.parseInt(tem[1]) - 1;
                task.get(input_num).mark();
                System.out.println("_____________________________________\n");
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println(task.get(input_num));
                System.out.println("_____________________________________\n");
            } else if (input.startsWith("unmark")) {
                String[] tem = input.split(" ");
                int input_num = Integer.parseInt(tem[1]) - 1;
                task.get(input_num).unmark();
                System.out.println("_____________________________________\n");
                System.out.println("OK, I've marked this task as not done yet:\n");
                System.out.println(task.get(input_num));
                System.out.println("_____________________________________\n");
            } else {
                task.add(new Task(input));
                System.out.println("_____________________________________\n");
                System.out.println("added: " + input + "\n");
                System.out.println("_____________________________________\n");
            }
        }
    }
}
