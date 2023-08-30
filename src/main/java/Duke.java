
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        java.nio.file.Path path = java.nio.file.Paths.get( "./","src", "main", "data", "duke.txt");
        boolean fileExists = java.nio.file.Files.exists(path);
        File f = new File(String.valueOf(path));
        if (!fileExists) {
            try {
                boolean fileCreated = f.createNewFile();
                if (fileCreated) {
                    System.out.println("File created: " + path);
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        final String EXIT_PHRASE = "bye";
        final String LIST_PHRASE = "list";
        final String TODO_PHRASE = "todo";
        final String DEADLINE_PHRASE = "deadline";
        final String EVENT_PHRASE = "event";
        final String DELETE_PHRASE = "delete";
        final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        int LIMIT = 100;
        int i = 0;
        ArrayList<Task> toDoList = new ArrayList<>();
        if (fileExists) {
            toDoList = loadTasksFromFile(String.valueOf(path));
            i = toDoList.size(); // Set the list size to the loaded tasks count
        }


        System.out.println(
                "Hello! I'm Sunacchi!\n" +
                        "What can I do for you?\n"
        );

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        String userInput = myObj.next();
        while (i < LIMIT && !userInput.equals(EXIT_PHRASE)) {
            if (userInput.equals(LIST_PHRASE)) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + "." +
                            toDoList.get(j).toString());
                }
                userInput = myObj.next();
                continue;
            }
//            if (userInput.equals("")) {
//                userInput = myObj.next();
//                continue;
//            }

            if (userInput.equals("mark")) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                curr.mark();
                System.out.println( "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals("unmark")) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                curr.unmark();
                System.out.println( "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals(DELETE_PHRASE)) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                toDoList.remove(Integer.parseInt(userInput) - 1);
                i--;
                System.out.println("Noted. I've removed this task:");
                System.out.println(curr.toString());
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals(TODO_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                toDoList.add(new Todo(userInput));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            if (userInput.equals(DEADLINE_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                System.out.println("To be done by?");
                String userInputBy = myObj.nextLine();
                LocalDate by;
                if (userInputBy.equals("today")) {
                    by = LocalDate.now();
                } else {
                    by = LocalDate.parse(userInputBy);
                }
                toDoList.add(new Deadlines(userInput, by));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            if (userInput.equals(EVENT_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                System.out.println("From?");
                LocalDateTime from = LocalDateTime.parse(myObj.nextLine(), DATE_TIME_FORMATTER);
                System.out.println("To?");
                LocalDateTime to = LocalDateTime.parse(myObj.nextLine(), DATE_TIME_FORMATTER);

                toDoList.add(new Events(userInput, from, to));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        saveTasksToFile(toDoList, String.valueOf(path));
        System.out.println("Bye. Hope to see you again soon!");

    }
    public static void saveTasksToFile(ArrayList<Task> tasks, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }
}
