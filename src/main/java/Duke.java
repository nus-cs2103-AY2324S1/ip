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
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list");
                for (int i = 0; i < list.size(); i++) {
                    Task job = list.get(i);
                    System.out.println(String.format("%d. %s", i + 1, job.toString()));
                }
                System.out.println("____________________________________________________________");
            } else {
                String[] input = echo.split(" ");
                switch (input[0]) {
                    case "mark":
                        int index = Integer.parseInt(input[1]) - 1;
                        Task job = list.get(index);
                        job = job.mark();
                        list.set(index, job);
                        System.out.println("____________________________________________________________\n" +
                                "OK, I've marked this task as done:\n" + job.toString() + "\n" +
                                "____________________________________________________________");
                        break;
                    case "unmark":
                        index = Integer.parseInt(input[1]) - 1;
                        job = list.get(index);
                        job = job.unmark();
                        list.set(index, job);
                        System.out.println("____________________________________________________________\n" +
                                "OK, I've marked this task as not done yet:\n" + job.toString() + "\n" +
                                "____________________________________________________________");
                        break;
                    default:
                        switch (input[0]) {
                            case "todo":
                                job = new ToDo(echo);
                                break;
                            case "deadline":
                                job = new Deadline(echo);
                                break;
                            case "event":
                                job = new Event(echo);
                                break;
                            default:
                                job = new Task(echo);
                        }
                        list.add(job);
                        System.out.println(job.addTask(list.size()));
                }
            }
        }
    }
}
