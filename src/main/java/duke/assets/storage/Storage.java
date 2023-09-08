package duke.assets.storage;

import duke.assets.parser.Parser;
import duke.dukeexceptions.CorruptDataException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private TaskList tasklist;
    private Parser parser;
    public Storage() {
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    public void writeToFile() {
        try {
            File myFile = new File("./src/main/java/duke/data/duke.txt");
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(tasklist.saveToFileFormat());
            pw.close();
        } catch (IOException IOExcept) {
            System.out.println("ChadGPT: Unfortunately there was an unexpected error when reading your data file.");
            IOExcept.printStackTrace();
        }
    }

    public boolean readFromFile() {
        try {
            File myFile = new File("./src/main/java/duke/data/duke.txt");
            myFile.createNewFile();
            Scanner sc = new Scanner(myFile);
            Parser dataParser = new Parser();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                dataParser.passDataCommand(nextLine, tasklist);
            }
            return true;
        } catch (IOException IOExcept) {
            System.out.println("ChadGPT: Unfortunately there was an unexpected error when reading your data file.");
            IOExcept.printStackTrace();
        } catch (CorruptDataException corruptDataExcept) {
            System.out.println("ChadGPT: Data is corrupt at: \"" + corruptDataExcept.getCorruptLine() +
                    "\". Please fix and press enter to proceed, or type the command \"exit\" to quit program");
            tasklist.clearList();
        }
        return false;
    }

    public void passUserCommand(String nextLine) {
        this.parser.passUserCommand(nextLine, tasklist);
    }
}
