package duke.utils;

import java.util.List;

import duke.exceptions.DukeException;

/**
 * User interface for the chatbot.
 */
public class Ui {

    private String farewell = "Goodbye. Hope to see you soon!";
    private String addTask = "A new task has been added!\n ";

    private String addNote = "A new note has been added! \n";

    /**
     * Bids farewell to the user.
     *
     * @return Farewell message.
     */
    public String farewell() {
        return farewell;
    }

    /**
     * Displays the type of input error.
     *
     * @param e The exception thrown.
     * @return Generic Duke error message.
     */
    public String showDukeError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Tells user to input the correct date format.
     *
     * @return Prompt for proper date format.
     */
    public String showDateError() {
        return "Date cannot be recognised :( please input a valid date format yyyy-mm-dd !";
    }

    /**
     * Tells user that there has been an error.
     *
     * @return General error message.
     */
    public String showGeneralError() {
        return "There has been an internal error. Please try again!";
    }

    /**
     * Confirms that a task has been added.
     *
     * @param taskDescription Description of the task added.
     * @return Confirmation of task added.
     */
    public String showTaskAdded(String taskDescription) {
        return addTask + taskDescription;
    }

    /**
     * Confirms that a note has been added.
     *
     * @param noteDescription Description of the note added.
     * @return Confirmation of note added.
     */
    public String showNoteAdded(String noteDescription) {
        return addNote + noteDescription;
    }

    /**
     * Displays a message when there are no tasks in the list.
     *
     * @return No tasks message.
     */
    public String showNoTasks() {
        return "You have no tasks! Yay :)";
    }

    /**
     * Displays a message when there are no notes in the list.
     *
     * @return No notes message.
     */
    public String showNoNotes() {
        return "There are no notes saved! Add some notes to view them :)";
    }

    /**
     * Lists out all the tasks in the list.
     *     0 represents all tasks, other numbers represent matching tasks.
     *
     * @param tasksDescriptions The list of descriptions of the tasks.
     * @return String representation of all tasks.
     */
    public String showTasks(List<String> tasksDescriptions, int type) {
        String output = "";
        if (tasksDescriptions.isEmpty()) {
            return showNoTasks();
        } else {
            if (type == 0) {
                output = "Here's your list of tasks!\n";
            } else {
                output = "Here's the matching tasks!\n";
            }
            for (int i = 0; i < tasksDescriptions.size(); i++) {
                String description = (i + 1) + ": " + tasksDescriptions.get(i) + "\n";
                output += description;
            }
            return output;
        }
    }

    /**
     * Lists out all the notes in the list.
     *
     * @param notesDescriptions The list of descriptions of the notes.
     * @return String representation of all notes.
     */
    public String showNotes(List<String> notesDescriptions) {
        String output = "";
        if (notesDescriptions.isEmpty()) {
            return showNoNotes();
        } else {
            output = "Here's your stored notes!\n";
            for (int i = 0; i < notesDescriptions.size(); i++) {
                String description = (i + 1) + ": " + notesDescriptions.get(i) + "\n";
                output += description;
            }
            return output;
        }
    }
}
