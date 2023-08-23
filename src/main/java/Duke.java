import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner userInput = new Scanner(System.in);
    ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();

    }
    public void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void start() {
        line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        line();

        while (true) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                Task currTask = taskList.get(taskIndex);
                currTask.taskDone(true);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                Task currTask = taskList.get(taskIndex);
                currTask.taskDone(false);
            } else {
                System.out.println("Got it. I've added this task:");
                if (input.startsWith("todo")) {
                    ToDo toDo = new ToDo(input);
                    System.out.println(toDo.toString());
                    taskList.add(toDo);

                } else if (input.startsWith("event")) {
                    String[] list = input.split("/");
                    String title = list[0].substring(6);
                    String start = list[1].substring(5);
                    String end = list[2].substring(3);

                    Event event = new Event(title, start, end);
                    System.out.println(event.toString());
                    taskList.add(event);

                } else {
                    String[] list = input.split("/");
                    String title = list[0].substring(9);
                    String time = list[1].substring(3);

                    Deadline deadline = new Deadline(title, time);
                    System.out.println(deadline.toString());
                    taskList.add(deadline);
                }

                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                line();
            }
        }
    }

    public void list() {
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Task t = taskList.get(i);
            System.out.println(index + "." + t.toString());
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
}
