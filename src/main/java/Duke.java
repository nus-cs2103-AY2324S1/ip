import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Husky\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (true) {
            String echo = io.nextLine();
            if (echo.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else if (echo.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    Task job = list.get(i);
                    System.out.println(String.format("%d. %s", i + 1, job.toString()));
                }
                System.out.println("____________________________________________________________");
            } else if (echo.length() > 1) {
                String[] input = echo.split(" ");
                if (input[0].equals("mark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    Task job = list.get(index);
                    MarkedTask markedJob = job.mark();
                    list.set(index, markedJob);
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as done:\n" + markedJob.toString() + "\n");
                    System.out.println("____________________________________________________________");
                } else if (input[0].equals("unmark")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    Task markedJob = list.get(index);
                    Task job = markedJob.unmark();
                    list.set(index, job);
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:\n" + job.toString() + "\n");
                    System.out.println("____________________________________________________________");
                } else {
                    list.add(new Task(echo));
                    System.out.println("____________________________________________________________\n" + "added: " + echo + "\n" +
                            "____________________________________________________________");
                }
            } else {
                list.add(new Task(echo));
                System.out.println("____________________________________________________________\n" + "added: " + echo + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}

