package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class TextFileHandler {

    /**
     * Creates a .txt file along with the necessary directories.
     * 
     * @param filePath Path of the file.
     * @throws IOException if the file path is not .txt
     */
    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (!isTxtFile(file)) {
            throw new IOException("File is not a text file.");
        }

        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Writes texts to .txt file.
     * 
     * @param filePath Path of the file.
     * @param text     Text to be written in the file.
     * @throws IOException if file cannot be opened.
     */
    public static void writeText(String filePath, String text) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(text);
        writer.close();
    }

    /**
     * Returns the lines of the file, creates file if not present.
     * 
     * @param filePath Path of the file.
     * @return A array of string which are lines in the .txt file.
     * @throws IOException if file cannot be opened.
     */
    public static String[] readLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    /**
     * Checks if the path given is a .txt file.
     * 
     * @param filePath Path of the file.
     * @return true is file is .txt, false otherwise.
     */
    public static boolean isTxtFile(File filePath) {
        String fileName = filePath.getName();
        return fileName.endsWith(".txt");
    }

}
