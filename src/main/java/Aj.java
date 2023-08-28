import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Aj {
/*
First take a command, parse to parser, create the necessary task objects, add the task etc
Since each task have different flags, we parse to that task object to parse the remaining themselves
 */


    List<Task> lst = new ArrayList<>();

    File DATAFILE;

    public void initialiseData() { // get data from file, create necessary task objects and add to lst
        String localDir = System.getProperty("user.dir");
        File file = new File(localDir + "/src/main/data/data.txt");
        DATAFILE = file;
        System.out.println("Loading data....\nHere are your saved data:\n");

        if (!DATAFILE.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedValues = line.split(",");
                Scanner strScanner = new Scanner(parsedValues[0]); // original command
                String command = strScanner.next().toLowerCase();
                String remaining = strScanner.nextLine();
                boolean isMark = Boolean.parseBoolean(parsedValues[1]);
                Task task;
                if (command.equals("todo")) {
                    task = new Todo(remaining.substring(1), isMark);
                } else if (command.equals("deadline")) {
                    task = getDeadlineTask(remaining, isMark);
                } else if (command.equals("event")) {
                    task = getEventTask(remaining, isMark);
                } else {
                    task = null;
                }
                this.lst.add(task);
            }
            printList();
            System.out.println("\n\nWhat can I do for you!!");
            horiLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateData(int idx, boolean isMarked) throws IOException { // linenumber refers to index from 0

        String localDir = System.getProperty("user.dir");
        Path myPath = Paths.get(localDir + "/src/main/data/data.txt");

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                String[] parsedValues = fileContent.get(i).split(",");
                String newLineContent = parsedValues[0] + "," + Boolean.toString(isMarked);
                fileContent.set(i, newLineContent);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    public void deleteData(int idx) throws IOException { // get linenumber and delete that entry
        String localDir = System.getProperty("user.dir");
        Path myPath = Paths.get(localDir + "/src/main/data/data.txt");

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                fileContent.remove(i);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    public void addData(String str) throws IOException { // get linenumber and delete that entry
        String localDir = System.getProperty("user.dir");
        Path myPath = Paths.get(localDir + "/src/main/data/data.txt");

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));
        fileContent.add(str);
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    public void horiLine() {
        System.out.println("---------------------");
    }

    public void greet() {
        horiLine();
        System.out.println("Hello! I'm Aj");
        horiLine();
    }

    public Task getDeadlineTask(String remaining, boolean isMark) { // takes in command, parse it and return task object
        String pattern = " (.*) /by (.*)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(remaining);

        if (matcher.matches()) {
            String taskName = matcher.group(1);
            String by = matcher.group(2);
            return new Deadline(taskName, isMark, by);
        }
        return null;
    }

    public Task getEventTask(String remaining, boolean isMark) { // takes in command, parse it and return task object
        String pattern = " (.*) /from (.*?) /to (.*)";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(remaining);

        if (matcher.matches()) {
            String taskName = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);
            return new Event(taskName, isMark, startTime, endTime);
        }
        return null;
    }

    public void checkIndex(int val) throws IndexOutOfRangeException { // throws error if index invalid
        if (val <= this.lst.size() - 1 && val >= 0) {
            return;
        } else {
            throw new IndexOutOfRangeException(this.lst.size());
        }
    }

    public void printNoTask() {
        System.out.printf("Now you have %d tasks in the list.\n", this.lst.size());
    }

    public void printList() {
        if (this.lst.size() == 0) {
            System.out.println("No items yet, add something!!!");
        }
        for (int i = 1; i <= this.lst.size(); i++) {
//        System.out.println(i + "." + this.lst[i - 1]);
            System.out.println(i + "." + this.lst.get(i - 1));
        }
    }

    public boolean askCommand(Scanner scanner) throws NoSuchCommandException, EmptyDescriptionException, IndexOutOfRangeException, IOException {
        // returns true if 'bye' is called
//    System.out.println("Calling");
        String command = scanner.next().toLowerCase();
//        String front = command.split(" ")[0];
//        String back = command.split(" ")[1];
        if (command.equals("mark")) {
            String back = scanner.next();
            int idx = Integer.parseInt(back) - 1; // this idx is idx of tasklst
            checkIndex(idx);
            Task task = this.lst.get(idx);
            horiLine();
            if (task.isCompleted()) {
                System.out.println("You have already marked it!!!");
            } else {
                task.toggleComplete();
                updateData(idx, true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            }
        } else if (command.equals("unmark")) {
            String back = scanner.next();
            int idx = Integer.parseInt(back) - 1;
            checkIndex(idx);
//      Task task = this.lst[idx];
            Task task = this.lst.get(idx);
            horiLine();
            if (!task.isCompleted()) {
                System.out.println("Task is already unmarked!!!");
            } else {
                task.toggleComplete();
                updateData(idx, false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            }
        } else if (command.equals("list")) {
            printList();
        } else if (command.equals("delete")) {
            String back = scanner.next();
            int idx = Integer.parseInt(back) - 1;
            checkIndex(idx);
            horiLine();
            System.out.println("Noted. I've removed this task:");
            Task removedTask = this.lst.get(idx);
            this.lst.remove(idx);
            deleteData(idx);
            System.out.println(removedTask);
            printNoTask();
        } else if (command.equals("bye")) {
            exit();
            return true;
        } else { // if its none of the main commands, then its a task. do logic for parsing here or thr

            String remaining = scanner.nextLine();
            horiLine();

            Task task = null;
            if (command.equals("todo")) {
                checkMessage(command, remaining);

                task = new Todo(remaining.substring(1), false);
            } else if (command.equals("deadline")) {
                checkMessage(command, remaining);
                task = getDeadlineTask(remaining, false);
            } else if (command.equals("event")) {
                checkMessage(command, remaining);
                task = getEventTask(remaining, false);
            } else {
//        System.out.println("No such command!!! Try again!");
                throw new NoSuchCommandException();
            }
            if (task != null) { // have a task
                addData(command + remaining + ",false");
                this.lst.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                printNoTask();
            }
        }
        horiLine();
        return false;
    }

    public void checkMessage(String cmd, String msg) throws EmptyDescriptionException { // if no arguments, give help message
        String helpMessage;
        if (msg.length() == 0) {
            if (cmd.equals("todo")) {
                helpMessage = "todo <task name>";
            } else if (cmd.equals("deadline")) {
                helpMessage = "deadline <task name> /by <date/time>";
            } else if (cmd.equals("event")) {
                helpMessage = "event <task name> /from <date/time> /to <date/time>";
            } else {
                helpMessage = "";
            }
            throw new EmptyDescriptionException(cmd, helpMessage);
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horiLine();
    }

    public static void main(String[] args) {
        Aj bot = new Aj();
        bot.greet();
        Scanner scanner = new Scanner(System.in);
        bot.initialiseData();
//        try {
//            bot.updateData(1, false);
//            bot.updateData(2, false);
//            bot.updateData(3, true);
//            bot.updateData(4, true);
//        } catch (Exception e) {
//            System.out.println("haiz");
//        }

        while (true) {
            try {
                if (bot.askCommand(scanner)) {
                    break;
                }
            } catch (AjException | IOException e) {
                System.out.println(e.getMessage());
                bot.horiLine();
            }
        }
    }
}
