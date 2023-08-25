import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the Deadline Task.
 * @author Donovan Chan Jia Jun
 */
public class Deadlines extends Task{
    private String deadline;
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadlines(String name, String deadline, boolean isComplete) {
        super(name, isComplete);
        this.deadline = deadline;
    }

    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("D" + "|" + marking + "|" + super.getName() + "|" + this.deadline);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the String representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getMarking(), super.name, this.deadline);
    }
}
