package duke.taskmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private List<Task> listOfTask = new ArrayList<>();

    /**
     * Constructor for Storage class.
     * @param filePath The path where you wish to store the text.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * To write string into file
     * @param filePath The path of the file where you wish to write into.
     * @param textToAdd The String you wish to write into the file.
     * @throws IOException If the path is not available.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * To handle the exception when using writeToFile method.
     * @param filePath The path of the file where you wish to write into.
     * @param textToAdd The String you wish to write into the file.
     */
    public void writeToFile_exceptionThrown(String filePath, String textToAdd) {
        try {
            writeToFile(filePath, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * To load the text line by line from the file
     * and store it into a List<Task>
     * @return The List<Task> which each element is loaded from the text file.
     */
    public List<Task> loadData(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line= reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task;

                if (type.equals("T")) {
                    task = new ToDo(description, isDone);
                } else if (type.equals("E")) {
                    String from = parts[3];
                    String till = parts[4];
                    task = new Event(description, from, till, isDone);
                } else if (type.equals("D")) {
                    String deadline = parts[3];
                    task = new Deadline(description, deadline, isDone);
                } else {
                    continue;
                }
                listOfTask.add(task);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfTask;
    }

    /**
     * To delete a certain line in the text file
     * @param lineIndexToDelete The index of the line (task) you
     *                          want to delete.
     */
    public void deleteLine(int lineIndexToDelete) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line=reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            if (lineIndexToDelete >= 0 && lineIndexToDelete-1 < lines.size()) {
                lines.remove(lineIndexToDelete-1);
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                for(String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();
            } else {
                System.out.println("invalid index");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To modify the text file as the user modify the
     * status of the task to 'done'.
     * @param index The index of task you wish to
     *              change the status to done.
     */
    public void changeToDone(int index) {
                List<String> lines = new ArrayList<>();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();

                    if (index >= 0 && index-1 < lines.size()) {
                        String lineToModify = lines.get(index-1);
                        String[] parts = lineToModify.split(" \\| ");

                        if (parts.length >= 2) {
                            parts[1] = "1"; // Change "0" to "1" for isDone status
                            lineToModify = String.join(" | ", parts);
                            lines.set(index-1, lineToModify);

                            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                            for (String updatedLine : lines) {
                                writer.write(updatedLine);
                                writer.newLine();
                            }

                            writer.close();
                        } else {
                            System.out.println("Invalid line format.");
                        }
                    } else {
                        System.out.println("Invalid line index.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    /**
     * To modify the text file as the user modify the
     * status of the task to 'undone'.
     * @param index The index of task you wish to
     *              change the status to undone.
     */
    public void changeToUnDone(int index) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            if (index >= 0 && index-1 < lines.size()) {
                String lineToModify = lines.get(index-1);
                String[] parts = lineToModify.split(" \\| ");

                if (parts.length >= 2) {
                    parts[1] = "0"; // Change "0" to "1" for isDone status
                    lineToModify = String.join(" | ", parts);
                    lines.set(index-1, lineToModify);

                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    writer.close();
                } else {
                    System.out.println("Invalid line format.");
                }
            } else {
                System.out.println("Invalid line index.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
