package task;

import dukeExceptions.DukeException;
import dukeExceptions.LoadException;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> listOfTask = new ArrayList<>();

    /**
     * The size of the task list.
     * @return Returns the size of the task list.
     */
    public int size() {
        return listOfTask.size();
    }

    /**
     * Adds a task into the task list.
     * @param task The task that is to be added.
     * @param print True to print messages, false to not print messages.
     */
    public void addTask(String task, boolean print) {
        Task temp = Task.of(task);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }

    /**
     * Adds a task into the task list.
     * @param task The task that is to be added.
     * @param dayDate The deadline of the task.
     * @param print True to print messages, false to not print messages.
     */
    public void addTask(String task, LocalDateTime dayDate, boolean print) {
        Task temp = Task.of(task, dayDate);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }

    /**
     * Adds a task into the task list.
     * @param task The task that is to be added.
     * @param startDayDateTime The date and time of the start of the task.
     * @param endDayDateTime The date and time of the end of the task.
     * @param print True to print messages, false to not print messages.
     */
    public void addTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime, boolean print) {
        Task temp = Task.of(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        if (print) {
            System.out.println("added: " + temp);
            Storage.save(listOfTask);
        }
    }

    /**
     * Prints out the list of task.
     */
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

    /**
     * Mark a task as done.
     * @param index The index of the task based on the current task list.
     * @param print True to print messages, false to not print messages.
     * @throws DukeException If the number is outside the range of indexes in the list.
     */
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

    /**
     * Mark a task as undone.
     * @param index The index of the task based on the current task list.
     * @param print True to print messages, false to not print messages.
     * @throws DukeException If the number is outside the range of indexes in the list.
     */
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

    /**
     * Delete a task from the task list.
     * @param index The index of the task based on the current task list.
     * @param print True to print messages, false to not print messages.
     * @throws DukeException If the number is outside the range of indexes in the list.
     */
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

