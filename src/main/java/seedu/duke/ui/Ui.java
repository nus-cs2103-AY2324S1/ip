package seedu.duke.ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import seedu.duke.exceptions.InvalidTaskIndexException;
import seedu.duke.exceptions.LemonException;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Task;

/**
 * The Ui class is responsible for handling user interface interactions and displaying messages to the user.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */

    public Ui() {

    }

    /**
     * Displays a welcome message when the application starts.
     * @return String of the welcome message
     */
    public String welcomeMessage() {
        return ("Hello! I'm Lemon!" + System.lineSeparator() + "What can I do for you?");
    }

    /**
     * Displays an error message when there is an issue loading the storage file with tasks.
     * @return String of the error message.
     */
    public String showLoadingError() {
        return ("Error loading storage file!");
    }

    /**
     * Returns the message after marking a task as done.
     *
     * @param toMarkTask The task that was marked as done.
     * @param tasks The TaskList object containing the task list.
     * @return String of the message to display indicating task is completed.
     */
    public String markPrint(String toMarkTask, TaskList tasks) {
        String output = "";
        output += ("Nice! I've marked this task as done: " + System.lineSeparator() + toMarkTask + "\n");
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns the message after unmarking a task as undone.
     *
     * @param toUnmarkTask The task that was marked as undone.
     * @param tasks The TaskList object containing the task list.
     * @return String of the message to display indicating task is completed.
     */
    public String unmarkPrint(String toUnmarkTask, TaskList tasks) {
        String output = "";
        output += ("OK, I've marked this task as not done yet: " + System.lineSeparator() + toUnmarkTask + "\n");
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns the message after deleting a task.
     *
     * @param toDeleteTask The task that was deleted.
     * @param tasks The TaskList object containing the task list without the deleted task.
     * @return String of the message to display indicating the task been deleted.
     */
    public String deletePrint(String toDeleteTask, TaskList tasks) {
        String output = "";
        output += ("Noted. I've removed this task:" + System.lineSeparator() + toDeleteTask + System.lineSeparator());
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns the message after adding a task.
     *
     * @param toAddTask The task that was added.
     * @param tasks The TaskList object containing the task list with the added task.
     */
    public String addTasks(String toAddTask, TaskList tasks) {
        String output = "";
        output += ("Got it. I've added this task: " + toAddTask);
        output += System.lineSeparator();
        output += ("Now you have " + tasks.getTasksSize() + " tasks in the list." + System.lineSeparator());
        return output;
    }

    /**
     * Returns the message after rescheduling a deadline task.
     * @param changeDeadlineTask task to change
     * @param tasks task list with all tasks.
     * @return String representation of the task that has been rescheduled.
     */
    public String rescheduleDeadline(String changeDeadlineTask, TaskList tasks) {
        String output = "";
        output += ("Got it. I've rescheduled this task: " + System.lineSeparator() + changeDeadlineTask);
        return output;
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The TaskList object containing the task list.
     * @return String of all the tasks in the TaskList.
     */
    public String listAll(TaskList tasks) throws LemonException {
        String output = "";
        try {
            for (int i = 0; i < tasks.getTasksSize(); i++) {
                output += (i + 1 + ". " + tasks.getTask(i).toString() + System.lineSeparator());
            }
            output += System.lineSeparator();
            return output;
        } catch (InvalidTaskIndexException e) {
            throw new LemonException("Error loading all tasks!");
        }
    }

    /**
     * Lists all tasks that matches the keyword in the task list.
     *
     * @param tasks The TaskList object containing the task list.
     * @return String of the list of tasks which matches the keyword.
     */
    public String listMatching(ArrayList<Task> tasks) {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1 + ". " + tasks.get(i).toString() + System.lineSeparator());
        }
        output += System.lineSeparator();
        return output;
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public String bye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Creates a Dialog Box in chatbot, for both user's input &
     * the response.
     */
    public static class DialogBox extends HBox {

        private Text text;
        private ImageView displayPicture;

        /**
         * Constructor of a dialog box
         * @param l message input by user or response
         * @param iv the image of Lemon or User
         */
        public DialogBox(String l, Image iv) {
            text = new Text(l);
            ImageView imageView = new ImageView();
            Circle clip = new Circle(30, 30, 30);
            imageView.setClip(clip);
            imageView.setImage(iv);
            displayPicture = imageView;
            displayPicture.setFitWidth(60.0);
            displayPicture.setFitHeight(60.0);


            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        }

        /**
         * Flips the dialog box such that the ImageView is on the left and text on the right.
         */
        private void flip() {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }

        public static DialogBox getUserDialog(String l, Image iv) {
            return new DialogBox(l, iv);
        }

        public static DialogBox getDukeDialog(String l, Image iv) {
            var db = new DialogBox(l, iv);
            db.flip();
            return db;
        }
    }
}
