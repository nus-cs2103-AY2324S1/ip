import java.util.ArrayList;

public class Todo {
    private ArrayList<Task> list;
    private final Reply reply = Reply.init();

    public Todo() {
        list = new ArrayList<>();
    }

    public void addTask(String task) {
        list.add(new Task(task));
    }

    public void printTasks() {
        Task[] tasks = list.toArray(Task[]::new);
        StringBuilder dialog = new StringBuilder("Here are the tasks in your list:\n     ");
        for (int i = 0; i < tasks.length; i++) {
            int listIndex = i + 1;
            Task task = tasks[i];
            String desc = task.description;
            dialog.append(listIndex)
                    .append(".").append("[")
                    .append(task.getStatusIcon())
                    .append("] ");

            if (i < tasks.length - 1) {
                dialog.append(desc).append("\n     ");
            } else {
                dialog.append(desc);
            }
        }
        reply.printDialog(dialog.toString());
    }

    public void markDone(int index) {
        StringBuilder dialog = new StringBuilder();
        Task element = list.get(index - 1);
        element.markAsDone();
        dialog.append("Nice! I've marked this task as done:\n")
                .append("       [")
                .append(element.getStatusIcon())
                .append("] ")
                .append(element.description);
        reply.printDialog(dialog.toString());
    }

    public void unmarkDone(int index) {
        StringBuilder dialog = new StringBuilder();
        Task element = list.get(index - 1);
        element.markAsNotDone();
        dialog.append("OK! I've marked this task as not done yet:\n")
                .append("       [")
                .append(element.getStatusIcon())
                .append("] ")
                .append(element.description);
        reply.printDialog(dialog.toString());
    }
}
