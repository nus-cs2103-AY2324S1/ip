import java.util.ArrayList;
import java.util.Arrays;
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
                        if (new ArrayList<String>(Arrays.asList("todo", "deadline", "event")).contains(input[0])
                                && input.length == 1) {
                            try {
                                throw new DukeException("");
                            } catch (Exception e) {
                                System.out.println("____________________________________________________________\n" +
                                        "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                        "____________________________________________________________"
                                );
                                break;
                            }
                        }
                        switch (input[0]) {
                            case "todo":
                                job = new ToDo(echo);
                                list.add(job);
                                System.out.println(job.addTask(list.size()));
                                break;
                            case "deadline":
                                job = new Deadline(echo);
                                list.add(job);
                                System.out.println(job.addTask(list.size()));
                                break;
                            case "event":
                                job = new Event(echo);
                                list.add(job);
                                System.out.println(job.addTask(list.size()));
                                break;
                            default:
                                // list.add(job);
                                // System.out.println(job.addTask(list.size()));
                                try {
                                    throw new DukeException("");
                                } catch (Exception e) {
                                    System.out.println("____________________________________________________________\n" +
                                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                            "____________________________________________________________");
                                }
                        }
                }
            }
        }
    }
}
