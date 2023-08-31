import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class Thea {

    public static void main(String[] args) throws Exception {
        readfile();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks =  new ArrayList<>();
        greet();
        String userInput = input.nextLine();
        while (true) {
            String[] commandWords = userInput.split(" ", 2);
            ArrayList<String> commandWordsArray = new ArrayList<>();
            Collections.addAll(commandWordsArray, commandWords);
            String command = commandWords[0];
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                printList(tasks);
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                if (index > tasks.size() - 1) {
                    throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
                } else {
                    tasks.get(index).markAsDone();
                    System.out.printf("Great job! ˊᗜˋ I've marked this task as done:\n  %s\n", tasks.get(index));
                }
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                if (index > tasks.size() - 1) {
                    throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
                } else {
                    tasks.get(index).unmarkAsDone();
                    System.out.printf("Okay, I've marked this task as not done yet:\n  %s\n", tasks.get(index));
                }
            } else if (command.equals("delete")){
                int index = Integer.parseInt(commandWords[1]) - 1;
                if (index > tasks.size() - 1) {
                    throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
                } else {
                    System.out.println("I have removed the following task to your list:\n  "
                            + tasks.get(index).toString() + "\nNow you have " + (tasks.size() - 1)
                            + ((tasks.size() - 1) == 1 ? " task" : " tasks")
                            + " in the list.");
                    tasks.remove(index);
                }
            }
            else {
                if (command.equals("todo")) {
                    if (commandWordsArray.size() != 1) {
                        add(new ToDo(commandWords[1]), tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of a todo cannot be empty! '^'");
                    }
                } else if (command.equals("deadline")) {
                    if (commandWordsArray.size() != 1) {
                        String relevantData = commandWords[1];
                        String[] nameAndTime = relevantData.split(" /by ");
                        add(new Deadline(nameAndTime[0], nameAndTime[1]), tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of a deadline cannot be empty! '^'");
                    }
                } else if (command.equals("event")) {
                    if (commandWordsArray.size() != 1) {
                        String relevantData = commandWords[1];
                        String[] nameAndTime = relevantData.split(" /from | /to ");
                        add(new Event(nameAndTime[0], nameAndTime[1], nameAndTime[2]), tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of an event cannot be empty! '^'");
                    }
                } else {
                    throw new WrongCommandException("Sorry, I don't understand what that means.. '^'");
                }
            }
            userInput = input.nextLine();
        }
    }

    public static void readfile() {
        String currentDir = System.getProperty("user.dir");
        Path path = Paths.get(currentDir, "data", "thea.txt");
        String line;
        try (BufferedReader bufferReader = Files.newBufferedReader(path)) {
            line = bufferReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?");
    }
    public static void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }
    public static void add(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        System.out.println("I have added the following task to your list:\n  "
                + task.toString() + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list. You can do this!");
    }
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }
}
