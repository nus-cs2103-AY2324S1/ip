package duke;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> toDo;

    public TaskList() {
        this.toDo = new ArrayList<>();
    }

    public static void add(Task task, String type) {
        toDo.add(task);
        String description = task.getDescription();
        Ui.successfulAdd(type, description, toDo.size());
        Storage.save();
    }

    public static void add(Task task) {
        toDo.add(task);
    }

    public static void delete(Integer target) {
        if (target <= toDo.size()) {
            Task toDelete = toDo.get(target - 1);
            String description = toDelete.getDescription();
            toDo.remove(target - 1);
            Ui.successfulDelete(description, toDo.size());
            Storage.save();
        } else {
            Ui.tasksNumberError();
        }
    }

    public static void mark(Integer task_no) {
        if (task_no <= toDo.size()) {
            Task target = toDo.get(task_no - 1);
            target.mark();
            String description = target.getDescription();
            Ui.markMsg(description);
            Storage.save();
        } else {
            Ui.tasksNumberError();
        }
    }

    public static void unmark(Integer taskNo) {
        if (taskNo <= toDo.size()) {
            Task target = toDo.get(taskNo - 1);
            target.unmark();
            String description = target.getDescription();
            Ui.unmarkMsg(description);
            Storage.save();
        } else {
            Ui.tasksNumberError();
        }
    }

    public static void listOut() {
        for (int i = 0; i < toDo.size(); i++) {
            Task currTask = toDo.get(i);
            String description = currTask.getDescription();
            System.out.println("        " + Integer.toString(i + 1) + "." + description);
        }
    }

}
