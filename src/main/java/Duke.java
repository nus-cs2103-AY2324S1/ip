import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Line line = new Line();
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) {
        Tasks tasks = readData();
        printGreetings();
        Scanner s = new Scanner(System.in);

        while (true) {
            String text = s.nextLine();
            if (text.equals("")) {
                System.out.println(line);
                System.out.println("    Please enter something :-)");
                System.out.println(line);
                continue;
            }

            if (text.equals("bye")) {
                break;
            }

            tasks.handle(text, false);
        }
        printExit();
    }

    private static Tasks readData() {
        Tasks tasks = new Tasks(FILE_PATH);
        File myObj = new File(FILE_PATH);
        System.out.println(line);
        try {
            Scanner myReader = new Scanner(myObj);
            System.out.println("    Data has been restored from " + FILE_PATH);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tasks.handle(data, true);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("    Data file not found, creating a new one");
            try {
                myObj.createNewFile();
            } catch (IOException e) {
                System.out.println("    Error creating new file, quitting program now...");
                System.exit(1);
            }
        }
        System.out.println(line);
        return tasks;
    }

    private static void printGreetings() {
        System.out.println(line);
        System.out.println("    Hello I'm lynn the koala <3");
        System.out.println("    What can I do for you?");
        System.out.println(line);
    }

    private static void printExit() {
        System.out.println(line);
        System.out.println("    Bye. Hpoe to see you again soon!");
        System.out.println(line);
    }
}
