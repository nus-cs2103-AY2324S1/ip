package duke;

import duke.exception.DukeFileNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void load_validFile_contentLoaded() {

        // test on non-existing file
        
        File f = new File("./dukeTest.txt");
        try {
            f.createNewFile();
        } catch (IOException ignored) {
            fail("Couldn't find file");
        }

        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write("[D] / 0 / play games / 2023-12-31 / 14:00\n");
            fw.write("[T] / 1 / read\n");
            fw.write("[E] / 0 / do homework / 2023-12-01 / 18:00 / 2023-12-31 / 07:00\n");
            fw.close();
        } catch (IOException e) {
            fail("Couldn't write to file");
        }

        //ensures that there is a file so that the constructor of Storage is most likely to not encounter any bugs
        Storage s = new Storage("./dukeTest.txt");
        ArrayList<String> strings = s.load();
        Scanner sc = null;

        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            fail("Couldn't scan file");
        }

        for (int i=0; i<strings.size(); i++) {
            if (!sc.hasNextLine()) {        //array has more lines than the file
                fail();
            }
            String line = sc.nextLine();
            assertEquals(strings.get(i), line);
        }

        if (sc.hasNextLine()) {             //file has more lines than the array
            fail();
        }

        // test on existing file

        Storage s2 = new Storage("./dukeTest2.txt");
        ArrayList<String> strings2 = s2.load();
        Scanner sc2 = null;

        try {
            sc2 = new Scanner(f);
        } catch (FileNotFoundException e) {
            fail("Couldn't scan file");
        }

        for (int i=0; i<strings.size(); i++) {
            if (!sc2.hasNextLine()) {        //array has more lines than the file
                fail();
            }
            String line = sc2.nextLine();
            assertEquals(strings2.get(i), line);
        }

        if (sc2.hasNextLine()) {             //file has more lines than the array
            fail();
        }
    }
}
