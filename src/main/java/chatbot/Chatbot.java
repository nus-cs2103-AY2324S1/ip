package chatbot;

import chatbot.command.Command;
import chatbot.task.TaskManager;

/**
 * The Chatbot class initialises the chatbot application and handles the main
 * event.
 */
public class Chatbot {
    private TaskManager taskManager;
    private Ui ui;

    /**
     * contructor for Chatbot class.
     */
    public Chatbot() {
        this.taskManager = new TaskManager();
        this.ui = new Ui();
    }


    /**
    Run chatbot class.
     */
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

    /**
     * The Chatbot class initialises the chatbot application and handles the main
     * event loop.
     */
    public static void main(String[] args) {
        Chatbot sara = new Chatbot();
        sara.run();
    }
}


