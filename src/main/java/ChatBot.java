import java.util.Scanner;

public class ChatBot {
    private final TaskList taskList ;

    public ChatBot() {
        this.taskList = new TaskList();
    }

    public void markTaskByBot(int taskIndex) {
        taskList.markTaskAsDone(taskIndex - 1);
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");
    }

    public void unmarkTaskByBot(int taskIndex) {
        taskList.markTaskAsNotDone(taskIndex - 1);
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");
    }

    private void addTaskByBot(String taskType, String description, String timing) {
        Task newTask = null;

        switch (taskType) {
            case "todo":
                newTask = new Todo(description);
                break;
            case "deadline":
                newTask = new Deadline(description, timing);
                break;
            case "event":
                if (timing != null) {
                    String[] eventTiming = timing.split("/to", 2);
                    if (eventTiming.length == 2) {
                        newTask = new Event(description, eventTiming[0].trim(), eventTiming[1].trim());
                    } else {
                        System.out.println("Invalid input format for event timing.");
                    }
                } else {
                    System.out.println("Event timing information missing.");
                }
                break;
        }

        if (newTask != null) {
            taskList.addTask(newTask);
            System.out.println("____________________________________________________________\n" +
                    " Got it. I've added this task:\n" + "  " +
                    newTask.toString() +
                    "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n" +
                    "____________________________________________________________");
        }
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);

        String helloMessage = "____________________________________________________________\n" +
                " Hello! I'm Najib\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String byeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(helloMessage);
        String input;
        String printMessage;

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            int taskIndex;

            if (command.equals("mark") && parts.length > 1) {
                taskIndex = Integer.parseInt(parts[1]);
                this.markTaskByBot(taskIndex);
            } else if (command.equals("unmark") && parts.length > 1) {
                taskIndex = Integer.parseInt(parts[1]);
                this.unmarkTaskByBot(taskIndex);
            } else if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;  // Exit the loop when user types "bye"
            } else if (input.equals("list")) {
                taskList.displayTasks();
            } else if (command.equals("todo") && parts.length > 1) {
                addTaskByBot("todo", parts[1], null);

            } else if (command.equals("deadline") && parts.length > 1) {
                String[] deadlineParts = parts[1].split("/by", 2);
                if (deadlineParts.length == 2) {
                    addTaskByBot("deadline", deadlineParts[0].trim(), deadlineParts[1].trim());
                } else {
                    System.out.println("Invalid input format for deadline.");
                }

            } else if (command.equals("event") && parts.length > 1) {
                String[] eventParts = parts[1].split("/from", 2);
                if (eventParts.length == 2) {
                    addTaskByBot("event", eventParts[0].trim(), eventParts[1].trim());
                } else {
                    System.out.println("Invalid input format for event.");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Please be specific!");
                System.out.println("____________________________________________________________");
            }

        }

        scanner.close();
    }
}
