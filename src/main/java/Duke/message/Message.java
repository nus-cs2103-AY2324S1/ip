package Duke.message;

import Duke.task.Task;
import Duke.application.Application;

import java.util.ArrayList;
import java.util.List;

public class Message {
    final private static String horizontalLine = "____________________________________________________________";
    final private String content;

    public Message(String content) {
        this.content = content;
    }

    public static List<Message> ConvertTasks(Application application) {
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < application.Size(); i++) {
            messages.add((new Message((i + 1) + ". " + application.GetTask(i).toString())));
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
    public static Message OnTaskDelete(Task task) {
        return new Message ("I've removed this task: \n" + task.toString());
    }

    public static Message NumberOfTasks(Application tasks) {
        return new Message ("There are " + tasks.Size() + " tasks in the list.");
    }
    public Message ChainTo(Message message, String splitString) {
        return new Message(this.content + splitString + message.content);
    }

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

    public void Print() {
        System.out.println(horizontalLine);
        System.out.println(this.ToString());
        System.out.println(horizontalLine);
    }
    public String ToString() {
        return content;
    }

}
