package smolbrain;

import smolbrain.exception.*;
import smolbrain.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(text);
        fw.close();
    }

    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH); // create a FileWriter in append mode
        fw.write(text);
        fw.close();
    }


    // Reads the file contents, passes each line into parser
    public ArrayList<Task> load() {

        File f = new File(FILE_PATH);
        ArrayList<Task> tasklist = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                strings.add(s.nextLine());
            }

            for (int i = 0; i < strings.size(); i++) {
                String type = strings.get(i).substring(0, 1);
                String marked = strings.get(i).substring(1, 2);
                String remain = strings.get(i).substring(2);
                String txt = "";

                switch (type) {
                    case "T":
                        txt = "todo " + remain;
                        break;

                    case "D":
                        txt = "deadline " + remain;
                        break;

                    case "E":
                        txt = "event " + remain;
                        break;
                }

                try {
                    Task task =  Parser.parseLoading(txt);
                    tasklist.add(task);
                    if (marked.equals("1")) {
                        task.mark();
                    }
                } catch (InvalidNumberException | InvalidRangeException | MissingTimeException |
                         InvalidDateTimeException | MissingDescriptionException e) {
                    new Ui().showError(e);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException e1) {
                new Ui().showError(e1);
            }
        }
        return tasklist;
    }

}
