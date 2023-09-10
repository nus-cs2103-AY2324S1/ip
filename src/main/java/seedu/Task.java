package seedu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Task {
    protected String description;
    protected boolean isDone;
    protected Type category;
    protected String title;
    protected String start = "";
    protected String end = "";

    /**
     * The task to be loaded
     */
    public Task(String description, String category) throws IllegalArgumentException {
        this.isDone = false;
        if (category.equals("todo")) {
            this.category = Type.ToDo;
            String[] splitWord =description.split(" ", 2);
            try {
                this.title = splitWord[1].trim();
                this.description = splitWord[0].trim() + " " + title;
            } catch(Exception e) {
                throw new IllegalArgumentException("Please correct the format");
            }
        } else if (category.equals("deadline")) {
            this.category = Type.Deadline;
            String[] splitWord =description.split("/", 4);
            try {
                if (splitWord.length == 2) {
                    this.title = splitWord[0].trim();
                    this.end = splitWord[1].split("by ")[1].trim();
                    this.description = title + "("+ splitWord[1].split("by ")[1].trim() + ")";
                } else {
                    String date = splitWord[1].substring( 3).length() == 1 ? "0"
                            + splitWord[1].substring( 3) : splitWord[1].substring( 3);

                    String endDate = splitWord[3]+"-"+splitWord[2]+"-" + date;
                    String formattedDate = LocalDate.parse(endDate)
                            .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    this.title = splitWord[0];
                    this.end = formattedDate;
                    this.description = splitWord[0] + "("+ formattedDate + ")";
                }
            } catch(Exception e) {
                throw new IllegalArgumentException("Please correct the format");
            }

        } else if (category.equals("event")) {
            String[] splitWord = description.split("/(from|to)", 3);
            this.category = Type.Event;
            try {
                this.title = splitWord[0].trim();
                this.start = splitWord[1].trim();
                this.end = splitWord[2].trim();
                this.description = title + "(From : " + start + " To : " + end + ")";
            } catch(Exception e) {
                throw new IllegalArgumentException("Please correct the format");
            }
        } else {
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the status of the task
     *
     * @returns a String of X if task is done otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status of the task
     *
     * @returns a String more descriptive status of the task
     */
    public String getStatus() {
        String cat = this.category == Type.ToDo ? "T" : this.category == Type.Deadline ? "D" : "E";
        return "[" + cat +"]" + "["+ this.getStatusIcon() + "] " + this.description.trim();
    }

    /**
     * Sets the description of the task
     *
     * @param desc the text description of the task
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Marks a certain task as done
     *
     * @returns a String to confirm the checked
     */
    public String mark() {
        this.isDone = true;
        assert this.isDone == true : "Something wrong with marking";
        return "["+ this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string to show the status of the task
     *
     * @returns a status of the current task
     */
    @Override
    public String toString() {
        return this.getStatus();
    }

}
