import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File f;
    public Storage(String filepath) {
        this.f = new File(filepath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    public ArrayList<String> load() {
        Scanner sc;
        ArrayList<String> lines = new ArrayList<>();
        try {
            sc = new Scanner(f);
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            lines.add(s);
        }
        return lines;
    }

    public void store(ArrayList<String> strings) {
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            for (String s : strings) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

 /*   *//*
    /**
     * Updates the file with the current list.
     *//*
    private static void updateFile() throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task t : tasks) {
            fw.write(t.toStringInFile() + "\n");
        }
        fw.close();
    }

    private static void readFile() throws IOException {
        String path = "src/main/java/";
        String fileName = "duke.txt";

        f = new File(path, fileName);
        if (f.createNewFile()) {
            return;
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] chars = s.split(" / ");
            String type = chars[0];
            boolean isDone = chars[1].equals("1");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
            Task t;
            switch (type) {
            case "[T]":
                t = new Todo(chars[2]);
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            case "[D]":
                if (chars.length == 4) {
                    t = new Deadline(chars[2], LocalDate.parse(chars[3]));
                } else {
                    t = new Deadline(chars[2], LocalDate.parse(chars[3]),
                            LocalTime.parse(chars[4], dateFormat));
                }
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            case "[E]":
                if (chars.length == 7) {
                    t = new Event(chars[2], LocalDate.parse(chars[3]), LocalTime.parse(chars[4], dateFormat),
                            LocalDate.parse(chars[5]), LocalTime.parse(chars[6]));
                } else if (chars.length == 5) {
                    t = new Event(chars[2], LocalDate.parse(chars[3]), LocalDate.parse(chars[4]));
                } else {
                    if (chars[5].length() > 5) {
                        t = new Event(chars[2], LocalDate.parse(chars[3]), LocalTime.parse(chars[4], dateFormat), //chars[5] is a date, last one is date
                                LocalDate.parse(chars[5]));
                    } else {
                        t = new Event(chars[2], LocalDate.parse(chars[3]), LocalDate.parse(chars[4]),
                                LocalTime.parse(chars[5], dateFormat));
                    }
                }
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            }
        }
    }*/


}
