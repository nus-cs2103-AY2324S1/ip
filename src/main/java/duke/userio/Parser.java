package duke.userio;

import duke.filemanagement.Storage;
import duke.task.*;

/**
 * Deals with making sense of user command.
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private boolean botInUse;
    private Storage storage;

    /**
     *  Deals with making sense of user command.
     * @param ui Helps generate appropriate responses.
     * @param taskList TaskList that the bot uses.
     * @param botInUse Status of bot.
     * @param storage Storage that the bot uses.
     */
    public Parser(Ui ui, TaskList taskList, boolean botInUse, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.botInUse = botInUse;
    }

    /**
     * Update task content from task list into the task file.
     */
    private void updateSaveFile() {
        storage.saveFile(taskList.outputNumberedList());
    }

    /**
     * Takes in user input and generate responses accordingly.
     * @param input User input.
     * @throws InvalidUserInputException User input does not generate any specific response.
     */
    public String listen(String input) throws InvalidUserInputException {
        String response;
        int taskIndexHolder;
        String keyword = extractKeyword(input);

        switch (keyword) {
            case "bye":
                botInUse = false;
                response = ui.bye();
                break;
            case "list":
                String outputList = taskList.outputNumberedList();
                response = ui.list(outputList);
                break;
            case "unmark":
                taskIndexHolder = Integer.parseInt(input.substring(7));
                Task taskToBeUnmarked = taskList.getTask(taskIndexHolder - 1);
                taskToBeUnmarked.markAsUndone();
                updateSaveFile();
                response = ui.unmarkTask(taskToBeUnmarked);
                break;
            case "mark":
                taskIndexHolder = Integer.parseInt(input.substring(5));
                Task taskToBeMarked = taskList.getTask(taskIndexHolder - 1);
                taskToBeMarked.markAsDone();
                updateSaveFile();
                response = ui.markTask(taskToBeMarked);
                break;
            case "todo":
                try {
                    String toDoDescription = input.split("todo ")[1];
                    ToDo toDoTask = new ToDo(toDoDescription);
                    taskList.addTask(toDoTask);
                    updateSaveFile();
                    response = ui.toDoAdded(toDoTask, taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response = ui.toDoMissingContent();
                }
                break;
            case "deadline":
                try {
                    String by = input.split("/by ")[1];
                    String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                    Deadline deadlineTask = new Deadline(deadlineDescription, by);
                    taskList.addTask(deadlineTask);
                    updateSaveFile();
                    response = ui.deadlineAdded(deadlineTask, taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response = ui.deadlineMissingContent();
                }
                break;
            case "event":
                try {
                    String from = input.split("/from ")[1].split(" /to")[0];
                    String to = input.split("/to ")[1];
                    String eventDescription = input.split("event ")[1].split(" /from")[0];
                    Event eventTask = new Event(eventDescription, from, to);
                    taskList.addTask(eventTask);
                    updateSaveFile();
                    response = ui.eventAdded(eventTask, taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response = ui.eventMissingContent();
                }
                break;
            case "delete":
                taskIndexHolder = Integer.parseInt(input.substring(7));
                Task toBeRemoved = taskList.getTask(taskIndexHolder - 1);
                taskList.deleteTask(taskIndexHolder - 1);
                updateSaveFile();
                response = ui.taskDeleted(toBeRemoved, taskList);
                break;
            case "find":
                String inputToFind = input.substring(5);
                System.out.println(inputToFind);
                TaskList tempTl = new TaskList();
                for (int i = 0; i < taskList.getSize(); i++) {
                    Task t = taskList.getTask(i);
                    if (t.containsString(inputToFind)) {
                        tempTl.addTask(t);
                    }
                }
                response = ui.findResponse(tempTl.outputNumberedList());
                break;
            case "update":
                try {
                    String substringAfterUpdate = input.substring(7);
                    int taskIndex = Integer.parseInt(substringAfterUpdate.split(" ")[0]) - 1;
                    String componentToUpdate = substringAfterUpdate.split(" ")[1];
                    String contentForUpdate = substringAfterUpdate.split(" ")[2];
                    taskList.updateTask(taskIndex, componentToUpdate, contentForUpdate);
                    String updatedList = taskList.outputNumberedList();
                    response = ui.updateResponse(updatedList);
                    break;
                } catch (Exception e) {
                    response = ui.updateFailedResponse();
                }
                break;
            default:
                throw new InvalidUserInputException();
        }
        assert response != null : "There should be responses produced no matter what input given";
        return response;
    }

    /**
     * Returns the keyword of user's input.
     * @param input user's input.
     * @return Keyword of user's input.
     */
    private String extractKeyword(String input) {
        return input.split(" ")[0];
    }

    /**
     * Updates bot usage.
     * @return A boolean to state whether bot is in use.
     */
    public boolean updateBotUsage() {
        return botInUse;
    }
}
