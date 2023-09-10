package mainDuke.task;

import java.util.ArrayList;

import mainDuke.exceptions.DukeException;


/**
 * Represents a Task, a supertype of Event, Deadline and Todo.
 * <code>isDone</code> shows whether the task is completed
 */
public abstract class Task {
    private ArrayList tags;
    /**
     * Name of task
     */
    private final String description;
    /**
     * Whether the task is done.
     */
    private boolean isDone;
    /**
     * Constructor, should not be used because this is an abstract class. For initialising description
     * and isDone only.
     * @param done whether the task is done.
     * @param description name of task.
     */
    public Task(boolean done, String description) {
        this.isDone = done;
        this.tags = new ArrayList<>();
        String updatedDesc = addAndFilterTags(description);
        this.description = updatedDesc;
    }

    /**
     * Getter for boolean isDone.
     * @return boolean isDone.
     */
    public boolean getisDone() {
        return this.isDone;
    }

    /**
     * Getter for descriptions
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done, if it is undone.
     * @throws DukeException if task is already done.
     */
    public String mark() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task already done");
        }
        this.isDone = true;
        return ("Nice! I've marked this task as done:\n" + toString());
    }

    /**
     * Unmarks the task as undone, if it is done.
     * @throws DukeException if task is still undone.
     */
    public String unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException("Task still undone");
        }
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n" + toString();
    }
    /**
     * Get String representation of whether the task is done.
     * @return <code>"X"</code> for done, " " for undone.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * String representation of task, including whether the task is done.
     * @return string representation of task.
     */
    @Override
    public String toString() {
        String tagString = "";
        if (tags.size() > 0) {
            tagString = "\n  Tags : ";
            for (int i = 0; i < tags.size(); i++) {
                tagString = tagString + tags.get(i) + " ";
            }
        }
        return ("[" + this.getStatusIcon() + "] " + this.description + tagString);
    }

    /**
     * Converts hard drive's String format of a task into an actual task item.
     * @param text String from hard drive.
     * @return task item.
     * @throws DukeException if string cannot be recognised and cannot be parsed into a task.
     */
    public static Task parse(String text) throws DukeException {
        String[] parts = text.split("\\|");
        String first = parts[0];
        Task task;
        assert parts[1].equals("1") | parts[1].equals("0") : "isDone indicator should be either"
                + "1 or 0";
        boolean done = parts[1].equals("1"); // 1 = done, 0 = undone

        String description = parts[2];
        System.out.println("desc : " + description);
        switch (first) {
        case "T":
            task = new Todo(done, "todo " + description);
            break;

        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(done, "event " + description + " /from " + from + " /to " + to);
            break;

        case "D":
            String by = parts[3];
            task = new Deadline(done, "deadline " + description + " /by " + by);
            break;

        default:
            throw new DukeException("Unable to parse from hard drive");
        }
        System.out.println("here : " + task);
        return task;
    }

    /**
     * Adds tags to task from input string, then returns the description with tags filtered out.
     * @param text original description.
     * @return description with tags filtered out.
     */
    private String addAndFilterTags(String text) {
        String[] words = text.split("\\s+"); // Assumes words are separated by spaces

        StringBuilder filteredText = new StringBuilder();
        // Check for words starting with "#"
        for (String word : words) {
            if (word.startsWith("#")) {
                tags.add(word);
            } else {
                filteredText.append(word);
            }
        }

        return filteredText.toString();
    }

    /**
     * returns tags in string form.
     * @return tags in string form, #tag1 #tag2 etc.
     */
    public String getTags() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            text.append(" " + tags.get(i));
        }
        return text.toString();
    }
}
