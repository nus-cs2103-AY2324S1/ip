package Jarvis;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Jarvis {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;
    private static String fileName;

    public Jarvis(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        Ui.printGreeting();
        // getting the file path to the save file
        String home = System.getProperty("user.home");
        Path pathToSaveFile = Paths.get(home, "Desktop", "CS2103T", "IP", "data", fileName);
        boolean isFileExists = Files.exists(pathToSaveFile);

        storage = new Storage(pathToSaveFile.toString());
        tasks = new TaskList(storage.load());
        parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String command = scanner.nextLine().trim();
                Parser.parseCommand(storage, tasks, command);
            } catch (IncorrectJarvisCommandException e) {
                break;
            } catch (InvalidTaskNumberException e) {
                break;
            } catch (WrongJarvisCommandFormatException e) {
                break;
            }
            storage.deleteContents();
            storage.save(tasks); // save task list to data file after every iteration
        }
        scanner.close(); // closing the user input scanner
    }

    public static void main(String[] args) {
        new Jarvis("data.txt").run();
    }
}