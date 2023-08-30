import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileWriter {
    private File file;
    private String filePath;

    public DukeFileWriter(String directoryPath, String fileName) {
        this.filePath = directoryPath + "/" + fileName;

        try {
            if (new File(directoryPath).mkdirs()) {
                System.out.println("Directories are created.");
            } else {
                System.out.println("Directories already exist.");
            }

            this.file = new File(filePath);

            if (this.file.createNewFile()) {
                System.out.println("File is created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> extractFromFile() throws FileNotFoundException, DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        try {
            Scanner scanner = new Scanner(this.file);

            while (scanner.hasNext()) {
                String taskDesc = scanner.nextLine();
                String[] taskDescArr = taskDesc.split(" ", 3);
                String taskTitle = taskDescArr[0];
                String taskDoneStatus = taskDescArr[1];
                String taskDetails = taskDescArr[2];

                Task taskToAdd;

                switch(taskTitle) {
                    case("[T]"):
                        taskToAdd = new Todo(taskDetails);
                        if (taskDoneStatus.equals("[X]")) {
                            taskToAdd.markDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    case("[D]"):
                        String[] taskDetailsArr = taskDetails.split("\\(by:", 2);
                        taskToAdd = new Deadline(taskDetailsArr[0].trim(),
                                LocalDateTime.parse(taskDetailsArr[1].split("\\)")[0].trim(), dateTimeFormat));
                        if (taskDoneStatus.equals("[X]")) {
                            taskToAdd.markDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    case("[E]"):
                        String[] taskDetailsArrOne = taskDetails.split("\\(from:", 2);
                        String taskDetailsForEvent = taskDetailsArrOne[0];
                        String[] taskDetailsArrTwo = taskDetailsArrOne[1].split("to:", 2);
                        taskToAdd = new Event(taskDetailsForEvent.trim(),
                                LocalDateTime.parse(taskDetailsArrTwo[0].trim(), dateTimeFormat),
                                LocalDateTime.parse(taskDetailsArrTwo[1].split("\\)")[0].trim(), dateTimeFormat));
                        if (taskDoneStatus.equals("[X]")) {
                            taskToAdd.markDone();
                        }
                        taskList.add(taskToAdd);
                        break;
                    default:
                        throw new DukeException("File is corrupted!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }
}
