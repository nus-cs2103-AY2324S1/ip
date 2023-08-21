import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] taskList = new Task[100];

        String initMsg = "____________________________________________________________\n"
                + " Hello! I'm IRIS\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(initMsg);

        input = scanner.nextLine();
        int taskIndex = 0;

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];

            if (input.equals("list")) {
                int count = 1;
                for (Task task : taskList) {
                    if (task == null) {
                        break;
                    } else {
                        System.out.println(count++ + ". " + task);
                    }
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList[index].markDone();
                System.out.println("Nice! I've marked this task as done:\n"
                + taskList[index]);
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList[index].markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + taskList[index]);
            } else {
                Task newTask = null;
                if (command.equals("todo")) {
                    newTask = new Todo(inputParts[1]);
                } else if (command.equals("deadline")) {
                    String[] commandParts = inputParts[1].split("/by", 2);
                    newTask = new Deadline(commandParts[0], commandParts[1]);
                } else if (command.equals("event")) {
                    String[] commandParts = inputParts[1].split("/from", 2);
                    String[] eventParts = commandParts[1].split("/to");
                    newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
                }

                if (newTask != null) {
                    taskList[taskIndex++] = newTask;
                    System.out.println("Got it. I've added this task:\n" + newTask);
                    System.out.println("Now you have " + taskIndex + " tasks in the list.");
                }
            }

            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }

        scanner.close();
        String endMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMsg);
    }
}

//else {
//        if (command.equals("todo")) {
//        Task newTask = new Todo(inputParts[1]);
//        taskList[taskIndex++] = newTask;
//        System.out.println("Got it. I've added this task:\n" + newTask);
//        } else if (command.equals("deadline")) {
//        String[] commandParts = inputParts[1].split("/by", 2);
//        Task newTask = new Deadline(commandParts[0], commandParts[1]);
//        taskList[taskIndex++] = newTask;
//        System.out.println("Got it. I've added this task:\n" + newTask);
//        } else if (command.equals("event")) {
//        String[] commandParts = inputParts[1].split("/from", 2);
//        String[] eventParts = commandParts[1].split("/to");
//        Task newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
//        taskList[taskIndex++] = newTask;
//        System.out.println("Got it. I've added this task:\n" + newTask);
//        }
