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

    public Task(String description, String category) throws IllegalArgumentException {
        this.isDone = false;
        if (category.equals("todo")) {
            this.category = Type.ToDo;
            String[] s =description.split(" ", 2);
            try {
                this.title = s[1];
                this.description = s[0] + s[1];
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }
        } else if (category.equals("deadline")) {
            this.category = Type.Deadline;
            String[] s =description.split("/", 4);
            try {
                if (s.length == 2) {
                    this.title = s[0];
                    this.end = s[1].split("by ")[1];
                    this.description = s[0] + "("+ s[1].split("by ")[1] + ")";
                } else {
                    String date = s[1].substring( 3).length() == 1 ? "0" + s[1].substring( 3) : s[1].substring( 3);

                    String endDate = s[3]+"-"+s[2]+"-" + date;
                    String formattedDate = LocalDate.parse(endDate)
                            .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    this.title = s[0];
                    this.end = formattedDate;
                    this.description = s[0] + "("+ formattedDate + ")";
                }

            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }

        } else if (category.equals("event")) {
            String[] s = description.split("/(from|to)", 3);
            this.category = Type.Event;
            try {
                this.title = s[0];
                this.start = s[1];
                this.end = s[2];
                this.description = s[0] + "(From : " + s[1] + " To : " + s[2] + ")";
            } catch(Exception e) {
                System.out.println("OOPS!!! The description of a event cannot be empty.");
                System.out.println("This is not in the correct format");
                throw new IllegalArgumentException("Please correct the format");
            }
        } else {
            throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        String cat = this.category == Type.ToDo ? "T" : this.category == Type.Deadline ? "D" : "E";
        return "[" + cat +"]" + "["+ this.getStatusIcon() + "] " + this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String mark() {
        this.isDone = true;
        return "["+ this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }

}
