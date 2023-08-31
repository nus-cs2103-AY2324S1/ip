
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

public class Kiera {
    protected static String NAME = "kiera";
    protected static String LINE = "   ---------------------------------------------";
    protected static String HELLO = LINE
                + "\n"
                + "    " 
                + "hi, it's kiera.\n" 
                + "    " 
                + "what do you need?\n"
                + LINE;
    protected static String BYE =  LINE
                + "\n"
                + "    " 
                + "muaks! <3\n"
                + LINE;
    
    protected static ArrayList<Task> store = new ArrayList<>();
    protected static String filePath = "./data/storage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Kiera(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void readFile(String filePath) throws KieraException {
        File f = new File(filePath);
        String result = "";
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                Task t;
                String next = s.nextLine();
                String[] r = next.split(" // ");
                String type = r[0];
                String done = r[1];
                String desc = r[2];
                if (type.equals("T")) {
                    System.out.println(desc);
                    t = new Todo(desc);
                } else if (type.equals("D")) {
                    t = new Deadline(desc);
                } else {
                    t = new Event(desc);
                }
                if (done.equals("X")) {
                    t.markAsDone();
                } else {
                    t.markAsUndone();
                }
                store.add(t);

            }

        } catch (FileNotFoundException e) {
            throw new KieraException("no previous data found; starting with an empty list!");
        }
        System.out.println(result);
    }
    public static void writeToFile(String filePath, String text) throws KieraException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new KieraException("data not saved..." + e);
        }
    }

    public static void list() {
        String result = "";
        if (store.isEmpty()) {
            System.out.println(LINE + "\n    nothing for you to do yet! \n" + result + LINE);
            return;
        }
        for (int i = 0; i < store.size(); i++) {
            Task t = store.get(i);

            result = result +  "    " + (i + 1) + ". " + t +  "\n";

        }
        System.out.println(LINE + "\n    you need to get these done today:\n" + result + LINE);
    }

    public static void mark(String input) {
        try {
            int unchecked = Integer.parseInt(input.replace("mark ", "")) - 1;
            Task t = store.get(unchecked);

            t.markAsDone();
            System.out.println(LINE
                    + "    \n    yay, one task down: \n"
                    + "     "
                    + t
                    + "\n"
                    + LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }

    }

    public static void unmark(String input) {
        try {
            int checked = Integer.parseInt(input.replace("unmark ", "")) - 1;
            Task t = store.get(checked);

            t.markAsUndone();

            System.out.println(LINE
                    + "    \n    ok, this task is not done yet: \n"
                    + "     "
                    + t
                    + "\n"
                    + LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }
        
    }


    public static void addTask(String input, String type) throws KieraException {
        Task t;
        if (type.equals("todo")) {
            if (input.isEmpty()) {
                throw new KieraException( "todo can't be empty! follow the format: todo (task).");
            }
            t = new Todo(input);
        } else if (type.equals("deadline")) {
            if (input.isEmpty()) {
                throw new KieraException("deadline can't be empty! follow the format: deadline (task) /by (date).");
            }
            t = new Deadline(input);
        } else {
            if (input.isEmpty()) {
                throw new KieraException( "event can't be empty! follow the format: event (task) /from (start) /to (end).");
            }
            t = new Event(input);
        }
        
        store.add(t);

        String plural = store.size() == 1 ? "task" : "tasks";

        System.out.println(LINE
                + "\n    "
                + "alright, one more task has been added: \n"
                + "      "
                + t
                + "\n    "
                + (store.size())
                + " more "
                + plural
                + " to go! \n"
                + LINE);
    }

    public static void delete(String input) {
        try {
            int deleted = Integer.parseInt(input.replace("delete ", "")) - 1;
            Task t = store.get(deleted);

            store.remove(deleted);


            String plural = store.size() == 0 ? "task" : "tasks";

            System.out.println(LINE
                    + "\n    "
                    + "alright, this task is gone: \n"
                    + "      "
                    + t.toString()
                    + "\n    "
                    + store.size()
                    + " more "
                    + plural
                    + " left! \n"
                    + LINE);

        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }
    }
    public static void filterDeadlineToday() {
        LocalDate d = LocalDate.now();
        List<Task> filtered = store.stream()
                .filter(task -> task.getDate() != null)
                .filter(task -> task instanceof Deadline)
                .filter(task -> task.getDate().equals(d))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("no deadline due on " + d);
            return;
        }
        String result = filtered.stream().map(task -> "    " + (store.indexOf(task) + 1) + ". " + task + "\n").collect(Collectors.joining());
        System.out.println(LINE + "\n" + result + LINE);
    }
    public static void filterEventToday() {
        LocalDate d = LocalDate.now();
        List<Task> filtered = store.stream()
                .filter(task -> task.getDate() != null)
                .filter(task -> task instanceof Event)
                .filter(task -> task.getDate().equals(d))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("no deadline due on " + d);
            return;
        }
        String result = filtered.stream().map(task -> "    " + (store.indexOf(task) + 1) + ". " + task + "\n").collect(Collectors.joining());
        System.out.println(LINE + "\n" + result + LINE);
    }
    public static void filterDeadlineDate(String date) {
        LocalDate d = LocalDate.parse(date);
        List<Task> filtered = store.stream()
                .filter(task -> task.getDate() != null)
                .filter(task -> task instanceof Deadline)
                .filter(task -> task.getDate().equals(d))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("no deadline due on " + date);
            return;
        }
        String result = filtered.stream().map(task -> "    " + (store.indexOf(task) + 1) + ". " + task + "\n").collect(Collectors.joining());
        System.out.println(LINE + "\n" + result + LINE);
    }

    public static void filterEventDate(String date) {
        LocalDate d = LocalDate.parse(date);
        List<Task> filtered = store.stream().filter(task -> task.getDate() != null).filter(task -> task.getDate().equals(d)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("no event on " + date);
            return;
        }
        String result = filtered.stream().map(task -> "    " + (store.indexOf(task) + 1) + ". " + task + "\n").collect(Collectors.joining());
        System.out.println(LINE + "\n" + result + LINE);

    }
    public static void main(String[] args) {
        try{
            readFile(filePath);
        } catch (KieraException e) {
            System.out.println(e);
        }


        Scanner in = new Scanner(System.in);
        System.out.println(HELLO);

        while (true) {
            try {
                String input = in.nextLine();
                if (input == null) {
                    continue;
                }
                if (input.equals("bye")) {
                    if (!store.isEmpty()) {
                        String text = store.stream().map(task -> task.toStorageString() + "\n").collect(Collectors.joining());
                        writeToFile(filePath, text);
                    }
                    break;
                }

                if (input.startsWith("mark")) {
                    mark(input);
                    continue;
                } else if (input.startsWith("unmark")) {
                    unmark(input);
                    continue;
                }

                if (input.equals("list")) {
                    list();
                    continue;
                }

                if (input.startsWith("delete")) {
                    delete(input);
                    continue;
                }

                if (input.startsWith("deadline-date")) {
                    String d = input.replace("deadline-date ", "");
                    filterDeadlineDate(d);
                    continue;
                } else if (input.startsWith("event-date")) {
                    String d = input.replace("event-date ", "");
                    filterEventDate(d);
                    continue;
                }

                if (input.startsWith("deadline-today")) {
                    filterDeadlineToday();
                    continue;
                } else if (input.startsWith("event-today")) {
                    filterEventToday();
                    continue;
                }

                if (input.startsWith("todo")) {
                    String desc = input.replace("todo ", "");
                    addTask(desc, "todo");
                } else if (input.startsWith("deadline")) {
                    String desc = input.replace("deadline ", "");
                    addTask(desc, "deadline");
                } else if (input.startsWith("event")) {
                    String desc = input.replace("event ", "");
                    addTask(desc, "event");
                } else {
                    System.out.println(LINE + "\n    sorry, i don't know what this means... \n" + LINE);
                }

            } catch (KieraException e) {
                System.out.println(e);
            }
        }
            System.out.println(BYE);

     
    }
}
