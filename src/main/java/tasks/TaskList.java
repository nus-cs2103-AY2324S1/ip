package tasks;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(0);
    }

    public TaskList(String content) {
        this.tasks = new ArrayList<>(0);

        addTextToTask(content);
    }

    public void addTextToTask(String content) {
        String[] lines = content.split("\n");

        for (String line: lines) {
            String[] parts = line.split(" ", 3);
            if (parts[0].equals("T")) {

                ToDo task = new ToDo(parts[2]);
                this.tasks.add(task);

            } else if (parts[0].equals("D")) {

                String[] arr = parts[2].split("/");
                Deadline task = new Deadline(arr[0], arr[1]);
                this.tasks.add(task);

            } else {

                String[] arr = parts[2].split("/");
                Event task = new Event(arr[0], arr[1], arr[2]);
                this.tasks.add(task);

            }
        }

    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:\n");

        if (tasks.size() == 0) {
            System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
        }
    }



    public void markTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsDone();

        System.out.println("Yay! You have completed this task:\n" +
                tasks.get(taskNumber - 1) + "\n");
    }

    public void unmarkTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsNotDone();

        System.out.println("Ok... Guess you're not actually done with this:\n" +
                tasks.get(taskNumber - 1) + "\n");
    }

    public void deleteTask(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        Task removedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        System.out.println("banished this task to the shadow realm:\n" + removedTask);
        printNumTasks();
    }

    public void addTodoTask(String description) {

        ToDo todo = new ToDo(description);
        this.tasks.add(todo);

        System.out.println("added new task:\n" + todo);
        printNumTasks();
    }

    public void addDeadlineTask(String description, String end) {

        Deadline deadline = new Deadline(description, end);
        this.tasks.add(deadline);

        System.out.println("added new task:\n" + deadline);
        printNumTasks();
    }

    public void addEventTask(String description, String start, String end) {

        Event event = new Event(description, start, end);
        this.tasks.add(event);

        System.out.println("added new task:\n" + event);
        printNumTasks();
    }

    public void printNumTasks() {
        System.out.println("you now have " + tasks.size() + " tasks in your list." + "\n");
    }
}
