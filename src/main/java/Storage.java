import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private File file;
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            Scanner scan = new Scanner(System.in);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File load() {
        return this.file;
    }

    public void rewrite(String newText) {
        try {
            FileWriter fw;
            Scanner sc = new Scanner(this.file);
            fw = new FileWriter(filePath);
            fw.write(newText);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
    public void add(Task task) {
        try {
            FileWriter fw;
            Scanner sc = new Scanner(this.file);
            if (sc.hasNextLine()) {
                fw = new FileWriter(filePath, true);
                fw.write("\r\n");
            } else {
                fw = new FileWriter(filePath);
            }
            fw.write(task.writeToFile());
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int taskNumber) {
        try {
            FileWriter fw;
            Scanner sc = new Scanner(this.file);
            fw = new FileWriter(filePath);
            fw.write(task.writeToFile());
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
