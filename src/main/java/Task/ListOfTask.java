package main.java.Task;

import main.java.Storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> listOfTask = new ArrayList<>();

    public int size() {
        return listOfTask.size();
    }

    public void addTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    public void addTask(String task, LocalDateTime dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    public void addTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    public void loadTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
    }

    public void loadTask(String task, LocalDateTime dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
    }

    public void loadTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
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

    public void mark(int index) {
        try {
            listOfTask.get(index - 1).mark();
            System.out.println(listOfTask.get(index - 1).toString());
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void loadMark(int index) {
        listOfTask.get(index - 1).mark();
    }

    public void unMark(int index) {
        try {
            listOfTask.get(index - 1).unMark();
            System.out.println(listOfTask.get(index - 1).toString());
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void delete(int index) {
        try {
            Task removed = listOfTask.remove(index - 1);
            System.out.println(removed + " has been removed");
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }
}

