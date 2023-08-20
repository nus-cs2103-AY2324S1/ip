import java.util.ArrayList;

public class Todo {
    private ArrayList<String> list;

    public Todo() {
        list = new ArrayList<>();
    }

    public void addTask(String task) {
        list.add(task);
    }

    public void printTasks() {
        String[] tasks = list.toArray(String[]::new);
        Reply reply = Reply.init();
        String dialog = "";
        for (int i = 0; i < tasks.length; i++) {
            int listIndex = i + 1;
            if (i < tasks.length - 1) {
                dialog = dialog + listIndex + ". " + tasks[i] + "\n     ";
            } else {
                dialog = dialog + listIndex + ". " + tasks[i];
            }
        }
        reply.printDialog(dialog);
    }
}
