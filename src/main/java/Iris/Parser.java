package iris;

/**
 * The class responsible for parsing user commands in the Iris application.
 */
public class Parser {

    public String parseCommand(Storage taskStorage, TaskList taskList, Ui ui, String input) throws
            WriteTaskException, UnrecognizedCommandException, InvalidTaskException {
        String[] sections = input.split(" ", 2);
        String command = sections[0];
        String description = sections.length > 1 ? sections[1] : "";
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
                    TaskList.addTask(taskList, command, description);
                    Task task = taskList.getTask(taskList.getSize());
                    taskStorage.writeTask(taskList);
                    return ui.getAddTaskMessage(taskList, task);
                } catch (EmptyTaskDescriptorsException e) {
                    ui.respond(e.toString());
                }
            case "mark": {
                int markIndex = Integer.parseInt(description);
                Task markTask = taskList.getTask(markIndex);
                markTask.markAsDone();
                taskStorage.writeTask(taskList);
                return ui.markTaskMessage(markTask);
            }
            case "unmark": {
                int unmarkIndex = Integer.parseInt(description);
                Task unmarkTask = taskList.getTask(unmarkIndex);
                unmarkTask.markAsUndone();
                taskStorage.writeTask(taskList);
                return ui.unmarkTaskMessage(unmarkTask);
            }
            case "delete": {
                int delIndex = Integer.parseInt(description);
                Task task = taskList.getTask(delIndex);
                TaskList.deleteTask(taskList, delIndex);
                taskStorage.writeTask(taskList);
                return ui.getDeleteTaskMessage(taskList, task);
            }
            case "find": {
                TaskList keywordTaskList = taskList.getTasksWithKeyword(description);
                if (keywordTaskList.getSize() >= 1) {
                    return ui.getKeywordTasksMessage(keywordTaskList);
                } else {
                    return ui.getNoKeywordTasksFoundMessage();
                }
            }
            case "postpone": {
                try {
                    String[] postponeSections = description.split(" ", 2);
                    int postponeIndex = Integer.parseInt(postponeSections[0]);
                    String deadlineString = postponeSections[1];
                    Task postponedTask = taskList.postponeTimeSensitiveTask(postponeIndex, deadlineString);
                    taskStorage.writeTask(taskList);
                    return ui.getPostponeTaskMessage(postponedTask);
                } catch (NotTimeSensitiveTaskException ex) {
                    return ui.getUnableToPostponeTaskMessage();
                }
            }
            default: {
                throw new UnrecognizedCommandException();
            }
        }
    }
}
