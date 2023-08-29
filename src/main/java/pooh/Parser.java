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
    public void parseInput(Storage taskStorage, TaskList taskList, String userInput) throws
            UnrecognizedCommandException, InvalidTaskException, WriteTasksException {
        String userAction = userInput.split(" ")[0];
        if (userAction.equalsIgnoreCase("list")) {
            Ui.printTasksMsg(taskList);
        } else {
            switch (userAction) {
            case "list":
                Ui.printTasksMsg(taskList);
            case "bye":
                Ui.exitMsg();
                System.exit(1);
            case "mark":
                int markIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task markTask = taskList.getTask(markIndex);
                markTask.markAsDone();
                Ui.taskDoneMsg(markTask);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task unmarkTask = taskList.getTask(unmarkIndex);
                unmarkTask.markAsUndone();
                Ui.taskUndoneMsg(unmarkTask);
                break;
            case "todo":
            case "event":
            case "deadline":
                try {
                    TaskList.addTask(taskList, userAction, userInput);
                } catch (EmptyTaskDescriptorsException ex) {
                    Ui.generalRespond(ex.toString());
                }
                break;
            case "delete":
                int delIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                TaskList.deleteTask(taskList, delIndex);
                break;
            default:
                throw new UnrecognizedCommandException();
            }
            taskStorage.writeTask(taskList);
        }
    }

}
