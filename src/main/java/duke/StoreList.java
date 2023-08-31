package duke;

import java.util.ArrayList;
import java.util.Collection;

public class StoreList {

    ArrayList<Task> list;

    public StoreList() {
        this.list = new ArrayList<>();
    }

    String add(Commands type, String description) {
        try {

            Task task = Task.create(type, description);
            list.add(task);
            return String.format(
                    "added: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String add(Task task) {
        list.add(task);
        return String.format(
                "added: %s\nYou have %d tasks.",
                task,
                list.size()
        );
    }

    public void addTasks(Collection<? extends Task> tasks) {
        list.addAll(tasks);
    }

    String markDone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsDone();
            return String.format("Nice! You have completed the task:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    String markUndone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsNotDone();
            return String.format("Ok! duke.Task marked undone:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    String delete(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.remove(index);
            return String.format(
                    "removed: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    @Override
    public String toString() {
        if (list.size() == 0) {
            return "You have no tasks :).";
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("    %d. %s\n", i + 1, list.get(i));
        }
        return result;
    }

    public String showSaveText() {
        String saveText = "";
        for (int i = 0; i < list.size(); i++) {
            saveText += list.get(i).fileString() + (i + 1 == list.size() ? "" : "\n");
        }
        return saveText;
    }
}