import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private Ui ui;

    private Storage storage;

    private TaskList tasks;
    private File savedList;

    public Duke(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
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
                ui.say("uhhh wat?");
                continue;
            }

            if (command.contains("bye")) {
                break;
            } else if (command.equals("list")) {
                ui.say(tasks.list());
                continue;
            } else if (command.equals("mark") || command.equals("unmark")) {
                try {
                    ui.say(tasks.changeMark(command, tokeniser));
                } catch (IllegalCommandException e) {
                    ui.say(e.getMessage());
                }
                continue;
            } else if (command.equals("delete")) {
                try {
                    ui.say(tasks.deleteTask(tokeniser));
                } catch (IllegalCommandException e) {
                    ui.say(e.getMessage());
                }
                continue;
            }
            try {
                Task newTask = Task.generateTask(command, tokeniser);
                tasks.add(newTask);
                ui.say("Gotchu! noted down: \n" +
                        Ui.indentLineBy(newTask.toString(), 2) +
                        "Now you have " +
                        tasks.getNumberOfTask() +
                        " tasks in the list!");
            } catch (IllegalCommandException e) {
                ui.say(e.getMessage());
            } catch (IllegalDateTimeException e) {
                ui.say(e.getMessage());
            }
        }
    }

    public void run() throws IllegalCommandException {
        try {
            storage.readFile(tasks); // reads loaded file
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            storage.clearFile();    // file is corrupt
            tasks.clearAll();
            ui.say("saved file is corrupt, creating new file...");
        }
        ui.greet(tasks);            // pass tasks in to see if there saved task
        this.processInput();        // function to run the chatbot
        try {
            storage.writeFile(tasks);   // write file with all tasks
        } catch (IOException e) {
            ui.say(e.getMessage());
        }
        ui.sayBye();
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
            Duke duke = new Duke("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt");
            duke.run();
        }
    }