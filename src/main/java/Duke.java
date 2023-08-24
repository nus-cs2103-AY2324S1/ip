import java.util.Scanner;

public class Duke {
    static class Task {
        String task;
        boolean isDone = false;
        public Task(String task) {
            this.task = task;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
        Task[] storage = new Task[100];
        int filledIndex = 0;

        String openingStr =
                "____________________________________________________________\n" +
                        " Hello! I'm JEOE\n" +
                        " What can I do for you?\n" +
                        " type :\n" +
                        " list => to list out items in storage\n" +
                        " _Anything else_ => store in storage\n" +
                        "____________________________________________________________\n";
        System.out.println(openingStr);

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            boolean isMark = false;
            boolean isUnmark = false;
            if (input.length() > 0) {
                isMark = input.split(" ")[0].equals("mark"); //exclude the last index
                isUnmark = input.split(" ")[0].equals("unmark");
            }

            if (input.equals("bye")) {
                isRunning = false;
                String closingStr =
                        "____________________________________________________________\n" +
                                " Bye. Hope to see you again soon!\n" +
                                "____________________________________________________________";
                System.out.println(closingStr);
            } else if (input.equals("list")) {
                String list =
                        "____________________________________________________________\n" +
                                "Here are the tasks in your list:\n";

                for (int i = 1; i <= filledIndex; i++) {
                    String num = String.valueOf(i);
                    Task task = storage[i];
                    list = task.isDone ? (list + num + ". [X] " + task.task) : (list + num + ". [] " + task.task);
                    list += "\n";
                }

                list += "____________________________________________________________\n";
                System.out.println(list);
            } else if (isMark || isUnmark) {
                String list = "____________________________________________________________\n";
                int index = Integer.parseInt(input.split(" ")[1]);
                if (isMark) {
                    storage[index].isDone = true;
                    list +=
                            ("Nice! I've marked this task as done:\n" +
                                    "[X] " + storage[index].task + "\n");
                } else {
                    storage[index].isDone = false;
                    list +=
                            ("OK, I've marked this task as not done yet:\n" +
                                    "[] " + storage[index].task + "\n");
                }

                list += "____________________________________________________________\n";
                System.out.println(list);
            } else {
                storage[++filledIndex] = new Task (input); // storage array starts filling from index 1
                String reply =
                        "____________________________________________________________\n" +
                                "added: " + input + "\n" +
                                "____________________________________________________________\n";
                System.out.println(reply);
            }
        }
        scanner.close();
    }
}