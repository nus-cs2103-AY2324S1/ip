package data.tasks;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    private final String description;
    private boolean isDone = false;
    private final LocalDateTime dateToSortBy;

    public Task(String description) {
        this.description = description;
        this.dateToSortBy = LocalDateTime.MIN;
    }

    public Task(String description,
                LocalDateTime dateToSortBy) {
        this.description = description;
        this.dateToSortBy = dateToSortBy;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public int findKeyword(String keyword) {
        return description.indexOf(keyword);
    }

    public String toFileFormatString() {
        return String.format(
            "%s|%s",
            isDone ? "1" : "0",
            description
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s",
            isDone ? "X" : "  ",
            description
        );
    }

    @Override
    public int compareTo(Task o) {
        int dateCompareVal = this.dateToSortBy.compareTo(o.dateToSortBy);
        if (dateCompareVal == 0) {
            return this.description.compareTo(o.description);
        }
        return dateCompareVal;
    }
}

