import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        String inData = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");

        ArrayList<Task> list = loadTasks(); //load tasks from file when chatbot starts
        int count = list.size();

        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            try {
                if (inData.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                if (inData.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    continue;
                }

                if (inData.startsWith("mark ")) {
                    int num = inData.charAt(5) - '0' - 1;
                    if (num >= 0 && num < count) {
                        list.get(num).markAsDone();
                        System.out.println("Nice! I've marked this task done:");
                        System.out.println(list.get(num));
                    } else {
                        System.out.println("Invalid");
                    }
                    continue;
                }

                if (inData.startsWith("unmark ")) {
                    int num = inData.charAt(7) - '0' - 1;
                    if (num >= 0 && num < count) {
                        list.get(num).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(num));
                    } else {
                        System.out.println("Invalid");
                    }
                    continue;
                }

                if (inData.startsWith("todo ")) {
                    String des = inData.substring(5);
                    if (des.isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    list.add(new Todo(des));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if (inData.startsWith("deadline ")) {
                    if (inData.substring(9).isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] split = inData.substring(9).split(" /by ");
                    String des = split[0];
                    LocalDate date = LocalDate.parse(split[1]);
                    String by = date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
                    list.add(new Deadline(des, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if (inData.startsWith("event ")) {
                    if (inData.substring(6).isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] split = inData.substring(6).split(" /from ");
                    String des = split[0];
                    String[] fromto = split[1].split(" /to ");
                    String from = fromto[0];
                    String to = fromto[1];

                    list.add(new Event(des, from, to));
                    list.get(count).cat = "E";
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if (inData.startsWith("delete ")) {
                    int num = Integer.parseInt(inData.substring(7)) - 1;
                    if (num >= 0 && num < list.size()) {
                        list.remove(num);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(num));
                        count--;
                        System.out.println("Now you have " + count + " tasks in the list");

                    } else {
                        System.out.println("Invalid.");
                    }
                    continue;
                }
                throw new UnknownCommandException();


            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch  (DateTimeParseException e) {
                System.out.println("OOps invalid time input");
            }

        }
        if(inData.equals("bye")) {
            System.out.println("bye");
            saveTasks(list);
        }
    }

    //load tasks from the file into the chatbot
    private static ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File("duke.txt");
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String task = fileScanner.nextLine();
                    Task dtask = Taskparser.parseTask(task); //convert format of tasks in file to list
                    dukeList.add(dtask);
                }
            }
        }

        return dukeList;
    }

            // Save tasks to a data file
            private static void saveTasks(ArrayList<Task> list) {
                File file = new File("duke.txt");
                try (PrintWriter out = new PrintWriter("duke.txt")) {
                    for (Task task : list) {
                        String taskString = Taskparser.taskToString(task); //convert task to string
                        out.println(taskString);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error saving tasks to file: " + e.getMessage());
                }
        }
    }


