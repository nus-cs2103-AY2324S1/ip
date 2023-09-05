package Duke.storage;

import Duke.exception.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filePath;
    private File file;
    private BufferedWriter bufferedWriter;

    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        file = new File(filePath);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            throw new FileNotFoundException(filePath);
        }
    }

    public void addLine(String line) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getLine(int index) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int currentLine = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if(index == currentLine) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public void removeLine(int lineIndex) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int currentLine = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine != lineIndex) {
                    lines.add(line);
                }
                currentLine++;
            }
            bufferedWriter.close();
            bufferedReader.close();

            file = new File(filePath);

            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String lineToAdd : lines) {
                bufferedWriter.write(lineToAdd);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {   // Create a FileWriter with the given file path
        try {
            FileWriter fileWriter = new FileWriter(file);
            // Overwrite the file content with an empty string
            fileWriter.write("");
            // Close the FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
