package main.java;

import java.util.ArrayList;

class Task {

    private String taskName;
    private Boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
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

    public static class ToDos extends Task {
        public ToDos(String taskName) {
            super(taskName);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadlines extends Task {
        private String dayDate;
        public Deadlines(String taskName, String dayDate) {
            super(taskName);
            this.dayDate = dayDate;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + dayDate + ")";
        }
    }

    public static class Event extends Task {
        private String startDayDateTime;
        private String endDayDateTime;
        public Event(String taskName, String startDayDateTime, String endDayDateTime) {
            super(taskName);
            this.endDayDateTime = endDayDateTime;
            this.startDayDateTime = startDayDateTime;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + startDayDateTime + "to: " + endDayDateTime +")";
        }
    }
}

class ListOfTask {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private static int counter = 0;

    protected void addTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.ToDos(task);
        System.out.println("added: " + temp);
    }

    protected void addTask(String task, String dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.Deadlines(task, dayDate);
        System.out.println("added: " + temp);
        counter++;
    }

    protected void addTask(String task, String startDayDateTime, String endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.Event(task, startDayDateTime, endDayDateTime);
        System.out.println("added: " + temp);
        counter++;
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void unMark(int index) {
        try {
            listOfTask.get(index - 1).unMark();
            System.out.println(listOfTask.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void delete(int index) {
        try {
            Task removed = listOfTask.remove(index - 1);
            System.out.println(removed + " has been removed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }
}
