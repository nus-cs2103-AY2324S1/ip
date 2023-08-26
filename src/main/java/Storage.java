import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    public String[] parseEntry(String entry) throws DukeDatabaseException {
        ArrayList<String> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([A-Z])\\]\\[(.)\\] (.+?)(?: \\(by: (.+?)\\)| \\(from: (.+?) to: (.+?)\\))?");
        Matcher matcher = pattern.matcher(entry);

        if (matcher.matches()) {
            elements.add(matcher.group(1));
            elements.add(matcher.group(2));
            elements.add(matcher.group(3));
            if (matcher.group(4) != null) {
                elements.add(matcher.group(4));
            } else if (matcher.group(5) != null && matcher.group(6) != null) {
                elements.add(matcher.group(5));
                elements.add(matcher.group(6));
            }
        } else {
            throw new DukeDatabaseException();
        }

        return elements.toArray(new String[0]);
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File("data/duke.txt");
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                try {
                    String[] entryList = this.parseEntry(entry);
                    Task newTask;
                    switch (entryList[0]) {
                        case "T":
                            newTask = new Todo(entryList[2]);
                            if (entryList[1].equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                        case "D":
                            newTask = new Deadline(entryList[2], entryList[3]);
                            if (entryList[1].equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                        case "E":
                            newTask = new Event(entryList[2], entryList[3], entryList[4]);
                            if (entryList[1].equals("X")) {
                                newTask.markAsDone();
                            }
                            taskList.add(newTask);
                            break;
                    }
                } catch (DukeDatabaseException e) {
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
}
