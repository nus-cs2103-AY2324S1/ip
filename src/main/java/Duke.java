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
        message.append(" tasks in the list.");
        return message.toString();
    }

    public void addTask(String[] task, String typeOfTask) {
        switch (typeOfTask) {
            case "event":
                this.tasks[this.numberOfTasks] = new Event(task[0], task[1]);
                System.out.println(printAddTaskSuccessMessage());
                break;
            case "todo":
                this.tasks[this.numberOfTasks] = new Todo(task[1]);
                System.out.println(printAddTaskSuccessMessage());
                break;
            case "deadline":
                this.tasks[this.numberOfTasks] = new Deadline(task[0], task[1]);
                System.out.println(printAddTaskSuccessMessage());
                break;
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < chatbot.numberOfTasks; i++) {
                        System.out.println(i + 1 + "." + chatbot.tasks[i].toString());
                    }
                    break;
                // Add task
                case "todo":
                    chatbot.addTask(words, "todo");
                    break;
                case "deadline":
                    if (words[1].contains("/by")) {
                        String[] task = words[1].split(" /by ");
                        chatbot.addTask(task, "deadline");
                    }
                    break;
                case "event":
                    if (words[1].contains("/from") && words[1].contains("/to")) {
                        String[] task = words[1].split(" /from ");
                        chatbot.addTask(task, "event");
                    }
                    break;
                // Mark task as done
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    int markIndex = Integer.parseInt(words[1]);
                    Task markTask = chatbot.tasks[markIndex - 1];
                    markTask.markAsDone();
                    System.out.println(markTask.toString());
                    break;
                // Mark task as not done
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    int unmarkIndex = Integer.parseInt(words[1]);
                    Task unmarkTask = chatbot.tasks[unmarkIndex - 1];
                    unmarkTask.markAsNotDone();
                    System.out.println(unmarkTask.toString());
                    break;
                // Invalid command
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
