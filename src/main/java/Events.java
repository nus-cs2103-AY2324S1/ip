import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the Events Task.
 * @author Donovan Chan Jia Jun
 */
public class Events extends Task {
    private String from;
    private String to;
    public Events(String name, String from, String to) {
        super(name);
        this.to = to;
        this.from = from;
    }

    public Events(String name, String from, String to, boolean isComplete) {
        super(name, isComplete);
        this.to = to;
        this.from = from;
    }

    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("E" + "|" + marking + "|" + super.getName() + "|" + this.from + "|" + this.to);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the String representation of the Events object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)", super.getMarking(), super.name, this.from, this.to);
    }
}
