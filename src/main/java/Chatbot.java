import java.util.Scanner;

public class Chatbot {
    private TaskManager taskManager;
    private Ui ui;

    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }

    public Chatbot() {
        this.taskManager = new TaskManager();
        this.ui = new Ui();
    }


    public void run() {
        ui.showGreeting();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                TaskType taskType = determineTasktype(userInput);

                switch (taskType) {
                    case TODO:
                        handleTodo(userInput);
                        break;
                    case DEADLINE:
                        handleDeadline(userInput);
                        break;
                    case EVENT:
                        handleEvent(userInput);
                        break;
                    case UNKNOWN:
                        isRunning = handleUnknownCommand(userInput);  // Returns false if 'bye'
                        break;
                }

            } catch (ChatbotException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("☹ OOPS!!! An unexpected error occurred.");
            }
        }
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

        if (parts.length < 2) {
            throw new ChatbotException("☹ OOPS!!! The date for a deadline cannot be empty.");
        }

        String taskDescription = parts[0].trim();
        String date = parts[1].trim();

        if (taskDescription.isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (date.isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The date for a deadline cannot be empty.");
        }

        taskManager.addDeadlines(taskDescription, date);
    }


    private void handleEvent(String userInput) throws ChatbotException {
        String[] parts = userInput.substring(6).split("/from|/to");
        String taskDescription = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();
        taskManager.addEvents(taskDescription, start, end);
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

    private boolean handleUnknownCommand(String userInput) throws ChatbotException {
        if ("bye".equalsIgnoreCase(userInput)) {
            ui.showFarewell();
            return false;
        } else if ("list".equalsIgnoreCase(userInput)) {
            taskManager.printTasks();
        } else if (userInput.startsWith("mark")) {
            handleMark(userInput);
        } else if (userInput.startsWith("unmark")) {
            handleUnmark(userInput);
        } else if (userInput.startsWith("delete")) {
            int index = Integer.parseInt(userInput.split(" ")[1]);
            taskManager.deleteTask(index);
        } else if (userInput.startsWith("task on")) {
            String date = userInput.split(" ")[2];  // Changed from 1 to 2
            taskManager.printTasksOnDate(date);
        } else {
            throw new ChatbotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }


    public static void main(String[] args) {
        Chatbot Sara = new Chatbot();
            Sara.run();
    }
}


