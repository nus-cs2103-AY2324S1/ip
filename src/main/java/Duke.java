import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exception.MissingTaskException;
import Exception.MissingCommandException;
import Exception.MissingTextException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();
    static final ObjectMapper MAPPER = new ObjectMapper();

    final static String horizontalLine = "   -----------------------------\n";
    public static void main(String[] args) {
        try {
            tasks = MAPPER.readValue(new File("tasks.json"), new TypeReference<>() {});
        } catch (FileNotFoundException e) {
            System.out.println("File not found, generating file...");
        } catch (IOException e) {
            System.out.println("Uh oh, file is corrupted, starting afresh...");
        }


        printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            System.out.println("\n" + horizontalLine);
            String[] splitText = text.split(" ");
            String[] splitText2 = text.split(" ", 2);
            String command = splitText[0].toLowerCase();

            switch (command) {
                case "bye":
                    System.out.println("     BYE!\n" + horizontalLine);
                    System.exit(0);
                case "list":
                    printList();
                    break;
                case "delete":
                    if (splitText2.length == 2) {
                        int taskNumber = getTaskNumber(splitText2[1]);
                        if (taskNumber != -1) {
                            tasks.remove(taskNumber - 1);
                            System.out.println("Task deleted");
                            printList();
                        }
                    } else {
                        System.out.println("     You need to type in something u silly dog.");
                    }
                    break;
                case "todo": {
                    if (splitText2.length == 2) {
                        tasks.add(new ToDo(splitText2[1]));
                        printTasksLength();
                    } else {
                        System.out.println("     You need to type in a task u silly dog.");
                    }

                    break;
                }
                case "deadline": {
                    try {
                        String by = getUsingCommand(splitText, "by");
                        String task = getTask(splitText);
                        tasks.add(new Deadline(task, by));
                        printTasksLength();
                    } catch (MissingTaskException e) {
                        System.err.println("Missing task error");
                    } catch (MissingTextException e) {
                        System.err.println("Missing text after command error, check that you have inputted a text after command.");
                    } catch (MissingCommandException e) {
                        System.err.println("Missing command error, check that you included the following commands \n"
                                + "/by [insert deadline]");
                    }
                    break;
                }
                case "event": {
                    try {
                        String from = getUsingCommand(splitText, "from");
                        String to = getUsingCommand(splitText, "to");
                        String task = getTask(splitText);
                        tasks.add(new Event(task, from, to));
                        printTasksLength();
                    } catch (MissingTaskException e) {
                        System.err.println("Missing task error, check that you have added a task.");
                    } catch (MissingTextException e) {
                        System.err.println("Missing text after command error, check that you have inputted a text after command.");
                    } catch (MissingCommandException e) {
                        System.err.println("Missing command error, check that you included the following commands \n"
                                + "/from [insert from date], /to [insert to date]");
                    }
                    break;
                }
                case "mark":
                case "unmark": {
                    if (splitText2.length == 2) {
                        int taskNumber = getTaskNumber(splitText2[1]);
                        if (taskNumber != -1) {
                            if (command.equals("mark")){
                                tasks.get(taskNumber - 1).mark();
                                System.out.println("     You've marked task: " + taskNumber);
                            }
                            else {
                                tasks.get(taskNumber - 1).unmark();
                                System.out.println("     You've marked task: " + taskNumber);
                            }
                            printList();
                        }
                    } else {
                        System.out.println("     You need to type in something u silly dog.");
                    }
                    break;
                }
                default:
                    System.out.println("     I can only understand the following commands: \n"
                            + "     bye, list, mark, unmark, todo, deadline, event");
                    break;
            }
            System.out.println("\n" + horizontalLine);

            updateAndSaveFile();
        }
    }

    private static void printGreetings() {
        System.out.println(horizontalLine
                + "     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n"
                + horizontalLine);
    }
    private static void printList() {
        if (tasks.isEmpty()) {
            System.out.println("     You have no tasks added yet");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("     " + i + ". " + tasks.get(i - 1));
            }
        }
    }

    private static String getUsingCommand(String[] splitText, String command) throws MissingCommandException, MissingTextException {
        boolean found = false;
        StringBuilder text = new StringBuilder();
        for (String s : splitText) {
            if (!found) {
                if (s.toLowerCase().equals("/" + command)) {
                    found = true;
                }
            } else {
                if (s.charAt(0) == '/')
                    break;
                text.append(" ").append(s);
            }
        }

       if (found && (text.length() > 0)) {
           return text.substring(1);
       } else if (found)
           throw new MissingTextException();
       else {
           throw new MissingCommandException();
       }
    }

    private static String getTask(String[] splitText) throws MissingTaskException {
        String task = "";
        if (splitText.length > 1) {
            for (int i = 1; i < splitText.length; i++) {
                if (splitText[i].charAt(0) != '/')
                    task = task + " " + splitText[i];
                else
                    break;
            }
            return task.substring(1);
        }
        throw new MissingTaskException();
    }

    private static void printTasksLength() {
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static int getTaskNumber(String string) {
        try {
            int taskNumber = Integer.parseInt(string);
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                return taskNumber;
            } else {
                System.out.println("Enter a number that is within the list: ");
                printList();
                return -1;
            }
        } catch (NumberFormatException ne) {
            System.out.println("You need to enter a number");
            return -1;
        }
    }



    private static void updateAndSaveFile() {
        try {
            String jsonString = MAPPER.writerFor(new TypeReference<ArrayList<Task>>() {}).writeValueAsString(tasks);
            Files.writeString(Path.of("tasks.json"), jsonString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
