import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    private static final String chatbot = "War Room";
    private static ArrayList<Task> userData = new ArrayList<>(100);

    public static String[] validStartingCommands = {"todo", "deadline", "event"};

    public static String[] validMarkingCommands = {"mark", "unmark", "delete"};
    
    private static void rewriteFile(ArrayList<Task> newData) throws IOException, FileNotFoundException {
        try {
            FileWriter fw = new FileWriter("src/main/data/duke.txt");
            for (int i = 0; i < newData.size(); i++) {
                Task newTask = newData.get(i);
                int isDoneValue = newTask.isDone ? 1 : 0;
                if (Objects.equals(newTask.tag, "T")) {
                    fw.write(String.format("%s|%d|%s%n", newTask.generalTag(), isDoneValue, newTask.getDescription()));
                } else if (Objects.equals(newTask.tag, "D")) {
                    Deadline deadlineTask = (Deadline) newTask;
                    fw.write(String.format("%s|%d|%s|%s%n", deadlineTask.generalTag(), isDoneValue, deadlineTask.getDescription(), deadlineTask.byString()));
                } else if (Objects.equals(newTask.tag, "E")) {
                    Event eventTask = (Event) newTask;
                    fw.write(String.format("%s|%d|%s|%s|%s%n", eventTask.generalTag(), isDoneValue, eventTask.getDescription(), eventTask.getFrom(), eventTask.getTo()));
                }

            }
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException, IOException {
        try {
            File f = new File("src/main/data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String event = parts[0];
                int mark = Integer.parseInt(parts[1]);
                String description = parts[2];
                if (Objects.equals(event, "T")) {
                    Task newTask = new Todo(description);
                    newTask.isDone = mark == 1;
                    userData.add(newTask);
                } else if (Objects.equals(event, "D")) {
                    Task newTask = new Deadline(description, parts[3]);
                    newTask.isDone = mark == 1;
                    userData.add(newTask);
                } else if (Objects.equals(event, "E")) {
                    Task newTask = new Event(description, parts[3], parts[4]);
                    newTask.isDone = mark == 1;
                    userData.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String user_input = scanner.nextLine();
            String[] words = user_input.split(" ");

            if (words.length == 1) {
                try {
                    if (Objects.equals(words[0], "list")) {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < userData.size(); i++) {
                            Task currentTask = userData.get(i);
                            System.out.println((i + 1) + "." + currentTask.toString());
                        }
                    } else if (Objects.equals(words[0], "bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        scanner.close();
                        break;
                    } else if (Arrays.asList(validStartingCommands).contains(words[0])) {
                        throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", words[0]));
                        /* Throw our own class of exceptions */
                    } else if (Arrays.asList(validMarkingCommands).contains(words[0])) {
                        throw new DukeException("OOPS!!! Please provide a valid digit to mark the items :(");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
                    }
                } catch (DukeException DE) {
                    System.out.println(DE.getMessage());
                }
            } else {
                if (Arrays.asList(validStartingCommands).contains(words[0])) {

                    String taskDescription = "";
                    int by = 99999;
                    int from = 99999;
                    int to = 99999;
                    for (int j = 1; j < words.length; j++) {
                        if (Objects.equals(words[j], "/by")) {
                            by = j;
                            break;
                        } else if (Objects.equals(words[j], "/from")) {
                          from = j;
                          break;
                        } else {
                            taskDescription += " " + words[j];
                        }
                    }

                    /* Time to head into the specific details */
                    if (Objects.equals(words[0], "todo")) {
                        Task newTask = new Todo(taskDescription);
                        userData.add(newTask);
                        rewriteFile(userData);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask.toString());
                        System.out.println("Now you have " + userData.size() + " tasks in the list.");
                    } else if (Objects.equals(words[0], "deadline")) {
                        try {
                            if (Objects.equals(by, 99999)) {
                                throw new DukeException("For deadlines, please give a gauge of when it is due");
                            }

                            String deadline = "";
                            for (int i = by + 1; i < words.length; i++) {
                                deadline += " " + words[i];
                            }

                            Task newTask = new Deadline(taskDescription, deadline);
                            userData.add(newTask);
                            rewriteFile(userData);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTask.toString());
                            System.out.println("Now you have " + userData.size() + " tasks in the list.");
                        } catch (DukeException DE){
                            System.out.println(DE.getMessage());
                        }
                    } else {

                        try {
                            String fromChar = "";
                            for (int i = from + 1; i < words.length; i++) {
                                if (Objects.equals(words[i], "/to")) {
                                    to = i;
                                    break;
                                } else {
                                    fromChar += " " + words[i];
                                }
                            }

                            if (Objects.equals(fromChar, "")) {
                                throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
                            }

                            String toChar = "";
                            for (int i = to + 1; i < words.length; i++) {
                                toChar += " " + words[i];
                            }

                            if (Objects.equals(toChar, "")) {
                                throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
                            }

                            Task newTask = new Event(taskDescription, fromChar, toChar);
                            userData.add(newTask);
                            rewriteFile(userData);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTask.toString());
                            System.out.println("Now you have " + userData.size()  + " tasks in the list.");
                        } catch (DukeException DE) {
                            System.out.println(DE.getMessage());
                        }
                    }
                } else if (Arrays.asList(validMarkingCommands).contains(words[0])) {
                    try {
                        if (words.length > 2) {
                            throw new DukeException("Mark/UnMark/Delete commands can only take a maximum of 2 words!");
                        }
                    } catch (DukeException DE) {
                        System.out.println(DE.getMessage());
                    }

                    try {
                        try {
                            Integer.parseInt(words[1]);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Mark/UnMark/Delete commands can only take numbers as a parameter");
                        }

                        if (Integer.parseInt(words[1]) <= 0 || Integer.parseInt(words[1]) > userData.size()) {
                            throw new DukeException("Please input a valid number!");
                        }

                        int referenceIndex = Integer.parseInt(words[1]);
                        if (Objects.equals(words[0], "mark")) {
                            Task currentTask = userData.get(referenceIndex - 1);
                            currentTask.isDone = true;
                            rewriteFile(userData);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(currentTask.toString());
                        } else if (Objects.equals(words[0], "unmark")) {
                            Task currentTask = userData.get(referenceIndex - 1);
                            currentTask.isDone = false;
                            rewriteFile(userData);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(currentTask.toString());
                        } else {
                            Task removedTask = userData.get(referenceIndex - 1);
                            userData.remove(referenceIndex - 1);
                            rewriteFile(userData);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(removedTask.toString());
                            System.out.println(String.format("Now you have %d tasks in the list.", userData.size()));
                        }
                    } catch (DukeException DE) {
                        System.out.println(DE.getMessage());
                    }
                } else {
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException DE) {
                        System.out.println(DE.getMessage());
                    }
                }
            }
        }
    }
}