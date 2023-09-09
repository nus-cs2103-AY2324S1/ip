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
        if (input.equals("bye")) {
            botInUse = false;
            return ui.bye();
        } else if (input.equals("list")) {
            String outputList = taskList.outputNumberedList();
            return ui.list(outputList);
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            Task t = taskList.getTask(a - 1);
            t.markAsUndone();
            updateSaveFile();
            return ui.unmarkTask(t);
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            Task t = taskList.getTask(a - 1);
            t.markAsDone();
            updateSaveFile();
            return ui.markTask(t);
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                taskList.addTask(toDoTask);
                updateSaveFile();
                return ui.toDoAdded(toDoTask, taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.toDoMissingContent();
            }
        } else if (input.contains("deadline")) {
            try {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskList.addTask(deadlineTask);
                updateSaveFile();
                return ui.deadlineAdded(deadlineTask, taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.deadlineMissingContent();
            }
        } else if (input.contains("event")) {
            try {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                taskList.addTask(eventTask);
                updateSaveFile();
                return ui.eventAdded(eventTask, taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.eventMissingContent();
            }
        } else if (input.contains("delete")) {
            int a = Integer.parseInt(input.substring(7));
            Task toBeRemoved = taskList.getTask(a - 1);
            taskList.deleteTask(a - 1);
            updateSaveFile();
            return ui.taskDeleted(toBeRemoved, taskList);
        } else if (input.contains("find")) {
            String inputToFind = input.substring(6);
            TaskList tempTL = new TaskList();
            for (int i = 0; i < taskList.getSize(); i++) {
                Task t = taskList.getTask(i);
                if (t.containString(inputToFind)) {
                    tempTL.addTask(t);
                }
            }
            return ui.findResponse(tempTL.outputNumberedList());
        } else {
            throw new InvalidUserInputException();
        }
    }

    /**
     * Updates bot usage.
     * @return A boolean to state whether bot is in use.
     */
    public boolean updateBotUsage() {
        return botInUse;
    }
}
