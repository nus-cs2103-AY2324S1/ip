package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dukeexception.CorruptedFileException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class TaskList {
    private ArrayList<Task> taskList;
    private int counter;

    private Storage storage;

    public TaskList(Storage storage) {
        //Load if possible
        taskList = new ArrayList<>();
        counter = -1;
        this.storage = storage;
    }
    public void loadFromDisk(Storage storage) throws IOException, CorruptedFileException {
        taskList = stringListToTaskList(storage.loadFromDisk());
        counter = taskList.size() - 1;
    }
    public void writeToDisk(Storage storage) throws IOException {
        storage.writeToDisk(taskListToStringList(taskList));
    }


    public void addTask(Task task) throws IOException {
        taskList.add(task);
        counter += 1;
        writeToDisk(storage);
    }
    public boolean removeTask(int index) throws IOException {
        if (index > counter) {
            return false;
        }
        taskList.remove(index);
        writeToDisk(storage);
        return true;
    }
    public boolean setMark(int targetIndex, boolean isToBeMarked) throws IOException {
        if (targetIndex > counter || targetIndex < 0) {
            return false;
        }
        if (isToBeMarked) {
            taskList.get(targetIndex).markDone();
        }
        if (!isToBeMarked) {
            taskList.get(targetIndex).markUndone();
        }
        writeToDisk(storage);
        return true;
    }
    private ArrayList<Task> stringListToTaskList(ArrayList<String> stringArrayList) throws CorruptedFileException {
        ArrayList<Task> res = new ArrayList<>();
        for (String s : stringArrayList) {
            String[] temp = s.split(Task.DIVIDER);
            boolean completeStatus;
            if (temp[1].equals("TRUE")) {
                completeStatus = true;
            } else if (temp[1].equals("FALSE")) {
                completeStatus = false;
            } else {
                throw new CorruptedFileException();
            }
            try {
                switch (temp[0]) {
                case ("TD"):
                    res.add(new ToDo(temp[2], completeStatus));
                    break;
                case ("DL"):
                    res.add(new Deadline(temp[2], completeStatus, (LocalDate.parse(temp[3]))));
                    break;
                case ("EV"):
                    res.add(new Event(temp[2], completeStatus, LocalDate.parse(temp[3]), LocalDate.parse(temp[4])));
                    break;
                default:
                    throw new CorruptedFileException();
                }
            } catch (DateTimeParseException e) {
                throw new CorruptedFileException();
            }
        }
        return res;
    }

    private ArrayList<String> taskListToStringList(ArrayList<Task> taskArrayList) {
        ArrayList<String> res = new ArrayList<>();
        for (Task s : taskArrayList) {
            res.add(s.fileFormat());
        }
        return res;
    }

    public String listString() {
        if (counter == -1) {
            return ("No list, silly!");
        } else {
            String res = "Here's the list so far.";
            for (int i = 0; i < counter + 1; i++) {
                res += ("\n" + (i + 1) + ". " + taskList.get(i));
            }
            return res;
        }
    }
}
