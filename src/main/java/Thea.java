import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;

public class Thea {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks =  retrieveTasks();
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
                        ToDo todo = new ToDo(commandWords[1]);
                        add(todo, tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of a todo cannot be empty! '^'");
                    }
                } else if (command.equals("deadline")) {
                    if (commandWordsArray.size() != 1) {
                        String relevantData = commandWords[1];
                        String[] nameAndTime = relevantData.split(" /by ");
                        Deadline deadline = new Deadline(nameAndTime[0], nameAndTime[1]);
                        add(deadline, tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of a deadline cannot be empty! '^'");
                    }
                } else if (command.equals("event")) {
                    if (commandWordsArray.size() != 1) {
                        String relevantData = commandWords[1];
                        String[] nameAndTime = relevantData.split(" /from | /to ");
                        Event event = new Event(nameAndTime[0], nameAndTime[1], nameAndTime[2]);
                        add(event, tasks);
                    } else {
                        throw new EmptyDescriptionException("The description of an event cannot be empty! '^'");
                    }
                } else {
                    throw new WrongCommandException("Sorry, I don't understand what that means.. '^'");
                }
            }
            saveTaskList(tasks);
            userInput = input.nextLine();
        }
    }

    public static void saveTaskList(ArrayList<Task> tasks) {
        String currentDir = System.getProperty("user.dir");
        Path dataDirPath =Paths.get(currentDir, "data");
        Path path = Paths.get(currentDir, "data", "thea.txt");
        if(!Files.exists(dataDirPath)) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try (BufferedWriter bufferWriter = Files.newBufferedWriter(path)) {
            for (Task task: tasks) {
                bufferWriter.write(task.toMemoryFormat());
                bufferWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Task> retrieveTasks() throws Exception{
        String currentDir = System.getProperty("user.dir");
        Path dataDirPath =Paths.get(currentDir, "data");
        Path path = Paths.get(currentDir, "data", "thea.txt");
        if(!Files.exists(dataDirPath)) {
            return new ArrayList<>();
        }
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        String line;
        ArrayList<Task> retrievedTasks = new ArrayList<>();
        Task task;
        try (BufferedReader bufferReader = Files.newBufferedReader(path)) {
            line = bufferReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(" [|] ");
                if (splitLine[0].equals("T")) {
                    task = new ToDo(splitLine[2]);
                } else if (splitLine[0].equals("D")) {
                    task = new Deadline(splitLine[2], splitLine[3]);
                } else if (splitLine[0].equals("E")) {
                    task = new Event(splitLine[2],splitLine[3], splitLine[4]);
                } else {
                    throw new FileCorruptedException("Unexpected File Format Found. File might be corrupted.");
                }
                if (splitLine[1].equals("1")) {
                    task.markAsDone();
                }
                retrievedTasks.add(task);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retrievedTasks;
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
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("Yay! You have no tasks in your list.");
        }
    }
}
