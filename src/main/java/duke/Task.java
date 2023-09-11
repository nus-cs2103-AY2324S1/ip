package duke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Task represents a task given by a user's input
 * It contains details of a task
 */

@SuppressWarnings("checkstyle:Regexp")
public class Task {

    private String taskName;
    private boolean isDone;

    private Set<String> tags;

    /**
     * Constructor for creating a task
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Completes a task when called by setting it as done
     */
    public void completeTask() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        // Double space to maintain consistency with rubrics
        System.out.println("  " + this.toString());
    }

    /**
     * Completes a task without printing anything
     */
    public void quietlyCompleteTask() {
        this.isDone = true;
    }
    /**
     * Mark a task as undone by setting done as false
     */
    public void undoTask() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        // Double space to maintain consistency with rubrics
        System.out.println("  " + this.toString());
    }

    /**
     * Checks whether a task is completed and return an X if done
     * @return String X if done, a blank space string if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks whether a task is done through a string
     * @return  a string containing whether the task is done
     */
    public String isDone() {
        return String.valueOf(this.isDone);
    }

    /**
     * Prints out a message that a task has been added
     */
    public void taskAdded(int noOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }
    /**
     * Prints out a message that a task has been deleted
     */
    public void taskDeleted(int noOfTask) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }

    /**
     * Converts the task to a format that can be saved
     * @return  a string that can be saved in the storage in a particular format
     */
    public String convertToSaveFormat() {
        return "";
    }

    /**
     * Returns the task name
     * @return  a string containing the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks for the validity of a tag command
     * @param input the user's string input
     * @throws WrongInputException which informs the user of the error and actions to take
     */
    public static void tagValidator(String input) throws WrongInputException {
        String[] splitString = input.split(" ");
        if (splitString.length < 3) {
            throw new WrongInputException("Invalid tag format",
                    "please enter tag <number on list> <tag name with no space>");
        }
        if (splitString.length > 3) {
            throw new WrongInputException("Invalid tag format",
                    "please enter tag <number on list> <tag name with no space>");
        }
        String tag = splitString[2];
        boolean isEmptyTag = tag.trim().isEmpty();
        if (isEmptyTag) {
            throw new WrongInputException("Tag cannot be blank", "please enter a non-blank tag");
        }
    }
    /**
     * Attach a tag to a task
     * @param tag the tag to be attached
     */
    public void addTag(String tag) {
        if (this.tags.contains(tag)) {
            System.out.println("Tag already exists");
            return;
        }
        this.tags.add(tag);
    }
    /**
     * Remove a tag from a task
     * @param tag the tag to be removed
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Converts the tags of a task into a string that can be saved
     * @return  a string that can be saved
     */
    public String saveTagFormat() {
        String tagsString = String.join(", ", this.tags);
        return tagsString;
    }

    /**
     * Loads the tags of a task
     */
    public void loadTags(String savedTags) {
        String[] tags = savedTags.split(", ");
        List<String> tagsList = Arrays.asList(tags);
        Set<String> tagsSet = new HashSet<>(tagsList);
        this.tags = tagsSet;
    }

    /**
     * Prints the tags of a task
     * @return  a string containing the tags of a task
     */
    public String printTags(int listNumber) {
        String tagsString = String.join(", ", this.tags);
        System.out.println(listNumber + ". " + "[" + this.getTaskType() + "] "
                + this.taskName + " | Tags: " + tagsString);
        return tagsString;
    }

    /**
     * Returns the task type
     * @return  a string containing the task type
     */
    public String getTaskType() {
        return "";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[" + this.getStatusIcon() + "] " + this.taskName;
        } else {
            return "[" + this.getStatusIcon() + "] " + this.taskName;
        }
    }

}
