import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private ArrayList<Task> todolist;

    private Duke() {
        this.todolist = new ArrayList<>();
        greet();
    }

    private static void greet() {
        System.out.println(greeting);
    }

    private void exit() {
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
        try {
            this.saveFile();
            System.out.println(end);
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
            printlist();
            return true;
        } else if (check1.equals(mark)) {
            mark(Integer.parseInt(str.substring(5, str.length())));
            return true;
        } else if (check2.equals(unmark)) {
            unmark(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else if (check2.equals(delete)) {
            delete(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else {
            try {
                addtolist(s);
            } catch (InputMismatchException e) {
                System.out.println(e.toString());
            }
            return true;
        }
    }

    private void mark(int i) {
        todolist.get(i - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(todolist.get(i - 1).toString());
    }

    private void unmark(int i) {
        todolist.get(i - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(todolist.get(i - 1).toString());
    }
    private void addtolist(String s) {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        String check3 = "";
        if (s.length() >= 4) {
            check1 = str.substring(0, 4);
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 8);
        }
        if (s.length() >= 5) {
            check3 = str.substring(0, 5);
        }
        if (check1.equals("todo")) {
            if (s.length() <= 5) {
                throw new InputMismatchException("task blank");
            } else {
                System.out.println("Got it. I've added this task:");
                Todo t = new Todo(str.substring(5, str.length()).toString());
                todolist.add(t);
                System.out.println(t.toString());
            }
        } else if (check2.equals("deadline")) {
            if (s.length() <= 9) {
                throw new InputMismatchException("task blank");
            } else {
                System.out.println("Got it. I've added this task:");
                String t = str.substring(9, str.length()).toString();
                String[] arr = t.split("/by ");
                Deadline d = new Deadline(arr[0], arr[1]);
                todolist.add(d);
                System.out.println(d.toString());
            }
        } else if (check3.equals("event")) {
            if (s.length() <= 6) {
                throw new InputMismatchException("task blank");
            } else {
                System.out.println("Got it. I've added this task:");
                String t = str.substring(6, str.length()).toString();
                String[] arr = t.split("/from ");
                String[] arrr = arr[1].split("/to ");
                Event e = new Event(arr[0], arrr[0], arrr[1]);
                todolist.add(e);
                System.out.println(e.toString());
            }
        } else {
            throw new InputMismatchException("invalid input");
        }
        System.out.println("Now you have " + todolist.size() + " tasks in the list.");

    }

    private void delete(int i) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(todolist.get(i - 1).toString());
        todolist.remove(i - 1);
        System.out.println("Now you have " + todolist.size() + " tasks in the list.");
    }

    private void printlist() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.todolist.size(); ++i) {
            Task t =  this.todolist.get(i - 1);
            System.out.println(i + ". " + t.toString());
        }
    }

    private void saveFile() throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath), false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Task task : this.todolist) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Duke d = new Duke();
        while (s.hasNextLine()) {
            String t = s.nextLine();
            if (!d.respond(t)) {
                return;
            }
        }
    }
}
