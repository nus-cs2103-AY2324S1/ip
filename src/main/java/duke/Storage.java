package duke;

import duke.exception.DukeFileNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;
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
}
