package chatbot;

import chatbot.command.Command;
import chatbot.task.TaskManager;

import java.util.Timer;
import java.util.TimerTask;

public class Chatbot {
    private TaskManager taskManager;
    public Chatbot() {
        this.taskManager = new TaskManager();
        assert taskManager != null : "Task Manager must be initialise";
    }

    /**
     * Process user input and return bot response.
     *
     * @param input user input
     * @return string bot response
     */
    public String processUserInput(String input) {
        assert input != null : "Input cannot be empty";
        try {
            Command command = Parser.parseCommand(input);
            assert command != null : "command cannot be empty";
            String botResponse = command.execute(taskManager);
            if (command.isExit()) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);  // Terminates the currently running Java Virtual Machine
                    }
                }, 1000);  // Delay in milliseconds (3000ms = 3s)

                return botResponse;
            }
            return botResponse;
        } catch (ChatbotException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred.";
        }
    }
}
