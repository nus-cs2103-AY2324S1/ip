package task;
import java.util.Scanner;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(int status, String description) {
        super(description, status != 0);     //if 0, return false, else return true
    }

    @Override
    public String storeToDiskFormat() {
        return "T" + "|" + this.getStatus() + "|" + this.getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
