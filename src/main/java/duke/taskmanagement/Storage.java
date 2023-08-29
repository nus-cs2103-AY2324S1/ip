package duke.taskmanagement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;
    private List<Task> ls = new ArrayList<>();
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeToFile_exceptionThrown(String filePath, String textToAdd) {
        try {
            writeToFile(filePath, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public List<Task> loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line= reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                //System.out.println(isDone);
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
                ls.add(task);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

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
