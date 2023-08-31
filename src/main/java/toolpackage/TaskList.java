package toolpackage;

import dukepackage.DukeException;

import taskpackage.Task;

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

    public boolean saveStorage(FileWriter fileWriter) throws DukeException {
        try {
            for (Task listOfTask : listOfTasks) {
                fileWriter.write(listOfTask.addToStorage());
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! There was an error saving data into storage.");
        }
    }

    public void printList(Ui ui) {
        ui.printList(listOfTasks);
    }

    public boolean toggleDone(String id, String keyword, Ui ui) {
        try {
            this.listOfTasks.get(Integer.parseInt(id) - 1).toggleDone(keyword, ui);
            return true;
        } catch (NumberFormatException e) {
            if (keyword.equals("mark")) {
                System.out.println("☹ OOPS!!! Please indicate the task to mark in numbers.");
            } else {
                System.out.println("☹ OOPS!!! Please indicate the task to unmark in numbers.");
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
            return false;
        }
    }

    public boolean removeItem(String id, Ui ui) {
        try {
            Task task = this.listOfTasks.remove(Integer.parseInt(id) - 1);
            ui.removeItem(task, this.listOfTasks.size());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate the task to delete in numbers.");
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
            return false;
        }
    }

    public void addItem(Task task, Ui ui) {
        this.listOfTasks.add(task);
        ui.addItem(task, this.listOfTasks.size());
    }
}
