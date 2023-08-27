import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File("data/duke.txt");
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                try {
                    ArrayList<String> entryList = Parser.parseDatabaseEntry(entry);
                    Task newTask;
                    switch (entryList.get(0)) {
                        case "T":
                            newTask = new Todo(entryList.get(2));
                            if (entryList.get(1).equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                        case "D":
                            newTask = new Deadline(entryList.get(2), formatDate(entryList.get(3)));
                            if (entryList.get(1).equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                        case "E":
                            newTask = new Event(entryList.get(2), formatDate(entryList.get(3)),
                                    formatDate(entryList.get(4)));
                            if (entryList.get(1).equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                    }
                } catch (DukeDatabaseException e) {
                    System.out.println(e);
                } catch (DukeEndDateBeforeStartDateException e) {
                    System.out.println(e);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            File dukeFile = new File("data/duke.txt");
            try {
                dukeFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Failed to create duke file: " + ex.getMessage());
            }
        }
        return taskList;
    }

    public void saveData(TaskList taskList) {
        try {
            Files.delete(Paths.get("data/duke.txt"));
            FileWriter fw = new FileWriter("data/duke.txt");
            for (String taskString : taskList.stringify()) {
                fw.write(taskString + System.lineSeparator());
            }
            fw.close();
            System.out.println("Storage updated successfully !");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e );
        }
    }

    // reading from duke.txt returns date as MMM dd yyyy (e.g Oct 15 2019) instead of
    // yyyy-mm-dd format (e.g 2019-10-15), hence this function converts
    // Oct 15 2019 to 2019-10-15
    public LocalDate formatDate(String inputDate) {
        return LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }
}
