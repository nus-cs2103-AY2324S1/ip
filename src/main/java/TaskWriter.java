import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskWriter {
    private String filePath;

    public TaskWriter(String filePath) {
        this.filePath = filePath;
    }

    public void addLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while adding the line: " + e.getMessage());
        }
    }

    public void deleteLine(int lineNumber) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            lines.remove(lineNumber - 1);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("An error occurred while writing back to the file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid line number.");
        }
    }
}
