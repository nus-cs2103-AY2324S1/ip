import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "../../duke.txt";
    private File file;

    public Storage(File file) {
        this.file = file;
    }

    public Storage(String pathName) throws DukeException {
        this.file = new File(pathName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to find or create file.");
        }
    }

    public Storage() throws DukeException {
        this.file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to find or create file.");
        }
    }

    public String[] readLines() throws DukeException {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }

        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        sc.close();
        return lines.toArray(new String[]{});
    }

    public void write(String s) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.write(s);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to duke.txt");
        }
    }
}
