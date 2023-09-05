package duke.ui;

import duke.task.Task;

import java.time.LocalDate;

public interface Ui {
    /**
     * Initialises the UI.
     * @param name the name of the bot to be displayed to user.
     * @param args system arguments
     */
    void initialise(String name, String[] args);

    /**
     * Notifies the user that data is being loaded.
     */
    void notifyDataLoading();

    /**
     * Notifies the user that data has been loaded.
     */
    void notifyDataLoaded();

    /**
     * Notifies the user that data could not be loaded due to IO error.
     */
    void notifyLoadingIoError();

    /**
     * Notifies the user that data is corrupted and allow user to take action.
     */
    boolean handleFileCorrupted();

    /**
     * Takes input from the user.
     * @return the input from the user
     */
    String takeInput(String prompt);

    /**
     * Leave an exit message.
     */
    void exit();

    /**
     * Notifies user-input error.
     */
    void notifyError(String message);

    /**
     * Notifies user that a task has been marked done.
     * @param task the task to notify
     */
    void notifyMarkDone(Task task);

    /**
     * Notifies user that a task has been marked as not done.
     * @param task the task to notify
     */
    void notifyMarkNotDone(Task task);

    /**
     * Notifies that a task has been removed.
     * @param task the task removed
     */
    void notifyRemoved(Task task);

    /**
     * Denotes the type of task to be indicated.
     */
    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DEFAULT
    }

    /**
     * Notifies that a list of task is going to be displayed.
     * Does not display the tasks itself.
     * @param type type of task (todo/deadline/event/default)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date before which to display deadlines before or events happening on,
     *             null if not to filter by date
     */
    void notifyList(Type type, boolean isExcludingDone, LocalDate date);

    /**
     * Notifies that a task has been added.
     * @param task the task added
     */
    void notifyAdded(Task task);

    /**
     * Notifies that data is being saved to disk.
     */
    void notifyDataSaving();

    /**
     * Notifies the user that data has been saved to disk.
     */
    void notifyDataSaved();

    /**
     * Show task count.
     * @param count the number of task in the list
     */
    void showTaskCount(int count);

    /**
     * Notify the user of the search results.
     * @param input the search parameter
     */
    void notifyFind(String input);

    /**
     * Display custom data
     */
    void displayData(String data);
}
