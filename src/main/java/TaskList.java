import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void saveStorage(FileWriter fileWriter) {
        try {
            for (Task listOfTask : listOfTasks) {
                fileWriter.write(listOfTask.addToStorage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printList(Ui ui) {
        ui.printList(listOfTasks);
    }

    public void toggleDone(String id, String keyword, Ui ui) {
        try {
            this.listOfTasks.get(Integer.parseInt(id) - 1).toggleDone(keyword, ui);
        } catch (NumberFormatException e) {
            if (keyword.equals("mark")) {
                System.out.println("☹ OOPS!!! Please indicate the task to mark in numbers.");
            } else {
                System.out.println("☹ OOPS!!! Please indicate the task to unmark in numbers.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
        }
    }

    public void removeItem(String id, Ui ui) {
        try {
            Task task = this.listOfTasks.remove(Integer.parseInt(id) - 1);
            ui.removeItem(task, this.listOfTasks.size());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate the task to delete in numbers.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
        }
    }

    public void addItem(Task task, Ui ui) {
        this.listOfTasks.add(task);
        ui.addItem(task, this.listOfTasks.size());
    }
}
