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
        String greeting = "Hello, I'm Dude!\n"
                + "What can I do for you?";
        return greeting;
    }

    /**
     * Prints a farewell message for users.
     */
    public static String showFarewell() {
        String greeting = "Bye. Hope to see you again soon!";
        return greeting;
    }

    /**
     * Prints the list of tasks.
     */
    public String showTaskList(TaskList taskList) {
        int nTasks = taskList.getSize();
        String taskListString = "Tasks:\n";
        if (nTasks == 0) {
            taskListString = "You have no saved tasks.\n";
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
        if (noteList == null) {
            return "NoteList is null."; // Or handle this case appropriately
        }

        int nNotes = noteList.getSize();
        System.out.printf("Note List Size: %d", nNotes);
        String noteListString = "\nNotes:\n";
        if (nNotes == 0) {
            noteListString = "\nYou have no saved notes.\n";
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
        String confirmation = "Nice! I've marked this task as done:\n"
                + task.toString() + "\n";
        return confirmation;
        // task is already done?
    }

    /**
     * Prints a statement to show the task that has been successfully unmarked.
     */
    public String showUnmarkedTask(Task task) {
        String confirmation = "Nice! I've marked this task as as not done yet:\n"
                + task.toString() + "\n";
        return confirmation;
        // task is already undone?
    }

    /**
     * Prints a statement to show the task that has been successfully deleted.
     */
    public String showDeletedTask(Task task, int nTasks) {
        String confirmation = "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", nTasks);
        return confirmation;
    }

    /**
     * Prints a statement to show the note that has been successfully deleted.
     */
    public String showDeletedNote(Note note, int nNotes) {
        String confirmation = "Noted. I've removed this task:\n"
                + note.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", nNotes);
        return confirmation;
    }

    /**
     * Prints a statement to show the task that has been successfully added.
     */
    public String showAddedTask(Task task, int nTasks) {
        String confirmation = "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", nTasks);
        return confirmation;
    }

    /**
     * Prints a statement to show the task that has been successfully added.
     */
    public String showAddedNote(Note note, int nNotes) {
        String confirmation = "Got it. I've added this note:\n"
                + note.toString() + "\n"
                + String.format("Now you have %d notes in the list.\n", nNotes);
        return confirmation;
    }

    /**
     * Prints a statement to show the command is unknown.
     */
    public String showUnknownCommand(String input) {
        String reply = String.format("OOPS!!! I'm sorry, but I don't know what %s means :-(\n", input);
        return reply;
    }
}
