public class Chatbot {
    private TaskManager taskManager;
    private Ui ui;

    public Chatbot() {
        this.taskManager = new TaskManager();
        this.ui = new Ui();
    }


    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
                try {
                    String userInput = ui.readCommand();
                    Command command = Parser.parseCommand(userInput);
                    command.execute(taskManager, ui);
                    isExit = command.isExit();
                } catch (ChatbotException e) {
                    ui.showError(e.getMessage());
                } catch (Exception e) {
                    ui.showError("An unexpected error occurred.");
                }
            }
        }


    private void handleTodo(String[] userInput) throws ChatbotException {
        taskManager.addTodo(userInput[0].trim());
    }

    private void handleDeadline(String[] userInput) throws ChatbotException {
        taskManager.addDeadlines(userInput[0].trim(), userInput[1].trim());
    }


    private void handleEvent(String[] userInput) throws ChatbotException {
        taskManager.addEvents(userInput[0].trim(), userInput[1].trim(), userInput[2].trim());
    }

    private void handleMark(int userInput) {
            taskManager.taskDone(userInput);
    }

    private void handleUnmark(int userInput) {
        taskManager.unMarktask(userInput);
    }

    private boolean handleUnknownCommand(String userInput) throws ChatbotException {
        if ("bye".equalsIgnoreCase(userInput)) {
            ui.showFarewell();
            return false;
        } else if ("list".equalsIgnoreCase(userInput)) {
            taskManager.printTasks();
        }  else if (userInput.startsWith("delete")) {
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


