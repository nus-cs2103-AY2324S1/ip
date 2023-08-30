import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String delete = "delete";
    private static final Path filePath = Paths.get("./data/bot.txt");
    private Tasklist todolist;
    private Ui ui;

    private Duke() {
        this.todolist = new Tasklist();
        this.ui = new Ui();
    }

    private void exit() {
        try {
            this.saveFile();
            ui.exit();
        } catch (IOException e) {
            System.out.println("Error when saving data!");
        }
    }

    private boolean respond(String s) {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        //2 over so that delete and mark cannot have blank input
        if (s.length() >= 6) {
            check1 = str.substring(0, 4);
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 6);
        }
        if (s.equals(bye)) {
            this.exit();
            return false;
        } else if (s.equals(list)) {
            todolist.printlist();
            return true;
        } else if (check1.equals(mark)) {
            todolist.mark(Integer.parseInt(str.substring(5, str.length())));
            return true;
        } else if (check2.equals(unmark)) {
            todolist.unmark(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else if (check2.equals(delete)) {
            todolist.delete(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else {
            try {
                todolist.addtolist(s);
            } catch (DukeMissingArgumentException | DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
            return true;
        }
    }



    private void saveFile() throws IOException {
        if (!Files.exists(filePath.getParent())) {
            try {
                // Create the directory
                Files.createDirectories(filePath.getParent());
                System.out.println("Directory created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating directory.");
            }
        }
        if (!Files.exists(filePath)) {
            try {
                // Create the file
                Files.createFile(filePath);
                System.out.println("File created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating file.");
            }
        }
        FileWriter fw = new FileWriter(String.valueOf(filePath), false);
        BufferedWriter bw = new BufferedWriter(fw);
        todolist.savelist(bw);
        bw.close();
        fw.close();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Duke d = new Duke();
        d.ui.greet();
        while (s.hasNextLine()) {
            String t = s.nextLine();
            if (!d.respond(t)) {
                return;
            }
        }
    }
}
