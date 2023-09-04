import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

//    private ArrayList<Task> taskList;
    private LocalDate currentDate;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser = new Parser();

    public Duke(String filePath) {
//        this.taskList = new ArrayList<>();
        this.currentDate = LocalDate.now();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();

            System.out.println("Starting Duke with Empty Task List...");
        }
    }

    private void setcurrentDate(LocalDate date) {
        this.currentDate = date;
    }

    public void runBot() {

        this.ui.displayGreeting();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase(Command.EXIT)) {
            String botOutput = generateResponse(userInput, scanner, this.tasks);

            this.ui.displayMessage(botOutput);
            userInput = scanner.nextLine();
        }

        this.ui.displayExitGreeting();
    }

    public String generateResponse(String userInput, Scanner scanner, TaskList list) {
        String botOutput = "";

        if (userInput.equalsIgnoreCase(Command.LIST)) {
            botOutput = botOutput + "Here are the tasks in your list: \n    " + list.toString();

        } else if (userInput.equalsIgnoreCase(Command.LIST_WITHIN_WEEK)) {
            TaskList listWeek = list.dueWithinWeek();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one week: \n    " + listWeek.toString();

        } else if (userInput.equalsIgnoreCase(Command.LIST_WITHIN_MONTH)) {
            TaskList monthWeek = list.dueWithinMonth();
            botOutput = botOutput + "Here are the tasks in your list that start/due within one month: \n    " + monthWeek.toString();

        } else if (userInput.startsWith(Command.MARK)) {
            botOutput = botOutput + "Nice! I've marked this task as done: \n    ";
            try {
                int taskNo = parser.parseMark(userInput, list);
                Task x = list.getTask(taskNo - 1);
                x.markAsDone();
                botOutput = botOutput + x;
            } catch (ParserException p) {
                botOutput = p.getMessage();
            }
        } else if (userInput.startsWith(Command.UNMARK)) {
            botOutput = botOutput + "Ok, I've marked this task as not done yet: \n    ";
            try {
                int taskNo = parser.parseUnmark(userInput, list);
                Task x = list.getTask(taskNo - 1);
                x.markAsUndone();
                botOutput = botOutput + x;
            } catch (ParserException p) {
                botOutput = p.getMessage();
            }
        } else if (userInput.startsWith(Command.DELETE)) {
            botOutput = botOutput + "Noted. I've removed this task: \n    ";
            try {
                int taskNo = parser.parseDelete(userInput, list);
                Task x = list.deleteTask(taskNo - 1);
                botOutput = botOutput + x;
            } catch (ParserException p) {
                botOutput = p.getMessage();
            }
        } else {

            try {
                Task t = Task.taskCon(userInput);
                list.addTask(t);
                botOutput = botOutput + "added: " + t + "\n    Now you have " + list.getSize() + " tasks in the list.";
            } catch (InvalidCommandException e) {
                botOutput = "OOPS!!! I'm sorry, but I'm afraid I don't comprehend Sergeant!";
            } catch (InvalidTaskCreationException t) {
                botOutput = t.getMessage();
            } catch (DateTimeParseException d) {
                botOutput = "Please specify deadlines and dates in the following format, " + Task.dateTimeFormat;
            }

        }

        return botOutput;

    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke dukeInstance = new Duke(filePath);
        dukeInstance.runBot();
    }
}
