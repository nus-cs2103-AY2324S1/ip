import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // initial logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

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
                Task currentTask = new Task(string);
                list[taskInArray + 1] = currentTask;
                System.out.println(line + "\nadded: " + string);
                taskInArray += 1;
            }

            System.out.println(line);
            string = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!" + "\n" + line);
    }
}
