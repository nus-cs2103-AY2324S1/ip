package task;

import duke.Duke;
import java.time.LocalDateTime;


public class Task {

    String taskName;
    private Boolean isDone;
    private LocalDateTime dateAdded;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.dateAdded = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return ("[" + (this.isDone ? "X] " : " ] ") + this.taskName);
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String write() {
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

        public String write() {
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

        public String write() {
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
            return "[E]" + super.toString() + "(from: " + startDayDateTime.format(Duke.FORMAT)
                    + " to: " + endDayDateTime.format(Duke.FORMAT) +")";
        }

        public String write() {
            return "event " + super.taskName + "/from " + this.startDayDateTime.format(Duke.FORMAT)
                    + " /to " + this.endDayDateTime.format(Duke.FORMAT) + "\n";
        }
    }
}

