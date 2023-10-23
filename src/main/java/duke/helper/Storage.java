package duke.helper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String fileName;
    private String dirName;
    private String filePath;

    private int listPointer;


    //private methods below

    /**
     * Sets up the background for storage.
     *
     */
    private void backgroundSetUp() {

        File dir = new File(this.dirName);
        if (!(dir.exists())) {
            if (dir.mkdir()) {
                System.out.println("Directory '" + this.dirName + "' created.");
            } else {
                System.err.println("Failed to create directory '" + this.dirName + "'.");
            }
        }


        File file = new File(dir, this.fileName);

        if (!(file.exists())) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + this.fileName + "' created in directory '" + this.dirName + "'.");
                } else {
                    System.err.println("Failed to create file '" + this.fileName + "' in directory '" + this.dirName + "'.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }




    //public methods below

    public Storage (String fileName, String dirName) {
        this.fileName = fileName;
        this.dirName = dirName;
        this.filePath = this.dirName + "/" + this.fileName;

        backgroundSetUp();

    }


    /**
     * Converts the content in the file into a list of tasks.
     *
     * @return The list of tasks.
     */
    public Task[] loadList() {
        Task[] userList = new Task[100];
        int positionPointer = 0;


        Path path = Paths.get(this.filePath);
        try {
            Scanner fileScanner = new Scanner(path);
            while(fileScanner.hasNextLine()){

                // Record format: "Type | Status | Name | StartTime(optional) | EndTime(optional)"
                // example: "D | 0 | return book | 2023-09-04"
                // "0" for not done and "1" for done

                String line = fileScanner.nextLine();

                List<String> record = readRecord(readLine(line));

                Task task = recordToTask(record);
                userList[positionPointer] = task;

                positionPointer++;


            }
            fileScanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.listPointer = positionPointer;


        return userList;
    }

    /**
     * Converts a line into a list of tokens.
     *
     * @param line The line to be converted into list of tokens.
     * @return The list of tokens.
     */
    private List<String> readLine(String line) {
        List<String> tokens = new ArrayList<>();
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            tokens.add(token);

        }
        lineScanner.close();

        return tokens;
    }


    /**
     * Converts a list of tokens into a record.
     *
     * @param tokens The list of tokens to be converted into record.
     * @return The converted record.
     */
    public List<String> readRecord(List<String> tokens) {

        List<String> record = new ArrayList<>();
        StringBuilder attributeName = new StringBuilder();

        for (String element : tokens) {
            if (element.equals("|")) {
                record.add(attributeName.toString());
                attributeName = new StringBuilder();
            } else {
                if (attributeName.length() == 0) {
                    attributeName.append(element);
                } else {
                    attributeName.append(" ").append(element);
                }


            }
        }

        record.add(attributeName.toString());

        return record;

    }

    /**
     * Converts a record into a Task object.
     *
     * @param record The record to be converted into Task object.
     * @return The converted Task object.
     */
    public Task recordToTask(List<String> record) throws IllegalStateException {
        boolean isDone = record.get(1).equals("1");
        Task task;

        switch (record.get(0)) {
        case "T":
            task = new Task(record.get(2), 1, "Null", "Null", isDone);
            break;

        case "D":
            task = new Task(record.get(2), 2, "Null", record.get(3), isDone);
            break;

        case "E":
            task = new Task(record.get(2), 3, record.get(3), record.get(4), isDone);
            break;

        default:
            throw new IllegalStateException("Unexpected value: " + record.get(0));
        }

        return task;
    }

    /**
     * Saves the list back to file.
     *
     * @param userList The list of tasks to store.
     * @param numberOfElements The number of valid tasks in the list.
     */
    public void saveList(Task[] userList, int numberOfElements) {

        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < numberOfElements; i++) {
                writer.write(userList[i].getStringForRecordingInTextFile());
                writer.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getListPointer() {
        return listPointer;
    }








}
