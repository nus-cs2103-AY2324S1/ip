import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class UserInput {
    protected String text;
    protected boolean isDone;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy h:mma", Locale.ENGLISH);


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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.text;
    }

}
