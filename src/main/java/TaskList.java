package main.java;

import java.time.LocalDateTime;
import java.util.ArrayList;

class Task {

    private String taskName;
    private Boolean done;
    private LocalDateTime dateAdded;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
        this.dateAdded = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return ("[" + (this.done ? "X] " : " ] ") + this.taskName);
    }

    protected void mark() {
        this.done = true;
    }

    protected void unMark() {
        this.done = false;
    }

    protected boolean isDone() {
        return this.done;
    }

    protected String write() {
        return "task " + this.taskName + "\n";
    }

    public static class ToDos extends Task {
        public ToDos(String taskName) {
            super(taskName);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        protected String write() {
            return "todo " + super.taskName + "\n";
        }
    }

    public static class Deadlines extends Task {
        private LocalDateTime dayDate;
        public Deadlines(String taskName, LocalDateTime dayDate) {
            super(taskName);
            this.dayDate = dayDate;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + this.dayDate.format(Duke.FORMAT) + ")";
        }

        protected String write() {
            return "deadline " + super.taskName + "/by " + this.dayDate.format(Duke.FORMAT) + "\n";
        }
    }

    public static class Event extends Task {
        private LocalDateTime startDayDateTime;
        private LocalDateTime endDayDateTime;
        public Event(String taskName, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
            super(taskName);

            this.endDayDateTime = endDayDateTime;
            this.startDayDateTime = startDayDateTime;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + startDayDateTime.format(Duke.FORMAT) + " to: " + endDayDateTime.format(Duke.FORMAT) +")";
        }

        protected String write() {
            return "event " + super.taskName + "/from " + this.startDayDateTime.format(Duke.FORMAT) + "/to " + this.endDayDateTime.format(Duke.FORMAT) + "\n";
        }
    }
}

class ListOfTask {
    private ArrayList<Task> listOfTask = new ArrayList<>();

    protected int size() {
        return listOfTask.size();
    }

    protected void addTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    protected void addTask(String task, LocalDateTime dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    protected void addTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        Storage.save(listOfTask);
    }

    protected void loadTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
    }

    protected void loadTask(String task, LocalDateTime dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
    }

    protected void loadTask(String task, LocalDateTime startDayDateTime, LocalDateTime endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
    }

    protected void listTasks() {
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x-> {
            System.out.print(i[0] + ".");
            System.out.println(x);
            i[0]++;
            }
        );
    }

    protected void mark(int index) {
        try {
            listOfTask.get(index - 1).mark();
            System.out.println(listOfTask.get(index - 1).toString());
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void loadMark(int index) {
        listOfTask.get(index - 1).mark();
    }

    protected void unMark(int index) {
        try {
            listOfTask.get(index - 1).unMark();
            System.out.println(listOfTask.get(index - 1).toString());
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void delete(int index) {
        try {
            Task removed = listOfTask.remove(index - 1);
            System.out.println(removed + " has been removed");
            Storage.save(listOfTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }


}
