import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private final Reply reply = Reply.init();

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task task) {
        list.add(task);
        StringBuilder dialog = new StringBuilder("Got it. I've added this task:\n       ")
                .append(task)
                .append("\n     ")
                .append("Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        reply.printDialog(dialog.toString());
    }

    public void printTasks() {
        Task[] tasks = list.toArray(Task[]::new);
        StringBuilder dialog = new StringBuilder("Here are the tasks in your list:\n     ");
        for (int i = 0; i < tasks.length; i++) {
            int listIndex = i + 1;
            Task task = tasks[i];
            dialog.append(listIndex)
                    .append(".");

            if (i < tasks.length - 1) {
                dialog.append(task)
                        .append("\n     ");
            } else {
                dialog.append(task);
            }
        }
        reply.printDialog(dialog.toString());
    }

    public void markDone(int index) {
        StringBuilder dialog = new StringBuilder();
        Task element = list.get(index - 1);
        element.markAsDone();
        dialog.append("Nice! I've marked this task as done:\n")
                .append("       ")
                .append(element);
        reply.printDialog(dialog.toString());
    }

    public void unmarkDone(int index) {
        StringBuilder dialog = new StringBuilder();
        Task element = list.get(index - 1);
        element.markAsNotDone();
        dialog.append("OK! I've marked this task as not done yet:\n")
                .append("       ")
                .append(element);
        reply.printDialog(dialog.toString());
    }

    public void deleteTask(int index) {
        StringBuilder dialog = new StringBuilder();
        Task element = list.get(index - 1);
        list.remove(index - 1);
        dialog.append("Noted. I've removed this task:\n")
                .append("       ")
                .append(element)
                .append("\n")
                .append("     Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        reply.printDialog(dialog.toString());
    }
}
