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
            } catch (DukeMissingArgumentException | DukeInvalidArgumentException e) {
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
    private void addtolist(String s) throws DukeMissingArgumentException, DukeInvalidArgumentException {
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
                throw new DukeMissingArgumentException();
            } else {
                System.out.println("Got it. I've added this task:");
                Todo t = new Todo(str.substring(5, str.length()).toString());
                todolist.add(t);
                System.out.println(t.toString());
            }
        } else if (check2.equals("deadline")) {
            if (s.length() <= 9) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(9, str.length()).toString();
                    String[] arr = t.split("/by ");
                    LocalDateTime deadline = LocalDateTime.parse(arr[1], formatter);
                    Deadline d = new Deadline(arr[0], deadline);
                    todolist.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                }
            }
        } else if (check3.equals("event")) {
            if (s.length() <= 6) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(6, str.length()).toString();
                    String[] arr = t.split("/from ");
                    String[] times = arr[1].split(" /to ");
                    LocalDateTime startTime = LocalDateTime.parse(times[0], formatter);
                    LocalDateTime endTime = LocalDateTime.parse(times[1], formatter);
                    if (startTime.isAfter(endTime)) {
                        System.out.println("\tEnd time must be after the start time!\n");
                        return;
                    }
                    Event e = new Event(arr[0], startTime, endTime);
                    todolist.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                }
            }
        } else {
            throw new DukeInvalidArgumentException();
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
