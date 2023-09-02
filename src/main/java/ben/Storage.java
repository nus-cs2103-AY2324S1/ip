package ben;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Storage {
    private File f;

    public Storage(File f) {
        this.f = f;
    }

    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(f);
        writer.write(tasks.saveTasks());
        writer.close();
    }

    public LocalDateTime dateTimeParse(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + " 2359", formatter);
        }

        return LocalDateTime.parse(dateTime, formatter);
    }

    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split("[|]");
            String command = words[0];

            switch (command.toLowerCase()) {
            case "t":
                tasks.add(new ToDos(words[2], Boolean.parseBoolean(words[1])), false);
                break;
            case "d":
                tasks.add(new Deadlines(words[2],
                        Boolean.parseBoolean(words[1]), dateTimeParse(words[3])), false);
                break;
            case "e":
                tasks.add(new Events(words[2],
                                Boolean.parseBoolean(words[1]), dateTimeParse(words[3]), dateTimeParse(words[4])),
                        false);
                break;
            }
        }
    }
}
