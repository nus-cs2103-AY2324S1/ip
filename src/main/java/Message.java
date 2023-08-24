import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class Message {
    final private static String horizontalLine = "____________________________________________________________";
    final private String content;

    public Message(String content) {
        this.content = content;
    }

    public static List<Message> ConvertTasks(List<? extends Task> tasks) {
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            messages.add((new Message((i + 1) + ". " + tasks.get(i).toString())));
        }
        return messages;
    }

    public static Message OnGreeting(String name) {
        return new Message("Hello, I'm " + name + ".\n" +
                "What can I do for you?");
    }

    public static Message OnExit(){
        return new Message("Bye. Hope to see you again soon!");
    }

    public static Message OnTaskAdd(Task task) {
        return new Message("added: " + task.toString());
    }

    public static Message OnTaskComplete(Task task) {
        return new Message("Nice! I've marked this task as done:\n" + task.toString());
    }
    public static Message OnTaskUncomplete(Task task) {
        return new Message("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public static Message OnInvalidTaskNo(String inputComponent) {
        return new Message( "Sorry, " + inputComponent + "isn't a number.");
    }

    public static Message NoCommandFound() {
        return new Message( "Sorry, I don't know what that means!");
    }

    public static Message EmptyTaskName() {
        return new Message ( "Sorry, a task needs a name!");
    }

    public static Message OnTaskIndexOutOfRange() {
        return new Message ( "Sorry, that task index is not in range!");
    }

    public static Message OnTaskDelete(Task task) {
        return new Message ("I've removed this task: \n" + task.toString());
    }

    public static Message NumberOfTasks(List<Task> tasks) {
        return new Message ("There are " + tasks.size() + " tasks in the list.");
    }

    public Message ChainTo(Message message, String splitString) {
        return new Message(this.content + splitString + message.content);
    }

    public static Message ChainList(List<? extends Message> messages, String splitString){
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

    public void Print() {
        System.out.println(horizontalLine);
        System.out.println(this.ToString());
        System.out.println(horizontalLine);
    }
    public String ToString() {
        return content;
    }

}
