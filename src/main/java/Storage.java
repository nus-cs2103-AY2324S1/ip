import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Storage {
    Scanner sc;
    Ui ui;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Storage(Ui ui) {
        sc = new Scanner(System.in);
        this.ui = ui;
    }

    public ArrayList<Task> readFile() throws Exception {
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            File taskFile = new File("tasks.txt");
            Scanner taskScanner = new Scanner(taskFile);
            Parser parser = new Parser(ui);

            while (taskScanner.hasNextLine()) {
                String task = taskScanner.nextLine();
                Task newTask = parser.parseFileTask(task);
                list.add(newTask);
            }

            taskScanner.close();

        } catch (FileNotFoundException e) {
            // File does not exist
            try {
                // Create new file
                File taskFile = new File("tasks.txt");
                taskFile.createNewFile();
            } catch (Exception f) {
                ui.showError("Error creating new file.");
            }
        }
        return list;
    }

    public void writeFile(ArrayList<Task> list) throws Exception {
        FileWriter fw = new FileWriter("tasks.txt");

        try {
            FileWriter taskWriter = new FileWriter("tasks.txt");
            for (Task task : list) {
                taskWriter.write(task.toFileString() + "\n");
            }
            taskWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }

        fw.close();
    }
}
