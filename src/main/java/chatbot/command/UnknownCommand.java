package chatbot.command;

import chatbot.ChatbotException;
import chatbot.task.TaskManager;
import chatbot.task.Task;
import java.util.List;



/**
 * class which handle unknown command extends abstract class command.
 */
public class UnknownCommand extends Command {
    private String userInput;

    /**
     * Constructor for this class.
     *
     * @param userInput userInput.
     */
    public UnknownCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskManager taskManager) throws ChatbotException {
        StringBuilder responseBuilder = new StringBuilder(); // Use a StringBuilder to accumulate the response

        if ("list".equalsIgnoreCase(userInput)) {
            List<Task> allTasks = taskManager.getAllTasks();
            if (allTasks.isEmpty()) {
                responseBuilder.append("There are no tasks in the list.");
            } else {
                for (int i = 1; i <= allTasks.size(); i++) {
                    responseBuilder.append(String.format("%s. ", i) +allTasks.get(i - 1).toString()).append("\n");;
                }
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int index = Integer.parseInt(userInput.split(" ")[1]);
                String response = taskManager.deleteTask(index);
                responseBuilder.append(response);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                responseBuilder.append("Please provide a valid task number to delete.");
            }
        } else if (userInput.startsWith("find")) {
            try {
                String keyword = userInput.split(" ")[1];
                List<Task> foundTasks = taskManager.findTaskByKeyboard(keyword);
                if (foundTasks.isEmpty()) {
                    responseBuilder.append("No tasks found with keyword: ").append(keyword);
                } else {
                    for (Task task : foundTasks) {
                        responseBuilder.append(task.toString()).append("\n");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                responseBuilder.append("Please provide a keyword to search for.");
            }
        } else {
            throw new ChatbotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return responseBuilder.toString().trim(); // Convert StringBuilder to String and return
    }


    @Override
    public boolean isExit() {
        return false;
    }

}
