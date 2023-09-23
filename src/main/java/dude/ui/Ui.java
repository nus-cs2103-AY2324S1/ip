package dude.ui;

import java.util.Scanner;

import dude.note.Note;
import dude.note.NoteList;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Represents the UI of Dude and deals with interactions with the user.
 */
public class Ui {

    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message for users.
     */
    public static String showWelcome() {
        String greeting = "sup dude, whatchu need?";
        return greeting;
    }

    /**
     * Prints a farewell message for users.
     */
    public static String showFarewell() {
        String greeting = "bye dude";
        return greeting;
    }

    /**
     * Prints the list of tasks.
     */
    public String showTaskList(TaskList taskList) {
        int nTasks = taskList.getSize();
        String taskListString = "---tasks---\n";

        if (nTasks == 0) {
            taskListString = "no tasks atm, you're a free fella.\n";
        } else {
            for (int i = 0; i < nTasks; i++) {
                Task task = taskList.getTask(i);
                if (task != null) {
                    taskListString = taskListString + String.format("%d. %s\n", i + 1, task.toString());
                }
            }
        }
        return taskListString;
    }

    /**
     * Prints the list of notes.
     */
    public String showNoteList(NoteList noteList) {
        int nNotes = noteList.getSize();
        String noteListString = "\n---notes---\n";

        if (nNotes == 0) {
            noteListString = "no thoughts head empty brotha.\n";
        } else {
            for (int i = 0; i < nNotes; i++) {
                Note note = noteList.getNote(i);
                if (note != null) {
                    noteListString = noteListString + String.format("%d. %s\n", i + 1, note.toString());
                }
            }
        }

        return noteListString;
    }

    /**
     * Prints a statement to show the task that has been successfully marked.
     */
    public String showMarkedTask(Task task) {
        String confirmation = "noice. one task down:\n"
                + task.toString() + "\n";
        return confirmation;
        // task is already done?
    }

    /**
     * Prints a statement to show the task that has been successfully unmarked.
     */
    public String showUnmarkedTask(Task task) {
        String confirmation = "gosh darn... thought you were done:\n"
                + task.toString() + "\n";
        return confirmation;
        // task is already undone?
    }

    /**
     * Prints a statement to show the task that has been successfully deleted.
     */
    public String showDeletedTask(Task task, int nTasks) {
        String confirmation = "i mean that's also... one task down:\n"
                + task.toString() + "\n"
                + String.format("now just %d tasks to go.\n", nTasks);
        return confirmation;
    }

    /**
     * Prints a statement to show the note that has been successfully deleted.
     */
    public String showDeletedNote(Note note, int nNotes) {
        String confirmation = "one less thought for the sole braincell:\n"
                + note.toString() + "\n"
                + String.format("%d thoughts left in brain.\n", nNotes);
        return confirmation;
    }

    /**
     * Prints a statement to show the task that has been successfully added.
     */
    public String showAddedTask(Task task, int nTasks) {
        String confirmation = "i gotchu... one more task to do:\n"
                + task.toString() + "\n"
                + String.format("now that's %d tasks to be done.\n", nTasks);
        return confirmation;
    }

    /**
     * Prints a statement to show the task that has been successfully added.
     */
    public String showAddedNote(Note note, int nNotes) {
        String confirmation = "uhhuh, hope i remember that:\n"
                + note.toString() + "\n"
                + String.format("%d thoughts currently in head.\n", nNotes);
        return confirmation;
    }

    /**
     * Prints a statement to show the command is unknown.
     */
    public String showUnknownCommand(String input) {
        String reply = String.format("bruh, what %s supposed to mean :-(\n", input);
        return reply;
    }
}
