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
        if ("list".equalsIgnoreCase(userInput)) {
            return listTasks(taskManager);
        } else if (userInput.startsWith("delete")) {
            return deleteTask(taskManager);
        } else if (userInput.startsWith("find")) {
            return findTasks(taskManager);
        } else {
            throw new ChatbotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String listTasks(TaskManager taskManager) {
        StringBuilder responseBuilder = new StringBuilder();
        List<Task> allTasks = taskManager.getAllTasks();

        if (allTasks.isEmpty()) {
            responseBuilder.append("There are no tasks in the list.");
        } else {
            for (int i = 1; i <= allTasks.size(); i++) {
                responseBuilder.append(String.format("%s. ", i))
                        .append(allTasks.get(i - 1).toString())
                        .append("\n");
            }
        }

        return responseBuilder.toString().trim();
    }

    private String deleteTask(TaskManager taskManager) {
        StringBuilder responseBuilder = new StringBuilder();

        try {
            int index = Integer.parseInt(userInput.split(" ")[1]);
            String response = taskManager.deleteTask(index);
            responseBuilder.append(response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            responseBuilder.append("Please provide a valid task number to delete.");
        } catch (ChatbotException e) {
            throw new RuntimeException(e);
        }

        return responseBuilder.toString().trim();
    }

    private String findTasks(TaskManager taskManager) {
        StringBuilder responseBuilder = new StringBuilder();

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

        return responseBuilder.toString().trim();
    }



    @Override
    public boolean isExit() {
        return false;
    }

}
