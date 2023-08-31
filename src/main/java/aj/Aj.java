package aj;

import java.util.Scanner;
import java.io.IOException;


public class Aj {
    Parser parser;
    Storage storage;

    TaskList taskList;

    Ui ui;


    public boolean askCommand(Scanner scanner) throws NoSuchCommandException, EmptyDescriptionException, IndexOutOfRangeException, IOException {

        String command = scanner.next().toLowerCase();


        if (command.equals("list")) {
            this.ui.printList();
        } else if (command.equals("bye")) {
            this.ui.exit();
            return true;
        } else {
            String remaining = scanner.nextLine();
            if (command.equals("mark")) {
                this.ui.checkMessage(command, remaining);
//            String back = scanner.next();
                int idx = Integer.parseInt(remaining.substring(1)) - 1; // this idx is idx of tasklst
                this.ui.checkIndex(idx);
                Task task = this.taskList.getTask(idx);
                this.ui.horiLine();
                if (task.isCompleted()) {
                    System.out.println("You have already marked it!!!");
                } else {
                    task.toggleComplete();
                    this.storage.updateData(idx, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                }
            } else if (command.equals("unmark")) {
                this.ui.checkMessage(command, remaining);
//            String back = scanner.next();
                int idx = Integer.parseInt(remaining.substring(1)) - 1;
                this.ui.checkIndex(idx);
//      aj.Task task = this.lst[idx];
                Task task = this.taskList.getTask(idx);
                this.ui.horiLine();
                if (!task.isCompleted()) {
                    System.out.println("aj.Task is already unmarked!!!");
                } else {
                    task.toggleComplete();
                    this.storage.updateData(idx, false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                }
            } else if (command.equals("delete")) {
//            String back = scanner.next();
                this.ui.checkMessage(command, remaining);
                int idx = Integer.parseInt(remaining.substring(1)) - 1;
                this.ui.checkIndex(idx);
                this.ui.horiLine();
                System.out.println("Noted. I've removed this task:");
                Task removedTask = this.taskList.getTask(idx);
                this.taskList.removeTask(idx);
                this.storage.deleteData(idx);
                System.out.println(removedTask);
                this.ui.printNoTask();
            } else { // if its none of the main commands, then its a task. do logic for parsing here or thr

//            String remaining = scanner.nextLine();
                this.ui.horiLine();

                Task task = null;
                if (command.equals("todo")) {
                    this.ui.checkMessage(command, remaining);
                    task = this.parser.getTodoTask(remaining, false);
                } else if (command.equals("deadline")) {
                    this.ui.checkMessage(command, remaining);
                    task = this.parser.getDeadlineTask(remaining, false);
                } else if (command.equals("event")) {
                    this.ui.checkMessage(command, remaining);
                    task = this.parser.getEventTask(remaining, false);
                } else {
//        System.out.println("No such command!!! Try again!");
                    throw new NoSuchCommandException();
                }
                if (task != null) { // have a task
                    this.storage.addData(command + remaining + ",false");
                    this.taskList.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    this.ui.printNoTask();
                }
            }
        }
        this.ui.horiLine();
        return false;
    }


    public void setUpBot(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(this.parser, filePath);
        this.taskList = new TaskList(this.storage.initialiseData());
        this.ui = new Ui(taskList);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.ui.greet();
        while (true) {
            try {
                if (askCommand(scanner)) {
                    break;
                }
            } catch (AjException | IOException e) {
                System.out.println(e.getMessage());
                this.ui.horiLine();
            }
        }
    }

    public static void main(String[] args) {
        String fullFilePath = "/Users/aaronjt/Documents/CS2103_IP/ip/src/main/data/actualData.txt";
        String filePath = System.getProperty("user.dir") + "/src/main/data/actualData.txt";


        /*
        Since test script run in different directory, change setUpBot() below accordingly
         */
        String testingFilePath = System.getProperty("user.dir") + "/../src/main/data/testData.txt";

        Aj bot = new Aj(); // create bot instance
        bot.setUpBot(fullFilePath); // setup bot with necessary instances
        bot.run(); // run bot
    }
}
