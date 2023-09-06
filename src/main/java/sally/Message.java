package sally;

public class Message {

    public Message() {
    }

    public String deleteMessage(Task task, int size) {
        return "Noted. I've removed this task:" + "\n" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    public String listMessage(TaskList tasks) {
        String res = "My list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + "." + tasks.getTask(i);
        }
        return res;
    }

    public String markMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    public String unmarkMessage(Task task) {
        return "Ok, I've marked this task as not done yet:" + "\n" + task;
    }

    public String addMessage(Task task, int size) {
        return "Added to My List: " + "\n" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    public String findMessage(TaskList tasks) {
        String res = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + "." + tasks.getTask(i);
        }
        return res;
    }
}
