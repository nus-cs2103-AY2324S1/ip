package duke;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    protected static String name = "Alfred";

    protected static ArrayList<Task> list = new ArrayList<Task>();
    protected static int counter = 0;

    public static void println() {
        System.out.println("____________________________________________________________");
    }

    public static String getName() {
        return Duke.name;
    }

    public static int getCounter() {
        return Duke.counter;
    }

    public static void incrementCounter() {
        Duke.counter++;
    }
    public static void decrementCounter() {
        Duke.counter--;
    }

    public static void setList(Task item) {
        list.add(item);
    }


    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }



    public static void main(String[] args) {

        Scanner scanIn = new Scanner(System.in);
        println();
        System.out.println(String.format("Hello I'm %s, your personal assistant.", getName()));
        System.out.println("What can I do for you today, sir?");
        println();

        try {
            File f = new File("src/main/java/duke/data/duke.txt");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String text = fileScanner.nextLine();

                if (text.startsWith("unmark")) {
                    String[] splitText = text.split(" ");
                    int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToUnmark).markAsIncomplete();
                    continue;
                } else if (text.startsWith("mark")) {
                    String[] splitText = text.split(" ");
                    int numToMark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToMark).markAsComplete();
                    continue;
                } else if (text.startsWith("todo")) {
                    String description = text.substring(4);
                    if (description.isEmpty()) {
                        continue;
                    } else {
                        Todo todo = new Todo(description.trim());
                        setList(todo);
                        incrementCounter();
                    }
                } else if (text.startsWith("deadline")) {
                    String[] splitText = text.split("/");
                    String description = splitText[0].substring(8);
                    String deadline = splitText[1].trim().substring(3);
                    Deadline dl = new Deadline(description.trim(), deadline);
                    setList(dl);
                    incrementCounter();
                } else if (text.startsWith("event")) {
                    String[] splitText = text.split("/");
                    String description = splitText[0].substring(5);
                    String start = splitText[1].trim().substring(5);
                    String end = splitText[2].trim().substring(3);
                    Event event = new Event(description.trim(), start, end);
                    setList(event);
                    incrementCounter();
                } else if (text.startsWith("delete")) {
                    String[] splitText = text.split(" ");
                    int numToDelete = Integer.parseInt(splitText[1]) - 1;
                    decrementCounter();
                    list.remove(numToDelete);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        while (true) {
            try {
                String text = scanIn.nextLine();

                if (text.length() > 3 && text.substring(0, 4).equals("list")) {
                    println();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) == null) {
                            break;
                        } else {
                            System.out.println(String.format("%d. [%s] [%s] %s", i + 1, list.get(i).tag, list.get(i).getStatusIcon(), list.get(i)));
                        }
                    }
                    println();
                    continue;
                } else if (text.startsWith("unmark")) {
                    try {
                        appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToUnmark).markAsIncomplete();

                    println();
                    System.out.println("Alright! I'll uncheck this task for you: ");
                    System.out.println(String.format("\t [%s] [%s] %s", list.get(numToUnmark).tag, list.get(numToUnmark).getStatusIcon(), list.get(numToUnmark)));
                    println();
                    continue;
                } else if (text.startsWith("mark")) {
                    try {
                        appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToMark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToMark).markAsComplete();

                    println();
                    System.out.println("Alright! I'll check this task as complete for you: ");
                    System.out.println(String.format("\t [%s] [%s] %s", list.get(numToMark).tag, list.get(numToMark).getStatusIcon(), list.get(numToMark)));
                    println();
                    continue;
                } else if (text.equals("bye")) {
                    println();
                    System.out.println("Goodbye. Hope to be of service again soon!");
                    println();
                    break;
                } else if (text.startsWith("todo")) {
                    String description = text.substring(4);
                    if (description.isEmpty()) {
                        throw new DukeException("I apologise, sir. But the description of todo cannot be empty");
                    } else {
                        try {
                            appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        Todo todo = new Todo(description.trim());
                        setList(todo);
                        incrementCounter();

                        println();
                        System.out.println("Noted Sir. I've added this task to your list: ");
                        System.out.println(String.format("\t [%s] [%s] %s", todo.tag, todo.getStatusIcon(), todo.toString()));
                        System.out.println(String.format("As of now, you have %d tasks on the agenda.", getCounter()));
                        println();
                    }
                } else if (text.startsWith("deadline")) {
                    String[] splitText = text.split("/");
                    String description = splitText[0].substring(8);
                    if (description.isEmpty()){
                        throw new DukeException("I apologise, sir. But the description and deadline cannot be empty");

                    } else {
                        try {
                            appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        String deadline = splitText[1].trim().substring(3);
                        Deadline dl = new Deadline(description.trim(), deadline);
                        setList(dl);
                        incrementCounter();

                        println();
                        System.out.println("Noted Sir. I've added this task to your list: ");
                        System.out.println(String.format("\t [%s] [%s] %s", dl.tag, dl.getStatusIcon(), dl.toString()));
                        System.out.println(String.format("As of now, you have %d tasks on the agenda.", getCounter()));
                        println();
                    }
                } else if (text.startsWith("event")) {
                    String[] splitText = text.split("/");
                    String description = splitText[0].substring(5);
                    if (description.isEmpty()){
                        throw new DukeException("I apologise, sir. But the description, start and end cannot be empty");

                    } else {
                        try {
                            appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        String start = splitText[1].trim().substring(5);
                        String end = splitText[2].trim().substring(3);
                        Event event = new Event(description.trim(), start, end);
                        setList(event);
                        incrementCounter();

                        println();
                        System.out.println("Noted Sir. I've added this task to your list: ");
                        System.out.println(String.format("\t [%s] [%s] %s", event.tag, event.getStatusIcon(), event.toString()));
                        System.out.println(String.format("As of now, you have %d tasks on the agenda.", getCounter()));
                        println();
                    }

                } else if (text.startsWith("delete")) {
                    try {
                        appendToFile("src/main/java/duke/data/duke.txt", text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToDelete = Integer.parseInt(splitText[1]) - 1;

                    println();
                    System.out.println("Alright Sir, I have removed this task from the list for you.");
                    System.out.println(String.format("\t [%s] [%s] %s",list.get(numToDelete).tag, list.get(numToDelete).getStatusIcon(), list.get(numToDelete).toString()));
                    decrementCounter();
                    list.remove(numToDelete);
                    System.out.println(String.format("Now you have %d tasks left.", getCounter()));
                } else {
                    throw new DukeException("I apologise, sir. But I do not understand what you mean.");
                }
            } catch(DukeException e){
                println();
                System.out.println(e.message);
                println();
            }
        }
    }
}



