package duke.message;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represent a message that can be displayed in the GUI.
 */
public class Message extends HBox {
    private static final Image USER_IMAGE =
            new Image("https://se-education.org/guides/tutorials/images/javafx/DaUser.png");
    private static final Image BOT_IMAGE =
            new Image("https://se-education.org/guides/tutorials/images/javafx/DaDuke.png");
    private final String content;

    private final Label label;
    private ImageView image;
    /**
     * Constructs a message with the given content.
     *
     * @param content The content of the message.
     */
    public Message(String content) {
        this.content = content;
        //Set label contents and formatting
        this.label = new Label();
        label.setText(content);
        label.setWrapText(true);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.label);
    }

    /**
     * Constructs a message with the given content and image.
     *
     * @param content The content of the message.
     * @param image   The image associated with the message.
     */
    public Message(String content, Image image) {
        this(content);
        this.image = new ImageView();
        //Set image contents and formatting
        this.image.setImage(image);
        this.image.setFitWidth(100.0);
        this.image.setFitHeight(100.0);
        this.getChildren().addAll(this.image);
    }
    /**
     * Converts a list of tasks into a list of messages.
     *
     * @param taskList The list of tasks to convert.
     * @return A list of messages representing the tasks.
     */
    public static List<Message> convertTasks(TaskList taskList) {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            messages.add((new Message((i + 1) + ". " + taskList.getTask(i).toString())));
        }
        return messages;
    }

    public static Message onGreeting(String name) {
        return new Message("Hello, I'm " + name + ".\n" + "What can I do for you?");
    }

    public static Message onExit() {
        return new Message("Bye. Hope to see you again soon!");
    }

    public static Message onTaskAdd(Task task) {
        return new Message("added: " + task.toString());
    }

    public static Message onTaskComplete(Task task) {
        return new Message("Nice! I've marked this task as done:\n" + task.toString());
    }
    public static Message onTaskUncomplete(Task task) {
        return new Message("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public static Message onTaskDelete(Task task) {
        return new Message("I've removed this task: \n" + task.toString());
    }

    public static Message onTaskFind() {
        return new Message("Here are the matching tasks in your list");
    }
    public static Message onList(TaskList tasks) {
        return new Message("There are " + tasks.getTaskCount() + " tasks in the list.");
    }

    public Message chainTo(Message message, String splitString) {
        return new Message(this.content + splitString + message.content);
    }

    /**
     * Accumulates a list of messages into a single message with specified splitting text.
     * Messages in the list are concatenated together with the specified splitting text.
     *
     * @param messages    The list of messages to accumulate.
     * @param splitString The text to be used for splitting the messages.
     * @return A message containing the accumulated text.
     */
    public static Message accumulateList(List<? extends Message> messages, String splitString) {
        Message chainedMessage = new Message("");
        if (messages.size() > 0) {
            chainedMessage = messages.get(0);
            for (int i = 1; i < messages.size(); i++) {
                Message message = messages.get(i);
                chainedMessage = chainedMessage.chainTo(message, splitString);
            }
        }

        return chainedMessage;
    }

    /**
     * Flips the message, changing its alignment and layout.
     * This method creates a new message with the same content but with the image on the left side.
     *
     * @return A flipped message.
     */
    public Message flip() {
        Message flippedMessage = new Message(this.content, this.image.getImage());
        flippedMessage.setAlignment(Pos.TOP_LEFT);
        flippedMessage.getChildren().clear();
        flippedMessage.getChildren().addAll(flippedMessage.image, flippedMessage.label);
        return flippedMessage;
    }
    /**
     * Formats the message such that it appears on the users side.
     * @return A message from the user.
     */
    public Message fromUser() {
        return new Message(this.content, USER_IMAGE);
    }
    /**
     * Formats the message such that it appears on the bots side.
     * @return A message from the bot.
     */
    public Message fromDuke() {
        return new Message(this.content, BOT_IMAGE).flip();
    }

    @Override
    public String toString() {
        return content;
    }

}
