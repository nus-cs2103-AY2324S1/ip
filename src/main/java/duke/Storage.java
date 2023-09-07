package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Storage {
    private final String dir;

    public Storage(String dir) {
        this.dir = dir;
    }

    public void saveTasksToFile(TaskList tasks) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter((fileWriter));
            for (Task t : tasks.getList()) {
                bufferedWriter.write(t.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            Ui.Error(e);
        }
    }

    public ArrayList<Task> loadSaveFile() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<Task> input = new ArrayList<Task>();
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            fileReader = new FileReader(dir);
            bufferedReader = new BufferedReader((fileReader));
            String nextLine;
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                String[] splitted = nextLine.split(" \\| ");
                switch (splitted[0]) {
                case "T":
                    input.add(new ToDo(splitted[2], (splitted[1] == "1")));
                    break;
                case "E":
                    input.add(new Event(splitted[2], splitted[3], splitted[4], (splitted[1] == "1")));
                    break;
                case "D":
                    int key = Integer.valueOf(splitted[4]);
                    if (key == 2) {
                        input.add(new Deadline(splitted[2], LocalDateTime.parse(splitted[3]), (splitted[1] == "1")));
                    } else if (key == 1) {
                        input.add(new Deadline(splitted[2], LocalDate.parse(splitted[3]), (splitted[1] == "1")));
                    } else {
                        Ui.badDateLoaded();
                    }
                    break;
                }
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            Ui.Error(e);
        }
        return input;
    }
}
