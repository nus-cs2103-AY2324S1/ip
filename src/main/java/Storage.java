import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        String directoryPath = this.filePath.substring(0, this.filePath.indexOf("/"));
        String fileName = this.filePath.substring(this.filePath.indexOf("/"));

        try {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File f = new File(directoryPath, fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException{
        ArrayList<Task> lst = new ArrayList<>();

        File f = new File(this.filePath);
        Scanner scanner = new Scanner(f);
        try {
            while (scanner.hasNext()) {
                lst.add(textToTask(scanner.nextLine()));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    public static Task textToTask(String text) throws DukeException{ // Storage (load())
        String[] arr = text.split(" [|] ");
        String identifier = arr[0];
        String status = arr[1];
        boolean isDone;
        Task task = null;
        if (status.equals("[ ]")) {
            isDone = false;
        } else {
            isDone = true;
        }

        switch (identifier) {
            case "T":
                String todo = arr[2];
                task = new ToDo(todo, isDone);
                break;
            case "D":
                String deadline = arr[2];
                String by = arr[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                task = new Deadline(deadline, dateTime, isDone);
                break;
            case "E":
                String event = arr[2];
                String from = arr[3].substring(0, arr[3].indexOf("-"));
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
                LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);

                String to = arr[3].substring(arr[3].indexOf("-") + 1);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
                LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

                task = new Event(event, dateTimeFrom, dateTimeTo, isDone);
                break;
        }
        if (task == null) {
            throw new DukeException("Incorrect format of the file :(");
        } else {
            return task;
        }
    }

    public void clearFile() throws IOException { //Storage
        File f = new File(this.filePath);
        f.delete();
        f.createNewFile();
    }


    public void appendToFile(String textToAppend) throws IOException { // Storage
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public void updateFileAfterMark(int lineNumber) throws IOException { // Storage
        String updatedContent = "";
        File f = new File(this.filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[X]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }

    public void updateFileAfterUnmark(int lineNumber) throws IOException { // Storage
        String updatedContent = "";
        File f = new File(this.filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[ ]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }


    public void updateFileAfterDelete(int lineNumber) throws IOException { // Storage
        String updatedContent = "";
        File f = new File(this.filePath);
        Scanner scanner = new Scanner(f);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                scanner.nextLine();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }
}
