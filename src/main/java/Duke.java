import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;  // Import the Scanner class
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    static String logo = "                  .-\"-.\n"
            + "                 /|6 6|\\\n"
            + " _  ._ _   _    {/(_0_)\\}\n"
            + "(_) | (/_ (_)    _/ ^ \\_\n"
            + "                (/ /^\\ \\)-'\n"
            + "                 \"\"' '\"\"\n";

    static String greet = logo + "Woof! I'm Oreo! How may I help you?\n";
    static String exit = "I will be sad to see you go! bye!\n";
    private ArrayList<Task> taskList;
    private File savedList;

    public Duke() {
        this.taskList = new ArrayList<>();
    }

    private void list() {
        if (Task.numberOfTasks == 0) {
            System.out.println(TextFormat.botReply("list looks empty to me!"));
        } else {
            StringBuilder displayList = new StringBuilder();
            if (Task.numberOfTasks == Task.numberOfCompletedTasks) {
                displayList.append("Wow! You are ALL COMPLETE!!!!\n")
                        .append("TIME TO PLAY WITH MEEEEE :DDDD\n");
            } else if (Task.numberOfTasks > 10) {
                displayList.append("Seems like you have a lot of things to do...\n")
                        .append("Remember to play with me after :D\n");
            } else {
                displayList.append("Here are the things you told me to keep track of:\n");
            }
            for (int i = 0; i < Task.numberOfTasks; i++) {
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            System.out.println(TextFormat.botReply(displayList.toString()));
        }
    }

    private void changeMark(String command, Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > Task.numberOfTasks) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                if (command.equals("mark")) {
                    taskList.get(id - 1).markDone();
                    if (Task.numberOfTasks == Task.numberOfCompletedTasks) {
                        this.list();
                    }
                } else {
                    taskList.get(id - 1).markNotDone();}
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    private void deleteTask(Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > Task.numberOfTasks) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                Task.deleteTask(taskList.get(id - 1));
                taskList.remove(id - 1);
                if (Task.numberOfTasks == Task.numberOfCompletedTasks) {
                    this.list();
                }
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    public void processInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = "";
            String input = sc.nextLine();
            Scanner tokeniser = new Scanner(input);
            try {
                command = tokeniser.next();
            } catch (NoSuchElementException e) {
                System.out.println(TextFormat.botReply("uhhh wat?"));
                continue;
            }

            if (command.contains("bye")) {
                break;
            } else if (command.equals("list")) {
                this.list();
                continue;
            } else if (command.equals("mark") || command.equals("unmark")) {
                try {
                    changeMark(command, tokeniser);
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            } else if (command.equals("delete")) {
                try {
                    deleteTask(tokeniser);
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            try {
                Task newTask = Task.addTask(command, tokeniser);
                taskList.add(newTask);
            } catch (IllegalCommandException e) {
                System.out.println(e.getMessage());
            } catch (IllegalDateTimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadFile(){
        savedList = new File("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt");
        try {
            savedList.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(this.savedList);
        while (sc.hasNext()) {
            int id = sc.nextInt();
            int mark = sc.nextInt();
            String description = sc.nextLine();
            Task newTask;
            try {
                newTask = Task.addSavedTask(id, mark == 1, description);
            } catch (IllegalDateTimeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            taskList.add(newTask);
        }
    }

    public void writeFile() throws IOException {
        new FileWriter("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt", false)
                .close();
        FileWriter fw = new FileWriter("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt",
                true);
        for (int i = 0; i < Task.numberOfTasks; i++) {
            String data = taskList.get(i).writeToFile();
            fw.write(data);
        }
        fw.close();
    }

    public void run() throws IllegalCommandException {
        loadFile();     // loads file
        try {
            readFile(); // reads loaded file
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (Task.numberOfTasks != 0) {
            // if there are saved task
            greet = greet + "Welcome back! You had these tasks last time!\n";
            System.out.println(TextFormat.botReply(greet)); // print greet message
            list();
        } else {
            System.out.println(TextFormat.botReply(greet)); // print greet message
        }
        this.processInput();                            // function to run the chatbot
        try {
            this.writeFile();                           // write file with all tasks
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(TextFormat.botReply(exit));  // exit message
    }

        public static boolean isInteger(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (str.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            for (; i < length; i++) {
                char c = str.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            Duke duke = new Duke();
            duke.run();
        }
    }