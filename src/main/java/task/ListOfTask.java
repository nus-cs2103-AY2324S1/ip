package task;

import dukeExceptions.DukeException;
import dukeExceptions.LoadException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> listOfTask = new ArrayList<>();

    public int size() {
        return listOfTask.size();
    }

    public void addTask(String task, boolean print) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }


    public void addTask(String task, LocalDateTime dayDate, boolean print) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }

    public void addTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime, boolean print) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }

    public void listTasks() {
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x -> {
            System.out.print(i[0] + ".");
            System.out.println(x);
            i[0]++;
        });
    }


    /**
     * Finds and prints index and tasks that contains the string str in its name.
     * @param str The string that will be searched.
     */
    public void find(String str) {
        int[] start = new int[1];
        start[0] = 0;
        int size = listOfTask.size();
        int[] rememberIndex = new int[size];
        Task[] rememberTask = new Task[size];
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x -> {
            if (x.taskName.contains(str)) {
                rememberTask[start[0]] = x;
                rememberIndex[start[0]] = i[0];
                start[0]++;
            }
            i[0]++;
        });
        for (int j = 0; j < (start[0]); j++) {
            System.out.println(rememberIndex[j] + "." + rememberTask[j]);
        }
        if (start[0] == 0) {
            System.out.println("Whoopys uWu, sorry I couldnyt fynd any taysk that contyain that strying. XD uWu");
        }
    }

    public void mark(int index, boolean print) throws DukeException {
        try {
            listOfTask.get(index - 1).mark();
            if (print) {
                System.out.println(listOfTask.get(index - 1).toString());
                Storage.save(listOfTask);
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
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void delete(int index, boolean print) throws DukeException {
        try {
            Task removed = listOfTask.remove(index - 1);
            if (print) {
                System.out.println(removed + " has been deleted");
            }
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }
}

