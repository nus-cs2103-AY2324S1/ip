package duke.userio;

import duke.filemanagement.Storage;
import duke.task.*;

public class Parser  {
    private Ui ui;
    private TaskList taskList;
    private boolean botInUse;
    private Storage storage;
    public Parser(Ui ui, TaskList taskList, boolean botInUse, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.botInUse = botInUse;
    }
    public void updateSaveFile() {
        storage.saveFile(taskList.outputNumberedList());
    }
    public void listen(String input) throws InvalidUserInputException {
        if (input.equals("bye")) {
            botInUse = false;
            ui.bye();
        } else if (input.equals("list")) {
            String outputList = taskList.outputNumberedList();
            ui.list(outputList);
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            Task t = taskList.getTask(a-1);
            t.markAsUndone();
            ui.unmarkTask(t);
            updateSaveFile();
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            Task t = taskList.getTask(a-1);
            t.markAsDone();
            ui.markTask(t);
            updateSaveFile();
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                taskList.addTask(toDoTask);
                ui.toDoAdded(toDoTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.toDoMissingContent();
            }
        } else if (input.contains("deadline")) {
            try {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskList.addTask(deadlineTask);
                ui.deadlineAdded(deadlineTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.deadlineMissingContent();
            }
        } else if (input.contains("event")) {
            try {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                taskList.addTask(eventTask);
                ui.eventAdded(eventTask, taskList);
                updateSaveFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.eventMissingContent();
            }
        } else if (input.contains("delete")) {
            int a = Integer.parseInt(input.substring(7));
            Task toBeRemoved = taskList.getTask(a-1);
            taskList.deleteTask(a-1);
            ui.taskDeleted(toBeRemoved, taskList);
            updateSaveFile();
        } else if (input.contains("find")) {
            String inputToFind = input.substring(6);
            TaskList tempTL = new TaskList();
            for (int i = 0; i < taskList.getSize(); i++) {
                Task t = taskList.getTask(i);
                if (t.containString(inputToFind)) {
                    tempTL.addTask(t);
                }
            }
            ui.findResponse(tempTL.outputNumberedList());
        } else {
            throw new InvalidUserInputException();
        }
    }

    public boolean updateBotUsage() {
        return botInUse;
    }
}
