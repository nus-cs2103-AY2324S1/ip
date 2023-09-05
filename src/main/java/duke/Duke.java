package duke;

import duke.ui.graphic.MainWindow;
import javafx.application.Application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.parse.Parser;
import duke.parse.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.graphic.DukeApplication;
import duke.ui.text.TextUi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class, where the user interacts with the bot.
 * The bot accesses the storage through the Storage class,
 * standard in and out through the UI class,
 * manage the current task list through the TaskList class.
 */
public class Duke {
    private final String myName = "Quack-NKN";
    private TaskList taskList = new TaskList();
    private final Storage storage = new Storage("task-list.txt");
    private Ui userInterface;

    /**
     * Instantiate the bot, and starts the interaction immediately.
     */
    public Duke(boolean isCli, Ui userInterface) {
        this.userInterface = userInterface;
        if (isCli) {
            this.interact();
        } else {
            this.userInterface.initialise(this.myName, new String[] {});
        }
    }

    /**
     * Invoked at the start before interaction.
     * Reads data from file and save to the list of task.
     * If file is corrupted, the exception thrown from Storage instance is thrown.
     * If there is IO error, handle the exception gracefully,
     * and continue with an empty task list.
     */
    private void readFromDisk() throws Storage.FileCorruptedException {
        this.userInterface.notifyDataLoading();
        try {
            ArrayList<Task> taskList = this.storage.readFromDisk();
            this.taskList = new TaskList(taskList);
            this.userInterface.notifyDataLoaded();
        } catch (Storage.FileIoException e) {
            this.userInterface.notifyLoadingIoError();
        }
    }

    /**
     * Leaves an exit message.
     */
    public void exit() {
        this.userInterface.exit();
    }

    /**
     * Main programme to allow user to input and respond accordingly.
     * Available commands:
     * - bye/exit: to exit the programme
     * - list: to list out the current task list
     * - list {date}: to list out all events happening on that date or deadlines before/on that date
     * - list {todo/deadline/event}: list out all todo items / deadline items / event items
     * - list -d: list out all tasks not done
     * - mark {number}: to mark the task with the corresponding index in the list as done
     * - unmark {number}: to mark the task with the corresponding index in the list as not done
     * - todo {taskname}: to add a new task as a to-do item (no deadline or time)
     * - event {taskname} /from {starttime} /to {endtime}: to add a new task as an event (with start time and end time)
     * - deadline {taskname} /by {time}: to add a new task as a deadline (with deadline time)
     * Note that for list, a combination of options can be used, by separating them by space characters.
     * If there is an error in the input from user, notify the user.
     * Datetime format: "{date} {time}"
     * Date format: either "today", "tmr", "tomorrow", or DD/MM/YYYY
     * Time format: either "{HH:MM}", "{HH}am", "{HH}pm", "{HH:MM}am" or "{HH:MM}pm"
     */
    private void interact() {
        try {
            this.readFromDisk();
        } catch (Storage.FileCorruptedException e) {
            boolean isContinuing = this.userInterface.handleFileCorrupted();
            if (!isContinuing) {
                return;
            }
        }

        this.userInterface.initialise(this.myName, new String[] {});
        boolean isContinuing = true;
        while (isContinuing) {
            // receive input
            String input = this.userInterface.takeInput("In: ");
            try {
                Command command = Parser.parse(input);
                isContinuing = command.execute(this);
            } catch (Parser.ParseError e) {
                this.userInterface.notifyError(e.getMessage());
            }
        }
    }

    /**
     * Shows the list of task to the user, with the options included.
     * This does not filter task type (todo/deadline/event)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date date to display the deadlines before and events happening on,
     *             null if to not exclude any task by date
     */
    public void showList(boolean isExcludingDone, LocalDate date) {
        this.userInterface.notifyList(Ui.Type.DEFAULT, isExcludingDone, date);
        this.taskList.displayTasks(isExcludingDone, date, this.userInterface);
    }

    /**
     * Shows all to-do tasks with the given filters.
     * @param isExcludingDone whether to exclude tasks already done
     */
    public void showTodos(boolean isExcludingDone) {
        this.userInterface.notifyList(Ui.Type.TODO, isExcludingDone, null);
        this.taskList.displayTodos(isExcludingDone, this.userInterface);
    }

    /**
     * Shows all deadlines with the given filters.
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date to display deadlines before
     */
    public void showDeadlines(boolean isExcludingDone, LocalDate date) {
        this.userInterface.notifyList(Ui.Type.DEADLINE, isExcludingDone, date);
        this.taskList.displayDeadlines(isExcludingDone, date, this.userInterface);
    }

    /**
     * Shows all events with the given filters.
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date to display events happening on
     */
    public void showEvents(boolean isExcludingDone, LocalDate date) {
        this.userInterface.notifyList(TextUi.Type.EVENT, isExcludingDone, date);
        this.taskList.displayEvents(isExcludingDone, date, this.userInterface);
    }

    /**
     * Adds a task to
     * @param task
     */
    public void addTaskToList(Task task) {
        this.taskList.add(task);
        this.userInterface.notifyAdded(task);
        this.userInterface.showTaskCount(this.taskList.size());
    }

    /**
     * Mark a task with corresponding index as done.
     * Handles the case where task index is out of range.
     * @param index the index of task to remove
     */
    public void markTaskAsDone(int index) {
        try {
            Task task = this.taskList.markTaskAsDone(index);
            this.userInterface.notifyMarkDone(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.userInterface.notifyError("invalid task index");
        }
    }

    /**
     * Mark a task with corresponding index as not done.
     * Handles the case where task index is out of range.
     * @param index the index of task to remove
     */
    public void markTaskAsNotDone(int index) {
        try {
            Task task = this.taskList.markTaskAsNotDone(index);
            this.userInterface.notifyMarkNotDone(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.userInterface.notifyError("invalid task index");
        }
    }

    /**
     * Delete the task with the given index.
     * Handles the case where the task index is out of range.
     * @param index the index of the task
     */
    public void deleteTask(int index) {
        try {
            Task taskDeleted = this.taskList.deleteTask(index);
            this.userInterface.notifyRemoved(taskDeleted);
        } catch (TaskList.TaskIndexOutOfRange e) {
            this.userInterface.notifyError("invalid task index");
        }
    }

    /**
     * Save data to hard disk, with the current task list.
     */
    public void saveData() {
        this.userInterface.notifyDataSaving();
        try {
            this.taskList.saveData(this.storage);
            this.userInterface.notifyDataSaved();
        } catch (Storage.FileIoException e) {
            this.userInterface.notifyError("an error has occurred while writing to hard disk");
        }
    }

    /**
     * Echo command back to the user.
     * @param input the input from the user
     */
    public void echo(String input) {
        if (input.equals("quack")) {
            this.userInterface.displayData("Quack quack quack");
        } else {
            this.userInterface.displayData("Quack, what do you mean when you say " + input);
        }
    }

    /**
     * Finds a task based on the command from the user.
     * @param input the input from the user
     */
    public void find(String input) {
        this.userInterface.notifyFind(input);
        this.taskList.showResults(input, this.userInterface);
    }

    public static void main(String[] args) {
//        new Duke(true, new TextUi("Quack, ", "!"));
        Application.launch(DukeApplication.class, args);
    }
}
