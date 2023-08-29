package sally;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String task;
    protected boolean toBeDone;

    public Task(String task) {
        this.task = task;
        this.toBeDone = true;
    }

    public void mark() {
        toBeDone = false;
    }

    public void unmark() {
        toBeDone = true;
    }

    public String getStatusIcon() {
        return (toBeDone ? "[ ]" : "[X]");
    }

    public String toFileString() {
        if (this instanceof Todo) {
            Todo todo = (Todo) this;
            return todo.toString();
        } else if (this instanceof Event) {
            Event event = (Event) this;
            return event.toString();
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return deadline.toString();
        } else {
            return this.toString();
        }
    }

    private static LocalDateTime convertToDateTime(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
        return dateTime;
    }

    public static Task fromFileString(String fileString) {
        String target =  fileString;
        String[] split = target.split("]\\[");
        String splitOne = split[0]; // [D
        String[] toGetTaskType = splitOne.split("\\[");
        String taskType = toGetTaskType[1]; // D

        String splitTwo = split[1]; // " ] soc (by:sun)"
        String[] toGetStatusIcon = splitTwo.split("] ");
        String statusIcon = toGetStatusIcon[0]; // " "
        boolean isDone = false;

        if (statusIcon.equals(" ")) {
            isDone = false;
        } else {
            isDone = true;
        }

        String taskDescription = toGetStatusIcon[1];

        Task newTask;

        if (taskType.equals("T")) {
            newTask = new Todo(taskDescription);
        } else if (taskType.equals("E")) {
            String[] toGetFromTo = taskDescription.split(" \\(from: | to: |\\)");
            String task = toGetFromTo[0];
            LocalDateTime from = convertToDateTime(toGetFromTo[1]);
            LocalDateTime to = convertToDateTime(toGetFromTo[2]);
            newTask = new Event(task, from, to);

        } else if (taskType.equals("D")) {
            String[] toGetBy = taskDescription.split(" \\(by: |\\)");
            String task = toGetBy[0];
            LocalDateTime by = convertToDateTime(toGetBy[1]);
            newTask = new Deadline(task, by);

        } else {
            newTask = new Task(taskDescription);
        }

        if (isDone) {
            newTask.mark();
        }

        return newTask;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }
}
