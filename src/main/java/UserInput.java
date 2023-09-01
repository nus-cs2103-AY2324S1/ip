import java.util.ArrayList;
import java.util.Scanner;
public class UserInput {
    protected String text;
    protected boolean isDone;

    public UserInput(String text) {
        this.text = text;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.text;
    }

    public static UserInput parseFomSaveFormat(String sf) {
        String[] parts = sf.split(" | ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String date = parts[2];

        String description = parts[2];
        if (type.equals("T")) {
            UserInput ui = new ToDo(description);
            ui.isDone = isDone;
            return ui;
        }
        if (type.equals("D")) {
            UserInput ui = new Deadline(description, date);
            ui.isDone = isDone;
            return ui;
        }
        if (type.equals("E")) {
            String[] timeline = sf.split("-");
            String from = timeline[0];
            String to = timeline[1];
            UserInput ui = new Event(description, from, to);
            ui.isDone = isDone;
            return ui;
        }
        return null;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.text;
    }

}
