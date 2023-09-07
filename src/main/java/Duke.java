import java.util.Scanner;
public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount= 0;
    private static void addTask(String userCommand) {
        if (taskCount < MAX_TASKS) {
            String[] parts = userCommand.split(" ", 2);
            if (parts.length == 2) {
                String taskType = parts[0].toLowerCase();
                String taskDescription = parts[1];

                switch (taskType) {
                    case "todo":

                        tasks[taskCount++] = new Todo(taskDescription);

                        break;
                    case "deadline":
                        String[] deadlineParts = taskDescription.split(" /by ");
                        if (deadlineParts.length == 2) {
                            String deadlineTask = deadlineParts[0];
                            String deadlineTime = deadlineParts[1];
                            if (deadlineTask.trim().isEmpty()) {
                                System.out.println("What kind of deadline do you have??");
                            } else if (deadlineTime.trim().isEmpty()) {
                                System.out.println("Can you tell me when is your deadline??");
                            } else {
                                tasks[taskCount++] = new Deadline(deadlineParts[0], deadlineParts[1]);
                            }
                        } else {
                            System.out.println("Invalid deadline format.");
                        }
                        break;
                    case "event":
                        String[] eventParts = taskDescription.split(" /from | /to ");
                        if (eventParts.length == 3) {
                            String eventTask = eventParts[0];
                            String eventStartTime = eventParts[1];
                            String eventEndTime = eventParts[2];
                            if (eventTask.trim().isEmpty()) {
                                System.out.println("What event do you have?");
                            } else if (eventStartTime.trim().isEmpty()) {
                                System.out.println("When will the event start?");
                            } else if (eventEndTime.trim().isEmpty()) {
                                System.out.println("When will the event end?");
                            } else {
                                tasks[taskCount++] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                            }
                        } else {
                            System.out.println("Invalid event format.");
                        }
                        break;
                    default:
                        System.out.println("Invalid command format.");
                        break;
                }

                if (taskCount > 0) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                }
            } else {
                if (parts[0].equalsIgnoreCase("todo")) {
                    System.out.println("What you want to do?");
                } else if (parts[0].equalsIgnoreCase("deadline")) {
                    System.out.println("What deadline do you have??");
                } else if (parts[0].equalsIgnoreCase("event")) {
                    System.out.println("What event are you going to attend??");
                } else {
                    System.out.println("Sorry, I don't know what that means :(");
                }
            }
        } else {
            System.out.println("Sorry, the task list is full.");
        }
    }
    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("The task list is empty!");
        } else {
            for(int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskCount;
    }
    private static void markTask(String userCommand) {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Please use 'mark [task number]'.");
        }
    }
    private static void unmarkTask(String userCommand) {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Please use 'unmark [task number]'.");
        }
    }
    public static void main(String[] args) {
        String greeting = "Hi, I'm BiuBiu.\nWhat can I do for you?";
        System.out.println(greeting);
        String exit = "Bye. Have a great day!";

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userCommand = scanner.nextLine();
            if(userCommand.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                listTasks();
            } else if (userCommand.startsWith("mark ")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark ")) {
                unmarkTask(userCommand);
            } else {
                addTask(userCommand);
            }
        }
    }
}
