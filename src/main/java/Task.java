package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        private String dayDate;
        public Deadlines(String taskName, String dayDate) {
            super(taskName);
            this.dayDate = dayDate;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + dayDate + ")";
        }

        protected String write() {
            return "deadline " + super.taskName + "/by " + this.dayDate + "\n";
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

        protected String write() {
            return "event " + super.taskName + "/from " + this.startDayDateTime + "/to " + this.endDayDateTime + "\n";
        }
    }
}

class ListOfTask {
    private static ArrayList<Task> listOfTask = new ArrayList<>();

    protected int size() {
        return listOfTask.size();
    }

    protected void addTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        save();
    }

    protected void addTask(String task, String dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        save();
    }

    protected void addTask(String task, String startDayDateTime, String endDayDateTime) {
        Task temp = new Task.Event(task, startDayDateTime, endDayDateTime);
        listOfTask.add(temp);
        System.out.println("added: " + temp);
        save();
    }

    protected void loadTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
    }

    protected void loadTask(String task, String dayDate) {
        Task temp = new Task.Deadlines(task, dayDate);
        listOfTask.add(temp);
    }

    protected void loadTask(String task, String startDayDateTime, String endDayDateTime) {
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
            save();
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
            save();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void delete(int index) {
        try {
            Task removed = listOfTask.remove(index - 1);
            System.out.println(removed + " has been removed");
            save();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    protected void save() {
        File writeData = new File("./src/data/duke.txt");
        try {
            writeData.createNewFile();
            FileWriter writer = new FileWriter(writeData);
            listOfTask.forEach(x-> {
                try {
                    writer.write(x.write());
                    if (x.isDone()) {
                        writer.write("mark\n");
                    }
                } catch (IOException e) {
                    System.out.println("You do not have access to write to your save file");
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("You do not have access to save your file");
        }

    }
}
