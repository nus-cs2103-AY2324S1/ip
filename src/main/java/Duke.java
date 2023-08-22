import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // welcome message
        String line = "_".repeat(40);
        Task[] list = new Task[100];
        String name = "DukeKing";
        String welcome = "\nHello! I'm " + name + "\nWhat can I do for you?";
        System.out.println(line + welcome + "\n" + line);

        // setting up scanner
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        int taskInArray = 0;

        //looping in the program
        while (true) {
            // end the program
            if (string.equals("bye")) {
                break;

                // reading the list
            } else if (string.equals("list")) {
                System.out.println(line);
                String openingList = "Here are the tasks in your list:";
                System.out.println(openingList);
                for (int length = 1; length < taskInArray + 1; length += 1) {
                    System.out.println(length + "." + list[length]);
                }

                // marking the task to the list
            } else if (string.startsWith("mark")) {
                String[] splittedInput = string.split(" ");
                int taskNumber = Integer.parseInt(splittedInput[1]);
                list[taskNumber].markAsDone();
                String markingTask = "Nice! I've marked this task as done:";
                System.out.println(line + "\n" + markingTask + "\n" + list[taskNumber]);

                // unmarking the task from the list
            } else if (string.startsWith("unmark")) {
                String[] splittedInput = string.split(" ");
                int taskNumber = Integer.parseInt(splittedInput[1]);
                list[taskNumber].markAsUndone();
                String unMarkingTask = "OK, I've marked this task as not done yet:";
                System.out.println(line + "\n" + unMarkingTask + "\n" + list[taskNumber]);

            } else {
                // adding task to the list
                String addingTask = "Got it. I've added this task:";
                int noOfTask = taskInArray + 1;
                String numberOfTask = "Now you have " + noOfTask + " tasks in the list.";
                if (string.startsWith("todo")) {
                    String[] splittedInput = string.split(" ");
                    String task = string.replace("todo ", "");
                    Task currentTask = new Todo(task);
                    list[noOfTask] = currentTask;
                    System.out.println(line + "\n" + addingTask + "\n" + "  " + currentTask + "\n" + numberOfTask);
                } else if (string.startsWith("deadline")) {
                    String[] splittedInput = string.split(" ");
                    String task = string.replace("deadline ", "");
                    String[] splittedTask = task.split(" /by ");
                    String taskName = splittedTask[0];
                    String end = splittedTask[1];
                    Task currentTask = new Deadlines(taskName, end);
                    list[noOfTask] = currentTask;
                    System.out.println(line + "\n" + addingTask + "\n" + "  " + currentTask + "\n" + numberOfTask);
                } else if (string.startsWith("event")) {
                    String[] splittedInput = string.split(" ");
                    String task = string.replace("event ", "");
                    String[] splitStart = task.split(" /from ");
                    String taskName = splitStart[0];
                    String[] splitEnd = splitStart[1].split(" /to ");
                    String start = splitEnd[0];
                    String end = splitEnd[1];
                    Task currentTask = new Events(taskName, start, end);
                    list[noOfTask] = currentTask;
                    System.out.println(line + "\n" + addingTask + "\n" + "  " + currentTask + "\n" + numberOfTask);

                }
                taskInArray += 1;
            }

            System.out.println(line);
            string = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!" + "\n" + line);
    }
}
