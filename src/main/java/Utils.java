import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utils {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            File file = new File("./data/duke.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                fileWriter.write(task + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static String outputDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
