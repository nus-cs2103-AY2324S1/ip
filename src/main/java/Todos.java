import java.io.FileWriter;
import java.io.IOException;

/**
 * Emcapsulates a Todo task
 * @author Donovan Chan Jia Jun
 */
public class Todos extends Task{
    public Todos(String name) {
        super(name);
    }

    public Todos(String name, boolean isComplete) {
        super(name, isComplete);
    }

    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("T" + "|" + marking + "|" + super.getName());
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the string representation of the Todo Object.
     *
     * @return String Represents the Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getMarking(), super.name);
    }
}
