package duke;

import duke.parse.command.Command;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.ui.UI;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    private final String myName = "Quack-NKN";
    private TaskList taskList = new TaskList();
    private Storage storage = new Storage("task-list.txt");
    private UI ui = new UI("Quack, ", "!");

    public Duke() {
        interact();
    }

    /**
     * Invoked at the start before interaction.
     * Read data from file and save to the list of task.
     * Report whether the program should continue or exit by the returned value.
     * If there is an error in writing file, the program should not continue.
     * @return whether the program can continue, true if it can, false otherwise.
     */
    private void readFromDisk() throws Storage.FileCorruptedException {
        this.ui.notifyDataLoading();
        try {
            ArrayList<Task> taskList = this.storage.readFromDisk();
            this.taskList = new TaskList(taskList);
            this.ui.notifyDataLoaded();
        } catch (Storage.FileIOException e) {
            this.ui.notifyLoadingIOError();
        }
    }

    public void exit() {
        this.ui.exit();
    }

    /**
     * Main programme to allow user to input and respond accordingly.
     * Available commands:
     * - bye: to exit the programme
     * - list: to list out the current task list
     * - list {date}: to list out all events happening on that date or deadlines before/on that date
     * - list {todo/deadline/event}: list out all todo items / deadline items / event items
     * - mark {number}: to mark the task with the corresponding index in the list as done
     * - unmark {number}: to mark the task with the corresponding index in the list as not done
     * - todo {taskname}: to add a new task as a to-do item (no deadline or time)
     * - event {taskname} /from {starttime} /to {endtime}: to add a new task as an event (with start time and end time)
     * - deadline {taskname} /by {time}: to add a new task as a deadline (with deadline time)
     * Error handling is done in the individual functions, not here.
     * Datetime format: "{date} {time}"
     * Date format: either "today", "tmr", "tomorrow", or DD/MM/YYYY
     * Time format: either "{HH}am", "{HH}pm", "{HH:MM}am" or "{HH:MM}pm"
     */
    private void interact() {
        try {
            this.readFromDisk();
        } catch (Storage.FileCorruptedException e) {
            boolean isContinuing = this.ui.handleFileCorrupted();
            if (!isContinuing) {
                return;
            }
        }

        this.ui.start(this.myName);
        boolean isContinuing = true;
        while (isContinuing) {
            // receive input
            String input = this.ui.takeInput("In: ");
            try {
                Command command = Parser.parse(input);
                isContinuing = command.execute(this);
            } catch (Parser.ParseError e) {
                this.ui.notifyError(e.getMessage());
            }
        }
    }

    /**
     * Notify user of how many tasks are currently in the task list.
     */
    private void showTaskCount() {
        this.ui.showTaskCount(this.taskList.size());
    }

    public void showList(boolean isExcludingDone, LocalDate date) {
        this.ui.notifyList(UI.Type.DEFAULT, isExcludingDone, date);
        this.taskList.displayTasks(isExcludingDone, date);
    }

    public void showTodos(boolean isExcludingDone) {
        this.ui.notifyList(UI.Type.TODO, isExcludingDone, null);
        this.taskList.displayTodos(isExcludingDone);
    }

    public void showDeadlines(boolean isExcludingDone, LocalDate date) {
        this.ui.notifyList(UI.Type.DEADLINE, isExcludingDone, date);
        this.taskList.displayDeadlines(isExcludingDone, date);
    }

    public void showEvents(boolean isExcludingDone, LocalDate date) {
        this.ui.notifyList(UI.Type.EVENT, isExcludingDone, date);
        this.taskList.displayEvents(isExcludingDone, date);
    }

    public void addTaskToList(Task task) {
        this.taskList.add(task);
        this.ui.notifyAdded(task);
        this.ui.showTaskCount(this.taskList.size());
    }

    /**
     * Perform input checking and mark a task with corresponding index as done.
     * @param index the index of task to remove
     */
    public void markTaskAsDone(int index) {
        try {
            Task task = this.taskList.markTaskAsDone(index);
            this.ui.notifyMarkDone(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.ui.notifyError("invalid task index");
        }
    }

    /**
     * Perform input checking and mark a task with corresponding index as not done.
     * @param index the index of task to remove
     */
    public void markTaskAsNotDone(int index) {
        try {
            Task task = this.taskList.markTaskAsNotDone(index);
            this.ui.notifyMarkNotDone(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.ui.notifyError("invalid task index");
        }
    }

    /**
     * Perform input checking and delete the task if input is valid, given a command.
     * @param index the index of the task
     */
    public void deleteTask(int index) {
        try {
            Task taskDeleted = this.taskList.deleteTask(index);
            this.ui.notifyRemoved(taskDeleted);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.ui.notifyError("invalid task index");
        }
    }

    /**
     * Save data to hard disk, with the current task list.
     */
    public void saveData() {
        this.ui.notifyDataSaving();
        try {
            this.taskList.saveData(this.storage);
            this.ui.notifyDataSaved();
        } catch (Storage.FileIOException e) {
            this.ui.notifyError("an error has occurred while writing to hard disk");
        }
    }

    /**
     * Echo command back to the user.
     * @param input the input from the user
     */
    public void echo(String input) {
        this.ui.echo(input);
    }

    /**
     * Finds a task based on the command from the user.
     * @param input the input from the user
     */
    public void find(String input) {
        this.ui.notifyFind(input);
        this.taskList.showResults(input);
    }

    public static void main(String[] args) {
        new Duke();
    }
}
