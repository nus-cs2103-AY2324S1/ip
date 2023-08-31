package taskClasses;

import exception.InvalidDateTimeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskLists;
    private int taskCount = 0;
    public TaskList(
            ArrayList<Task> taskLists) {
        this.taskLists = taskLists;
    }

    public TaskList() {
        this.taskLists = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.taskLists;
    }

    public void printAllStatusAndDescription(){
        for (int i = 1; i < taskLists.size()+1; i++) {
            Task taskToPrint = taskLists.get(i - 1);
            System.out.println(i + ". " + taskToPrint.getStatusAndDescription());
        }
    }

    public String getStatusAndDescription(int number) {
        return taskLists.get(number-1).getStatusAndDescription();
    }

    public int getTasksCount() {
        return this.taskCount;
    }

    public void addToDoToList(Boolean isDone, String description) {
        Task newTask = new ToDo(description);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount += 1;
    }

    public void addDeadlineToList(Boolean isDone, String description, String date) throws InvalidDateTimeException {
        Task newTask = new Deadline(description, date);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount += 1;
    }

    public void addEventToList(Boolean isDone, String description, String start, String end) throws InvalidDateTimeException {
        Task newTask = new Event(description, start, end);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount += 1;
    }

    public void clear() {
        this.taskCount = 0;
    }

    public String deleteTask(int number) {
        String content = taskLists.get(number-1).getStatusAndDescription();
        taskLists.remove(number-1);
        this.taskCount -= 1;
        return content;
    }

    public void markTaskAsDone(int number) {
        taskLists.get(number-1).markAsDone();
    }

    public void markTaskAsNotDone(int number) {
        taskLists.get(number-1).markAsNotDone();
    }
}

