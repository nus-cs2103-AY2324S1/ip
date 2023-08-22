import java.util.Scanner;
public class Duke {
    protected Task[] tasks;
    protected int numberOfTasks;

    public Duke() {
        tasks = new Task[100];
        numberOfTasks = 0;
    }

    public String printAddTaskSuccessMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n");
        message.append(" ");
        message.append(this.tasks[this.numberOfTasks].toString());
        message.append("\n");
        this.numberOfTasks++;
        message.append("Now you have ");
        message.append(this.numberOfTasks);
        message.append(" task(s) in the list.");
        return message.toString();
    }

    public void addTask(String[] task, String typeOfTask) {
        switch (typeOfTask) {
            case "event":
                if (task.length == 1 || task[1].isBlank()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty and the start and end time/date cannot be empty.");
                    break;
                } else if (task[1].contains("/from") && task[1].contains("/to")) {
                    String[] description = task[1].split(" /from ");
                    this.tasks[this.numberOfTasks] = new Event(description[0], description[1]);
                    System.out.println(printAddTaskSuccessMessage());
                    break;
                } else {
                    System.out.println("OOPS!!! Invalid command. The command for an event should be:\nevent <task> /from <start date/time> /to <end date/time>");
                    break;
                }
            case "todo":
                if (task.length == 1 || task[1].isBlank()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    break;
                }
                this.tasks[this.numberOfTasks] = new Todo(task[1]);
                System.out.println(printAddTaskSuccessMessage());
                break;
            case "deadline":
                if (task.length == 1 || task[1].isBlank()) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty and the deadline for the deadline cannot be empty.");
                    break;
                } else if (task[1].contains("/by")) {
                    String[] description = task[1].split(" /by ");
                    this.tasks[this.numberOfTasks] = new Deadline(description[0], description[1]);
                    System.out.println(printAddTaskSuccessMessage());
                    break;
                } else {
                    System.out.println("OOPS!!! Invalid command. The command for a deadline should be:\ndeadline <task> /by <deadline>");
                    break;
                }
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();

        String greetings = "Hello! I'm Botty!\nWhat can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        // Only exit when user types the command bye
        label:
        while (true) {
            if (sc.hasNextLine()) {
                String command = sc.nextLine();
                // Split string into first word and remaining words
                String[] words = command.split(" ", 2);
                String firstWord = words[0];

                switch (firstWord) {
                    case "bye":
                        System.out.println(farewell);
                        sc.close();
                        break label;
                    // Display the stored commands
                    case "list":
                        if (chatbot.numberOfTasks == 0) {
                            System.out.println("You do not have any tasks in the list.");
                            break;
                        }
                        System.out.println("Here are the task(s) in your list:");
                        for (int i = 0; i < chatbot.numberOfTasks; i++) {
                            System.out.println(i + 1 + "." + chatbot.tasks[i].toString());
                        }
                        break;
                    // Add task
                    case "todo":
                        chatbot.addTask(words, "todo");
                        break;
                    case "deadline":
                        chatbot.addTask(words, "deadline");
                        break;
                    case "event":
                        chatbot.addTask(words, "event");
                        break;
                    // Mark task as done
                    case "mark":
                        if (chatbot.numberOfTasks == 0) {
                            System.out.println("Add task to start marking task as done!");
                            break;
                        } else if (words.length == 1 || words[1].isBlank()) {
                            System.out.println("OOPS!! Task number cannot empty. Please indicate a task number from one to the number of tasks in the list.");
                            break;
                        }
                        int markIndex = Integer.parseInt(words[1]);
                        if (markIndex > chatbot.numberOfTasks || markIndex <= 0) {
                            System.out.println("OOPS!! Invalid task number. The task number has to be from one to the number of tasks in the list.");
                            break;
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        Task markTask = chatbot.tasks[markIndex - 1];
                        markTask.markAsDone();
                        System.out.println(markTask.toString());
                        break;
                    // Mark task as not done
                    case "unmark":
                        if (chatbot.numberOfTasks == 0) {
                            System.out.println("Add task to start marking task as not done!");
                            break;
                        } else if (words.length == 1 || words[1].isBlank()) {
                            System.out.println("OOPS!! Task number cannot empty. Please indicate a task number from one to the number of tasks in the list.");
                            break;
                        }
                        int unmarkIndex = Integer.parseInt(words[1]);
                        if (unmarkIndex > chatbot.numberOfTasks || unmarkIndex <= 0) {
                            System.out.println("OOPS!! Invalid task number. The task number has to be from one to the number of tasks in the list.");
                            break;
                        }
                        System.out.println("OK, I've marked this task as not done yet:");
                        Task unmarkTask = chatbot.tasks[unmarkIndex - 1];
                        unmarkTask.markAsNotDone();
                        System.out.println(unmarkTask.toString());
                        break;
                    // Invalid command
                    default:
                        System.out.println("OOPS!!! Invalid command. Try the following commands instead:");
                        System.out.println("> todo <task>");
                        System.out.println("> deadline <task> /by <deadline>");
                        System.out.println("> event <task> /from <start date/time> /to <end date/time>");
                        System.out.println("> list");
                        System.out.println("> mark <task number>");
                        System.out.println("> unmark <task number>");
                        System.out.println("> bye");
                        break;
                }
            }
        }
    }
}
