package Duke.storage;

import Duke.exception.FileNotFoundException;
import java.io.*;

public class Storage {
    String filePath;
    private final File file;
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

    public void AddLine(String line) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String GetLine(int index) {
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


    public void RemoveLine(int lineIndex) {
        File tempFile;
        try {
            tempFile = new File("C:\\Users\\ortt2\\Documents\\ip\\src\\data\\temp.txt");
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile, false));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int currentLine = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine != lineIndex) {
                    tempWriter.write(line);
                    tempWriter.newLine();
                }
                currentLine++;
            }
            tempWriter.close();
            bufferedWriter.close();
            bufferedReader.close();
            if (!tempFile.renameTo(file)) {
                System.err.println("Could not rename temp file.");
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            System.out.println("Line deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
