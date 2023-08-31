package Duke.message;

import Duke.task.Task;
import Duke.application.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents messages that the Duke application can generate and display to the user.
 */
public class Message {
    final private static String horizontalLine = "____________________________________________________________";
    final private String content;

    /**
     * Constructs a Message object with the provided content.
     *
     * @param content The content of the message.
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * Converts a list of tasks into a list of messages.
     *
     * @param application The application instance containing the tasks.
     * @return A list of messages representing the tasks.
     */
    public static List<Message> ConvertTasks(Application application) {
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < application.TaskCount(); i++) {
            messages.add((new Message((i + 1) + ". " + application.GetTask(i).toString())));
        }
        return messages;
    }

    /**
     * Generates a greeting message with the specified name.
     *
     * @param name The name of the Duke instance.
     * @return A greeting message.
     */
    public static Message OnGreeting(String name) {
        return new Message("Hello, I'm " + name + ".\n" +
                "What can I do for you?");
    }

    /**
     * Generates a message for exiting the application.
     *
     * @return A goodbye message.
     */
    public static Message OnExit(){
        return new Message("Bye. Hope to see you again soon!");
    }

    /**
     * Generates a message for task addition.
     *
     * @param task The added task.
     * @return A message indicating the task has been added.
     */
    public static Message OnTaskAdd(Task task) {
        return new Message("added: " + task.toString());
    }

    public static Message OnTaskComplete(Task task) {
        return new Message("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Generates a message for marking a task as completed.
     *
     * @param task The completed task.
     * @return A message confirming the task completion.
     */
    public static Message OnTaskUncomplete(Task task) {
        return new Message("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Generates a message for task deletion.
     * @param task The task to remove.
     * @return A message confirming the task deletion.
     */
    public static Message OnTaskDelete(Task task) {
        return new Message ("I've removed this task: \n" + task.toString());
    }

    /**
     * Generates a message displaying the number of tasks.
     * @param application The application instance containing the task list.
     * @return A message with the number of tasks.
     */
    public static Message NumberOfTasks(Application application) {
        return new Message ("There are " + application.TaskCount() + " tasks in the list.");
    }

    /**
     * Chains this message with another message using a split string.
     *
     * @param message The message to chain with.
     * @param splitString The string used to split the messages.
     * @return A chained message.
     */
    public Message ChainTo(Message message, String splitString) {
        return new Message(this.content + splitString + message.content);
    }

    /**
     * Accumulates a list of messages into a single message, separated by a split string.
     *
     * @param messages The list of messages to accumulate.
     * @param splitString The string used to split the messages.
     * @return An accumulated message.
     */
    public static Message AccumulateList(List<? extends Message> messages, String splitString){
        Message chainedMessage = new Message("");
        if(messages.size() > 0){
            chainedMessage = messages.get(0);
            for (int i = 1; i < messages.size(); i++)
            {
                Message message = messages.get(i);
                chainedMessage = chainedMessage.ChainTo(message, splitString);
            }
        }

        return chainedMessage;
    }

    /**
     * Prints the message to the console.
     */
    public void Print() {
        System.out.println(horizontalLine);
        System.out.println(this.ToString());
        System.out.println(horizontalLine);
    }

    /**
     * Returns the content of the message as a string.
     *
     * @return The content of the message.
     */
    public String ToString() {
        return content;
    }

}
