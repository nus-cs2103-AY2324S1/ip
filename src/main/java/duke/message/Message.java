package Duke.message;

import Duke.task.Task;
import Duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Message {
    final private static String horizontalLine = "____________________________________________________________";
    final private String content;

    public Message(String content) {
        this.content = content;
    }

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

    public void print() {
        System.out.println(horizontalLine);
        System.out.println(this);
        System.out.println(horizontalLine);
    }

    @Override
    public String toString() {
        return content;
    }

}
