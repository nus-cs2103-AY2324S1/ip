
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/tasks.txt").run();
    }
}

/*
public class Duke {
    private static final ArrayList<Task> lst = new ArrayList<>();

    private static File f;

    public static void main(String[] args) {

        accessFile();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            checkInput(input);
            input = scanner.nextLine();
        }

        saveToFile();

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
            String date = words[1].substring(3);
            LocalDate by = LocalDate.parse(date);
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
            String fromDate = words[1].substring(5, 15);
            String toDate = words[2].substring(3);
            LocalDate from = LocalDate.parse(fromDate);
            LocalDate to = LocalDate.parse(toDate);
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
                    Deadline dl = new Deadline(arr[2], LocalDate.parse(arr[3]));
                    if (arr[1].equals("1")) {
                        dl.markAsDone();
                    }
                    lst.add(dl);
                } else if (arr[0].equals("E")){
                    Event e = new Event(arr[2], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
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
*/