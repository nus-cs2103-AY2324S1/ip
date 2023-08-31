package task;

import dukeExceptions.DukeException;
import storage.StorageStud;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListOfTaskStud {
    private ArrayList<TaskStud> listOfTask = new ArrayList<>();

    public int size() {
        return listOfTask.size();
    }

    public void addTask(String task, boolean print) {
        TaskStud temp = new TaskStud(task);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            StorageStud.save(listOfTask);
        }
    }


    public void addTask(String task, LocalDateTime dayDate, boolean print) {
        TaskStud temp = new TaskStud.DeadlinesStud(task, dayDate);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            StorageStud.save(listOfTask);
        }
    }

    public void addTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime, boolean print) {
        TaskStud temp = new TaskStud.EventStud(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            StorageStud.save(listOfTask);
        }
    }

    public void listTasks() {
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x-> {
                    System.out.print(i[0] + ".");
                    System.out.println(x);
                    i[0]++;
                }
        );
    }

    public void mark(int index, boolean print) throws DukeException {
        try {
            listOfTask.get(index - 1).mark();
            if (print) {
                System.out.println(listOfTask.get(index - 1).toString());
                StorageStud.save(listOfTask);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void unMark(int index, boolean print) throws DukeException {
        try {
            listOfTask.get(index - 1).unMark();
            if (print) {
                System.out.println(listOfTask.get(index - 1).toString());
            }
            StorageStud.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void delete(int index, boolean print) throws DukeException {
        try {
            TaskStud removed = listOfTask.remove(index - 1);
            if (print) {
                System.out.println(removed + " has been removed");
            }
            StorageStud.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }
}




