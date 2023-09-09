package pooh;

/**
 * Responsible for parsing user input and executing commands.
 * <p>
 * This class takes care of interpreting user commands and then calling appropriate
 * methods to perform the actions described by those commands. It supports a variety
 * of commands including task listing, marking tasks as done or undone, and more.
 * </p>
 */
public class Parser {

    /**
     * Parses and executes the command specified in the given user input.
     *
     * @param taskStorage The storage object responsible for task storage operations.
     * @param taskList    The task list that holds all tasks.
     * @param userInput   The string input from the user.
     * @throws UnrecognizedCommandException If the command is not recognized.
     * @throws InvalidTaskException         If the task specified for certain operations is invalid.
     * @throws WriteTasksException          If there is an error while writing tasks to storage.
     */
    public String parseInput(Storage taskStorage, TaskList taskList, Ui ui, String userInput) throws
            UnrecognizedCommandException, InvalidTaskException, WriteTasksException {
        String userAction = userInput.split(" ")[0];
        if (userAction.equalsIgnoreCase("list")) {
            return ui.getTasksMessage(taskList);
        } else {
            switch (userAction.toLowerCase()) {
            case "bye":
                taskStorage.writeTask(taskList);
                return ui.getExitMessage();
            case "mark":
                int markIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task markTask = taskList.getTask(markIndex);
                markTask.markAsDone();
                taskStorage.writeTask(taskList);
                return ui.getTaskDoneMessage(markTask);
            case "unmark":
                int unmarkIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task unmarkTask = taskList.getTask(unmarkIndex);
                unmarkTask.markAsUndone();
                taskStorage.writeTask(taskList);
                return ui.getTaskUndoneMessage(unmarkTask);
            case "todo":
            case "event":
            case "deadline":
                try {
                    TaskList.addTask(taskList, userAction, userInput);
                    Task task = taskList.getTask(taskList.getSize() - 1);
                    taskStorage.writeTask(taskList);
                    return ui.getAddTaskMessage(taskList, task);
                } catch (EmptyTaskDescriptorsException ex) {
                    return ui.respond(ex.toString());
                }
            case "delete":
                int delIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = taskList.getTask(delIndex);
                TaskList.deleteTask(taskList, delIndex);
                taskStorage.writeTask(taskList);
                return ui.getDeleteTaskMessage(taskList, task);
            case "find":
                String keyword = userInput.split(" ")[1];
                TaskList keywordTaskList = taskList.getTasksWithKeyword(keyword);
                if (keywordTaskList.getSize() >= 1) {
                    return ui.getKeywordTasksMessage(keywordTaskList);
                } else {
                    return ui.getNoKeywordTasksFoundMessage();
                }
            default:
                throw new UnrecognizedCommandException();
            }
        }
    }
}
