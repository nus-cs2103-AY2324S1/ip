package boti.task;

import java.io.IOException;

/**
 * Task class
 */
public abstract class Task {
    private String name;
    private boolean isMarked = false;

    /**
     * Instantiates the Task
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Gets the name of the task
     *
     * @return the name of the to do
     */
    public String getName() {
        return name;
    }

    /**
     * Gets whether the task is marked
     *
     * @return whether the task is marked
     */
    public boolean getMark() {
        return isMarked;
    }

    /**
     * Checks whether the name of the task containing the keyword
     *
     * @param keyWord the keyword
     * @return whether the name of the task containing the keyword
     */
    public boolean hasKeyword(String keyWord) {
        return name.toLowerCase().contains(keyWord.toLowerCase());
    }

    /**
     * Creates new task based on the string stored in the storage
     *
     * @param stored the string stored in the storage
     * @return the task based on the string stored in the storage
     * @throws IOException when the string stored does not belong to task
     */
    public static Task createFromStorage(String stored) throws IOException {
        String[] splitTaskInString = stored.split(" \\| ");
        String type = splitTaskInString[0];
        switch (type) {
        case "T":
            return ToDo.createFromStorage(stored);
        case "D":
            return Deadline.createFromStorage(stored);
        case "E":
            return Event.createFromStorage(stored);
        default:
            assert false : "The type of the task stored is invalid";
            throw new IOException();
        }
    }

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + name;
    }

    /**
     * Gets string format of the task in the storage
     *
     * @return the string format of the task in the storage
     */
    public abstract String storeInString();
}
