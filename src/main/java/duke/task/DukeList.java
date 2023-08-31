package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DukeList {
    ArrayList<Task> arr;

    public DukeList(ArrayList<Task> dukelist) {
        this.arr = dukelist;
    }

    public DukeList() {
        this.arr = new ArrayList<>(100);
    }

    public ArrayList<Task> getList() {
        return this.arr;
    }

    public void showList (Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() +  " tasks in the list");
    }

    public void deleteTask (int number) {
        Task chosenTask = arr.get(number);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + chosenTask.toString());

        arr.remove(number);
        System.out.println("Now you have " + arr.size() +  " tasks in the list");

    }

    public void addTodo(String input) {
            Todo todo = new Todo(input);
            arr.add(todo);
            showList(todo);
    }

    public void addDeadline(String input, LocalDateTime by) {
        Deadline deadline = new Deadline(input, by);
        arr.add(deadline);
        showList(deadline);
    }

    public void addEvent(String input, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(input, start, end);
        arr.add(event);
        showList(event);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int arrSize = arr.size();
        for (int i = 0; i < arrSize; i++) {
            int num = i + 1;
            Task chosenTask = arr.get(i);
            System.out.println(num + ". " + chosenTask.toString());
        }
    }

    public void setDone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    public void setUndone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }
}
