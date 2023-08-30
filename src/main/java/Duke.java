import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Duke {
    private static final ArrayList<Task> lst = new ArrayList<>();

    private static File f;

    public static void main(String[] args) {

        accessFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "____________________________________________________________\n"
                        + "Hello! I'm ET\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n"
        );

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            checkInput(input);
            input = scanner.nextLine();
        }

        saveToFile();
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
    }

    public static void checkInput(String str) {
        try {
            if (str.startsWith("mark")) {
                String num = str.substring(5);
                int i = Integer.parseInt(num);
                Task t = lst.get(i - 1);
                t.markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:\n"
                        + t + "\n"
                        + "____________________________________________________________\n"
                );
            } else if (str.startsWith("unmark")) {
                String num = str.substring(7);
                int i = Integer.parseInt(num);
                Task t = lst.get(i - 1);
                t.markAsNotDone();
                System.out.println("____________________________________________________________\n"
                        + "OK, I've marked this task as not done yet:\n"
                        + t + "\n"
                        + "____________________________________________________________\n"
                );
            } else if (str.equals("list")) {
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:");
                for (int i = 1; i <= lst.size(); i++) {
                    Task t = lst.get(i - 1);
                    System.out.println(i + ". " + t.toString());
                }
                System.out.println("____________________________________________________________");
            } else if (str.startsWith("delete")) {
                String num = str.substring(7);
                int i = Integer.parseInt(num);
                Task t = lst.get(i - 1);
                lst.remove(i - 1);
                System.out.println("____________________________________________________________\n"
                        + "Noted. I've removed this task:\n"
                        + t + "\n"
                        + "Now you have " + lst.size() + " tasks in the list.\n"
                        + "____________________________________________________________\n"
                );
            } else {
                addList(str);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addList(String str) throws InvalidInputException, IncompleteInputException {
        if (str.startsWith("todo ")) {
            if (str.length() < 6) throw new IncompleteInputException("todo");
            String des = str.substring(5);
            Todo todo = new Todo(des);
            lst.add(todo);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + todo + "\n"
                    + "Now you have " + lst.size() + " tasks in the list.\n"
                    + "____________________________________________________________\n"
            );

        } else if (str.startsWith("deadline ")) {
            String[] words = str.split("/");
            if (words.length != 2 || words[0].length() < 10 || words[1].length() < 4) {
                throw new IncompleteInputException("deadline");
            }
            String des = words[0].substring(9);
            String by = words[1].substring(3);
            Deadline dl = new Deadline(des, by);
            lst.add(dl);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + dl + "\n"
                    + "Now you have " + lst.size() + " tasks in the list.\n"
                    + "____________________________________________________________\n"
            );

        } else if (str.startsWith("event ")) {
            String[] words = str.split("/");
            if (words.length != 3 || words[0].length() < 7 || words[1].length() < 6 || words[2].length() < 4) {
                throw new IncompleteInputException("deadline");
            }
            String des = words[0].substring(6);
            String from = words[1].substring(5);
            String to = words[2].substring(3);
            Event event = new Event(des, from, to);
            lst.add(event);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + event + "\n"
                    + "Now you have " + lst.size() + " tasks in the list.\n"
                    + "____________________________________________________________\n"
            );
        } else {
            throw new InvalidInputException();
        }
    }

    public static void accessFile() {
        try {
            f = new File("./src/main/data/duke.txt");
            if (!f.createNewFile()) {
                readFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void printFile() {
        try {
            int count = 1;
            File f = new File("./src/main/data/duke.txt");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                System.out.println(count + ". " + fScanner.nextLine());
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void writeToFile(String pathname, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(pathname, true);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void readFile() {
        try {
            File f = new File("./src/main/data/duke.txt");
            Scanner fScanner = new Scanner(f);
            while (fScanner.hasNextLine()) {
                String s = fScanner.nextLine();
                String[] arr = s.split("--");
                if (arr[0].equals("T")) {
                    Todo t = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        t.markAsDone();
                    }
                    lst.add(t);
                } else if (arr[0].equals("D")) {
                    Deadline dl = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        dl.markAsDone();
                    }
                    lst.add(dl);
                } else if (arr[0].equals("E")){
                    Event e = new Event(arr[2], arr[3], arr[4]);
                    if (arr[1].equals("1")) {
                        e.markAsDone();
                    }
                    lst.add(e);
                }
            }
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void saveToFile(){
            f.delete();
            f = new File("./src/main/data/duke.txt");
            for (int i = 1; i <= lst.size(); i++) {
                Task t = lst.get(i - 1);
                writeToFile("./src/main/data/duke.txt", t.toFileString());
            }
    }

}
