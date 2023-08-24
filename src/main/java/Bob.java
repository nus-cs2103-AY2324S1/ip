import java.util.ArrayList;
public class Bob {
    private static ArrayList<Task> taskArr = new ArrayList<>();
    private String name = "Bob";
    private Task[] list = new Task[100];
    private String horizontal = "____________________________________________________________";
    private int count;

    public Bob() {
        this.count = 0;
    }

    public String messageCard(String message) {
        return horizontal + "\n" + message + "\n" + horizontal;
    }
    public void greet() {
        String ln1 = String.format("Hello! I'm %s", this.name);
        String ln2 = "What can I do for you?";
        String ln3 = ln1 + "\n"+ ln2;

        System.out.println(messageCard(ln3));
    }
    public void bye() {
        String ln3 = "Bye. Hope to see you again soon!";

        System.out.println(messageCard(ln3));
    }

    public void addTodo(String[] text) throws IllegalChatBotExceptions {
        if (text.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a todo cannot be empty."));
        }

        Todo task = new Todo(text[1]);
        taskArr.add(task);
        String message = "Got it. I've added this task: \n" + task +
                                "\nNow you have " + taskArr.size() + " tasks in the list.";

        System.out.println(messageCard(message));
    }

    public void addDeadline(String[] textArr) throws IllegalChatBotExceptions {
        if (textArr.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a deadline cannot be empty."));
        }

        String text = textArr[1];
        String[] split = text.split(" /by ", 2);
        String desc = split[0];
        String by = split[1];
        Deadline task = new Deadline(desc, by);
        taskArr.add(task);

        String message = "Got it. I've added this task: \n" + task +
                            "\nNow you have " + taskArr.size() + " tasks in the list.";
        System.out.println(messageCard(message));
    }

    public void addEvent(String[] textArr) throws IllegalChatBotExceptions {
        if (textArr.length < 2) {
            throw new IllegalChatBotExceptions(messageCard("☹ OOPS!!! The description of a event cannot be empty."));
        }

        String text = textArr[1];
        String[] slice = text.split(" /from ", 2);
        String desc = slice[0];
        String[] slicess = slice[1].split(" /to ", 2);
        String from = slicess[0];
        String to = slicess[1];

        Event task = new Event(desc, from, to);
        taskArr.add(task);

        String message = "Got it. I've added this task: \n" + task +
                            "\nNow you have " + taskArr.size() + " tasks in the list. ";
        System.out.println(messageCard(message));
    }

    public void listOut() {
        System.out.println(horizontal);
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= taskArr.size(); i++) {
            System.out.println(i + ". " + taskArr.get(i - 1));
        }

        System.out.println(horizontal);
    }

    public void markTask(int index) throws IllegalChatBotExceptions {
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                        "so you can't mark task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);
        task.markAsDone();
    }

    public void unmarkTask(int index) throws IllegalChatBotExceptions{
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                    "so you can't unmark task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);
        task.unmark();
    }

    public void deleteTask(int index) throws IllegalChatBotExceptions {
        if (index + 1 > taskArr.size()) {
            throw new IllegalChatBotExceptions(messageCard("The current number of tasks is " + taskArr.size() + ", " +
                    "so you can't delete task " + (index + 1) + "!!."));
        }
        Task task = taskArr.get(index);
        taskArr.remove(index);

        String message = "Noted. I've removed this task: \n" + task + "\nNow you have "
                        + taskArr.size() + " tasks in the list.";
        System.out.println(messageCard(message));
    }
}
