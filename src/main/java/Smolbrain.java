import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Smolbrain {

    public static final String DATA_PATH = "data.txt";
    public static boolean loading = true;
    public static boolean run = true;
    public static ArrayList<Task> data = new ArrayList<>();

    private static void readFile(String filePath) {
        File f = new File(filePath); // create a File for the given file path
        ArrayList<String> strings = new ArrayList<>();
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            System.out.println("Welcome back!!!");
            while (s.hasNext()) {
                strings.add(s.nextLine());
            }
            for (int i = 0; i < strings.size(); i++) {
                String type = strings.get(i).substring(0, 1);
                String marked = strings.get(i).substring(1, 2);
                String remain = strings.get(i).substring(2);
                String txt = "";
                switch (type) {
                    case "T":
                        txt = "todo " + remain;
                        break;

                    case "D":
                        txt = "deadline " + remain;
                        break;

                    case "E":
                        txt = "event " + remain;
                        break;
                }
                parse(txt.split(" "), data);
                if (marked.equals("1")) {
                    data.get(i).mark();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("New user!!");
            try {
                f.createNewFile();
            } catch (IOException e1) {
                System.out.println(e1);
            }
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(DATA_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String txt) throws IOException {
        FileWriter fw = new FileWriter(DATA_PATH); // create a FileWriter in append mode
        fw.write(txt);
        fw.close();
    }

    private static void updateData(ArrayList<Task> data) throws IOException {
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                writeToFile(data.get(i).encode());
            } else {
                appendToFile(System.lineSeparator() + data.get(i).encode());
            }
        }
    }

    public static void parse(String[] words, ArrayList<Task> data) {
        String descr = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyy HHmm");
        LocalDateTime dateTime;
        LocalDateTime dateTime2;
        try {
            switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i + 1) + ". " + data.get(i));
                    }
                    break;

                case "todo":
                    if (words.length < 2) {
                        throw new MissingDescriptionException("todo");
                    }
                    for (int i = 1; i < words.length; i++) {
                        descr = descr + words[i] + " ";
                    }
                    descr = descr.substring(0, descr.length() - 1);
                    Todo todo = new Todo(descr);
                    data.add(todo);
                    if (!loading) {
                        System.out.println("Got it. I've added this task: \n" + todo);
                        System.out.println("Now you have " + data.size() + " tasks in the list.");
                    }
                    break;

                case "deadline":
                    boolean by = false;
                    String by_text = "";
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/by")) {
                            by = true;
                            continue;
                        }
                        if (by) {
                            by_text = by_text + words[i] + " ";
                        } else {
                            descr = descr + words[i] + " ";
                        }
                    }
                    if (descr.equals("")) {
                        throw new MissingDescriptionException("deadline");
                    } else if (by_text.equals("")) {
                        throw new MissingTimeException("ending", "deadline");
                    }
                    descr = descr.substring(0, descr.length() - 1);
                    by_text = by_text.substring(0, by_text.length() - 1);

                    dateTime = LocalDateTime.parse(by_text, formatter);
                    Deadline deadline = new Deadline(descr, dateTime);
                    data.add(deadline);
                    if (!loading) {
                        System.out.println("Got it. I've added this task: \n" + deadline);
                        System.out.println("Now you have " + data.size() + " tasks in the list.");
                    }
                    break;

                case "event":
                    boolean from = false;
                    boolean to = false;
                    String from_text = "";
                    String to_text = "";
                    for (int i = 1; i < words.length; i++) {
                        if (words[i].equals("/from")) {
                            from = true;
                            continue;
                        } else if (words[i].equals("/to")){
                            to = true;
                            continue;
                        }
                        if (to) {
                            to_text = to_text + words[i] + " ";
                        } else if (from) {
                            from_text = from_text + words[i] + " ";
                        } else {
                            descr = descr + words[i] + " ";
                        }
                    }
                    if (descr.equals("")) {
                        throw new MissingDescriptionException("event");
                    } else if (from_text.equals("")) {
                        throw new MissingTimeException("start", "event");
                    } else if (to_text.equals("")) {
                        throw new MissingTimeException("end", "event");
                    }
                    descr = descr.substring(0, descr.length() - 1);
                    from_text = from_text.substring(0, from_text.length() - 1);
                    to_text = to_text.substring(0, to_text.length() - 1);
                    dateTime = LocalDateTime.parse(from_text, formatter);

                    dateTime2 = LocalDateTime.parse(to_text, formatter);
                    Event event = new Event(descr, dateTime, dateTime2);
                    data.add(event);
                    if (!loading) {
                        System.out.println("Got it. I've added this task: \n" + event);
                        System.out.println("Now you have " + data.size() + " tasks in the list.");
                    }
                    break;

                case "mark":
                    if (words.length < 2) {
                        throw new InvalidNumberException("mark");
                    }
                    int marknum;
                    try {
                        marknum = Integer.parseInt(words[1]);
                        data.get(marknum - 1).mark();
                        System.out.println("Nice! I've marked this task as done: \n" + data.get(marknum - 1));
                    } catch (NumberFormatException e) {
                        throw new InvalidNumberException("mark");
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidRangeException();
                    }
                    break;

                case "unmark":
                    if (words.length < 2) {
                        throw new InvalidNumberException("unmark");
                    }
                    int unmarknum;
                    try {
                        unmarknum = Integer.parseInt(words[1]);
                        data.get(unmarknum - 1).unmark();
                        System.out.println("OK, I've marked this task as not done yet: \n" + data.get(unmarknum - 1));
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("unmark");
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidRangeException();
                    }
                    break;

                case "delete":
                    if (words.length < 2) {
                        throw new InvalidNumberException("delete");
                    }
                    int delnum;
                    try {
                        delnum = Integer.parseInt(words[1]);
                        String task = data.get(delnum - 1).toString();
                        data.remove(delnum - 1);
                        System.out.println("Noted. I've removed this task: \n" + task);
                        System.out.println("Now you have " + data.size() + " tasks in the list.");
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("delete");
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidRangeException();
                    }
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    run = false;
                    break;

                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        } catch (InvalidRangeException | MissingDescriptionException | MissingTimeException | InvalidNumberException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        readFile("data.txt");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);

        loading = false;

        while(run) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            String[] words = input.split(" ");

            parse(words, data);

            try {
                updateData(data);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("____________________________________________________________\n");
        }

    }
}
