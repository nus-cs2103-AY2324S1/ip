import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;

    public Duke() {
        this.taskList = new ArrayList<>();
    }
    public static String botMessage(String message) {
        String space = "    ";
        String horizontalBar = "-------------------------------------------------";
        return space + horizontalBar + "\n" + space + message + "\n" + space + horizontalBar;
    }

    public static final String greeting = "Hello, I'm Capt. Price! What can I do for you today, Sergeant?";

    public static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String horizontalBar = "-------------------------------------------------";
    public static final String exitGreeting = "Over and out. See you next mission!";

    public void loadTasksFromFile(String filePath) throws IOException, InvalidFileFormatException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            switch (line.charAt(0)) {
                case 'T':
                    String[] splitStringList = line.split("\\|");
                    if (splitStringList.length != 3) {
                        throw new InvalidFileFormatException("Invalid format for Todo task in the file.");
                    }
                    String fabricatedUserInput = "todo " + splitStringList[2];

                    Task t;
                    try {
                        t = Task.taskCon(fabricatedUserInput);
                    } catch (InvalidCommandException | InvalidTaskCreationException e) {
                        throw new InvalidFileFormatException("Invalid format for Todo task in the file.");
                    }
                    
                    if (splitStringList[1].equals("1")) {
                        t.markAsDone();
                    }
                    this.taskList.add(t);
                    break;

                case 'D':
                    splitStringList = line.split("\\|");
                    if (splitStringList.length != 4) {
                        throw new InvalidFileFormatException("Invalid format for Deadline task in the file.");
                    }
                    fabricatedUserInput = "deadline " + splitStringList[2] + "/by " + splitStringList[3];

                    Task d;
                    try {
                        d = Task.taskCon(fabricatedUserInput);
                    } catch (InvalidCommandException | InvalidTaskCreationException e) {
                        throw new InvalidFileFormatException("Invalid format for Deadline task in the file.");
                    }

                    if (splitStringList[1].equals("1")) {
                        d.markAsDone();
                    }
                    this.taskList.add(d);
                    break;

                case 'E':
                    splitStringList = line.split("\\|");
                    if (splitStringList.length != 5) {
                        throw new InvalidFileFormatException("Invalid format for Event task in the file.");
                    }
                    fabricatedUserInput = "event " + splitStringList[2] + "/from " + splitStringList[3] + "/to " + splitStringList[4];

                    Task ev;
                    try {
                        ev = Task.taskCon(fabricatedUserInput);
                    } catch (InvalidCommandException | InvalidTaskCreationException e) {
                        throw new InvalidFileFormatException("Invalid format for Event task in the file.");
                    }

                    if (splitStringList[1].equals("1")) {
                        ev.markAsDone();
                    }
                    this.taskList.add(ev);
                    break;

                default:
                    // Handle invalid lines or raise an exception if needed.
                    break;
            }
        }
    }

    public void runBot() {

        System.out.println(botMessage(Duke.greeting));
//        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            String botOutput = generateResponse(userInput, scanner, this.taskList);

            System.out.println(botMessage(botOutput));
            userInput = scanner.nextLine();
        }

        System.out.println(botMessage(Duke.exitGreeting));
    }

    public String generateResponse(String userInput, Scanner scanner, ArrayList<Task> list) {
        String botOutput = "";

        if (userInput.equalsIgnoreCase("list")) {
            botOutput = botOutput + "Here are the tasks in your list: \n    ";
            for (int i = 1; i <= list.size(); i++) {
                botOutput = botOutput + i + "." + " " + list.get(i-1) + "\n    ";
            }
        } else if (userInput.startsWith("mark ")) {
            botOutput = botOutput + "Nice! I've marked this task as done: \n    ";

            try {

                int taskNo = Integer.parseInt(userInput.substring(5));
                if (taskNo > list.size() | taskNo < 1) {
                    throw new InvalidTaskNumberException("Please enter valid Task No. to mark!");
                }
                Task x = list.get(taskNo - 1);
                x.markAsDone();
                botOutput = botOutput + x;

            } catch (NumberFormatException e) {

                botOutput = "Invalid Input String!!";

            } catch (InvalidTaskNumberException d) {

                botOutput = d.getMessage();

            }

        } else if (userInput.startsWith("unmark ")) {
            botOutput = botOutput + "Ok, I've marked this task as not done yet: \n    ";

            try {

                int taskNo = Integer.parseInt(userInput.substring(7));
                if (taskNo > list.size() | taskNo < 1) {
                    throw new InvalidTaskNumberException("Please enter valid Task No. to unmark!");
                }
                Task x = list.get(taskNo - 1);
                x.markAsUndone();
                botOutput = botOutput + x;

            } catch (NumberFormatException e) {

                botOutput = "Invalid Input String!!";

            } catch (InvalidTaskNumberException d) {
                botOutput = d.getMessage();
            }

        } else if (userInput.startsWith("delete ")) {
            botOutput = botOutput + "Noted. I've removed this task: \n    ";

            try {

                int taskNo = Integer.parseInt(userInput.substring(7));
                if (taskNo > list.size() | taskNo < 1) {
                    throw new InvalidTaskNumberException("Please enter valid Task No. to delete!");
                }
                Task x = list.get(taskNo - 1);
                list.remove(x);
                botOutput = botOutput + x;

            } catch (NumberFormatException e) {

                botOutput = "Invalid Input String!!";

            } catch (InvalidTaskNumberException d) {
                botOutput = d.getMessage();
            }
        } else {

            try {
                Task t = Task.taskCon(userInput);
                list.add(t);
                botOutput = botOutput + "added: " + t + "\n    Now you have " + list.size() + " tasks in the list.";
            } catch (InvalidCommandException e) {
                botOutput = "OOPS!!! I'm sorry, but I'm afraid I don't comprehend Sergeant!";
            } catch (InvalidTaskCreationException t) {
                botOutput = t.getMessage();
            }

        }

        return botOutput;

    }

    public static void main(String[] args) {
        Duke dukeInstance = new Duke();

        try {
            String filePath = "./data/duke.txt";
            dukeInstance.loadTasksFromFile(filePath);
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
            dukeInstance.taskList = new ArrayList<>();
            System.out.println("Starting Duke with Empty Task List...");
        }
        
        dukeInstance.runBot();
    }
}
