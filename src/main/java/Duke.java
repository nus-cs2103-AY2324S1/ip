import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke{
    public static void main(String[] args) {
        String saveFileDir = "./data/duke.txt";
        Ui Ui = new Ui();
        Storage storage = new Storage(saveFileDir);
        ArrayList<Task> myList = new ArrayList<Task>(); // Create an ArrayList object
        myList = storage.loadSaveFile();
        Ui.hello();
        Scanner myScanner = new Scanner(System.in);
        Parser parser = new Parser(myList, Ui, storage, myScanner);

        while(myScanner.hasNext()){
            String inValue = myScanner.next();
            parser.parseInput(inValue);
        }
    }
}

