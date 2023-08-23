import java.util.Scanner;

public class Chatbot {
    private TaskManager taskManager;
    private Scanner scanner;

    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }

    public Chatbot() {
        this.taskManager = new TaskManager();
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        greetUser();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String userInput = scanner.nextLine().trim();
                TaskType taskType = determineTasktype(userInput);

                switch (taskType) {
                    case TODO : handleTodo(userInput);
                    break;
                    case DEADLINE : handleDeadline(userInput);
                    break;
                    case EVENT : handleEvent(userInput);
                    break;
                    case UNKNOWN : {
                        // Other non-task specific commands can be handled here.
                        if ("bye".equalsIgnoreCase(userInput)) {
                            farewellUser();
                            isRunning = false;
                            break;
                        } else if ("list".equalsIgnoreCase(userInput)) {
                            taskManager.printTasks();

                        } else if (userInput.startsWith("mark")) {
                            handleMark(userInput);
                        } else if (userInput.startsWith("unmark")) {
                            handleUnmark(userInput);
                        } else if (userInput.startsWith("delete")) {
                            int index = Integer.parseInt(userInput.split(" ")[1]);
                            taskManager.deleteTask(index);
                        } else {
                            throw new ChatbotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                }
            } catch (ChatbotException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            } catch (Exception e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     ☹ OOPS!!! An unexpected error occurred.");
                System.out.println("    ____________________________________________________________");
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    private TaskType determineTasktype(String userInput) {
        if (userInput.startsWith("todo")) return TaskType.TODO;
        if (userInput.startsWith("deadline")) return TaskType.DEADLINE;
        if (userInput.startsWith("event")) return TaskType.EVENT;
        return TaskType.UNKNOWN;
    }

    private void handleTodo(String userInput) throws ChatbotException {
        if (userInput.length() <= 4) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = userInput.substring(5);
        taskManager.addTodo(taskDescription);
    }

    private void handleDeadline(String userInput) throws ChatbotException {
        String[] parts = userInput.substring(9).split("/by");
        String taskDescription = parts[0].trim();
        String date = parts[1].trim();
        taskManager.addDeadlines(taskDescription, date);
    }

    private void handleEvent(String userInput) throws ChatbotException {
        String[] parts = userInput.substring(6).split("/from|/to");
        String taskDescription = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();
        taskManager.addEvents(taskDescription, start, end);
    }

    private void greetUser() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Sara");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    private void farewellUser() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private void handleMark(String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]);
            taskManager.taskDone(index);
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + taskManager.getTask(index));
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("     Oops! Please enter a valid task number to mark.");
            System.out.println("    ____________________________________________________________");
        }
    }

    private void handleUnmark(String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]);
            taskManager.unMarktask(index);
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + taskManager.getTask(index));
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("     Oops! Please enter a valid task number to unmark.");
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Chatbot Sara = new Chatbot();
            Sara.run();
    }
}


