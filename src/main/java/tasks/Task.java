package tasks;

import functional.DukeException;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String content;
    private boolean isMarked;
    private LocalDate localDate;
    private Integer timeCompare;

    /**
     * Constructs a task with an initial status of not isMarked
     */
    public Task(String content) {
        this.content = content;
        this.isMarked = false;
        this.localDate = LocalDate.now();
        this.timeCompare = 0;
    }

    /**
     * Constructs a task with an initial status that is isMarked
     */
    public Task(String content, boolean status) {
        this.content = content;
        this.isMarked = status;
        this.localDate = LocalDate.now();
        this.timeCompare = 0;
    }

    /**
     * @return the task as isMarked
     */
    public Task mark() throws DukeException {
        return new Task(content, true);
    }

    /**
     * @return the task as unisMarked
     */
    public Task unmark() throws DukeException {
        return new Task(content);
    }

    /**
     * @param listSize size of the TaskList
     * @return a String representation of the message displayed when adding a task to TaskList
     */
    public String addTask(int listSize) {
        return "____________________________________________________________\n"
                + "added: " + this.content + "\n"
                + "____________________________________________________________";
    }

    /**
     * @return the content of the task as a string
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return the boolean value of whether the task is isMarked
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    public LocalDate getDateTime() {
        return localDate;
    }

    public Integer getTimeCompare() {
        return timeCompare;
    }

    @Override
    public int compareTo(Task other) {
        if (!getDateTime().equals(other.getDateTime())) {
            return getDateTime().compareTo(other.getDateTime());
        } else {
            return this.getTimeCompare().compareTo(other.getTimeCompare());
        }
    }

    /**
     * To String method
     *
     * @return a string describing the status of the task
     */
    public String toString() {
        if (!this.isMarked) {
            return String.format("[ ] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }
}
