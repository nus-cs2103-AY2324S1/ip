package emiya.task;

import emiya.emiyaexception.CannotFindWordException;
import emiya.emiyaexception.ListEmptyException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task get(int pos) {
        return this.taskArrayList.get(pos);
    }

    public void remove(Task task) {
        this.taskArrayList.remove(task);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void list() throws ListEmptyException{
        int listPointer = 1;
        StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                "Lots of things to do! Get to it!:\n");
        if (taskArrayList.size() == 0) {
            throw new ListEmptyException();
        }
        for (Task task : taskArrayList) {
            String listItem = listPointer + "." + task + "\n";
            listPointer++;
            listString.append(listItem);
        }
        listString.append("-----------------------------------------\n");
        System.out.println(listString);
    }

    public void find(String word) throws ListEmptyException, CannotFindWordException {
        int listPointer = 1;
        boolean isFound = false;
        StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                "Sure, here are all the tasks that have this word in them:\n");
        if (taskArrayList.size() == 0) {
            throw new ListEmptyException();
        }
        for (Task task : taskArrayList) {
            String taskDescription = task.nameOfTask;
            if (taskDescription.contains(word)) {
                String listItem = listPointer + "." + task + "\n";
                listPointer++;
                listString.append(listItem);
                isFound = true;
            }
        }
        if (!isFound) {
            throw new CannotFindWordException();
        }
        listString.append("-----------------------------------------\n");
        System.out.println(listString);
    }
}
