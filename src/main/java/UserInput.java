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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.text;
    }

}
