package iris;

/**
 * The class responsible for parsing user commands in the Iris application.
 */
public class Parser {

    public String parseCommand(Storage taskStorage, TaskList taskList, Ui ui, String input) throws
            WriteTaskException, UnrecognizedCommandException, InvalidTaskException {
        String[] sections = input.split(" ", 2);
        String command = sections[0];
        String rest = sections.length > 1 ? sections[1] : "";
        switch (command.toLowerCase()) {
            case "bye": {
                taskStorage.writeTask(taskList);
                return ui.exitMessage();
            }

            case "list": {
                return ui.getTasksMessage(taskList);
            }
            case "todo":
            case "deadline":
            case "event":
                try {
                    TaskList.addTask(taskList, command, rest);
                    Task task = taskList.getTask(taskList.getSize() - 1);
                    taskStorage.writeTask(taskList);
                    return ui.getAddTaskMessage(taskList, task);
                } catch (EmptyTaskDescriptorsException e) {
                    ui.respond(e.toString());
                }
            case "mark": {
                int markIndex = Integer.parseInt(rest);
                Task markTask = taskList.getTask(markIndex);
                markTask.markAsDone();
                taskStorage.writeTask(taskList);
                return ui.markTaskMessage(markTask);
            }
            case "unmark": {
                int unmarkIndex = Integer.parseInt(rest);
                Task unmarkTask = taskList.getTask(unmarkIndex);
                unmarkTask.markAsUndone();
                taskStorage.writeTask(taskList);
                return ui.unmarkTaskMessage(unmarkTask);
            }
            case "delete": {
                int delIndex = Integer.parseInt(rest);
                Task task = taskList.getTask(delIndex);
                TaskList.deleteTask(taskList, delIndex);
                taskStorage.writeTask(taskList);
                return ui.getDeleteTaskMessage(taskList, task);
            }
            case "find": {
                TaskList keywordTaskList = taskList.getTasksWithKeyword(rest);
                if (keywordTaskList.getSize() >= 1) {
                    return ui.getKeywordTasksMessage(keywordTaskList);
                } else {
                    return ui.getNoKeywordTasksFoundMessage();
                }
            }
            default: {
                throw new UnrecognizedCommandException();
            }
        }
    }
}
