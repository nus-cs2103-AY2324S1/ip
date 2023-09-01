import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.IOException;


public class Barbie {


    enum Command {
        MARK,
        UNMARK,
        DEL,
        TODO,
        DEADLINE,
        PARTY,
        LIST,
        BYE,
    }


    public static void main(String[] args) {
        // CONSTANTS
        String line = "______________________________\n";
        Scanner scanner = new Scanner(System.in);
        String logo = " ____             _\n"
                + "|  _ \\           | |\n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\ |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/\n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        ArrayList<Task> list = getLastList();
        Path path = Paths.get("barbie.txt");
        int indexNumber = list.size(); // Starting from 1 reduces the need to subtract and add 1 for usability.


        // Intro
            System.out.println(line
                    + "Hi Barbie! Hi Ken!\n"
                    + "\nI'm\n"
                    + logo
                    + "\nWhat can I do for you?\n" +
                    line);
            System.out.println("[you]:");


            loop:
            while (true) {
                try {

                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    Command command = Command.valueOf(parts[0].toUpperCase());

                    System.out.println("\t" + line
                            + "\t [barbie]:\n");

                    switch (command) {
                        case MARK:
                        case UNMARK:
                        case DEL:

                            String desc = parts[1];
                            int taskNumber;
                            try {
                                taskNumber = Integer.parseInt(desc) - 1;
                            } catch (NumberFormatException e) {
                                throw new BarbieTaskNumberException();
                            }

                            switch (command) {
                                case MARK:
                                    // Editing variables
                                    list.get(taskNumber).mark();
                                    changeLineStatus(path, "1", taskNumber);

                                    // Output
                                    System.out.println("\t Nice! I've marked this task as done:\n"
                                            + "\t " + list.get(taskNumber) + "\n"
                                            + "\t" + line);
                                    break;

                                case UNMARK:
                                    // Editing variables
                                    taskNumber = Integer.parseInt(desc);
                                    list.get(taskNumber).unmark();
                                    changeLineStatus(path, "2", taskNumber);

                                    // Output
                                    System.out.println("\t Alright! I've marked this task as not done yet:\n"
                                            + "\t " + list.get(taskNumber) + "\n"
                                            + "\t" + line);
                                    break;

                                case DEL:
                                    // Editing variables
                                    list.remove(taskNumber);
                                    indexNumber -= 1;
                                    deleteLine(path, taskNumber);

                                    // Output
                                    System.out.println("\t Deletion success! I've deleted this task off your list.");
                                    break;

                            }
                            break;

                        case TODO:
                        case DEADLINE:
                        case PARTY:
                            if (parts.length < 2) {
                                throw new BarbieNoDescException();
                            }
                            desc = parts[1];
                            String[] parts2 = parts[1].split("/");
                            switch (command) {
                                case DEADLINE:
                                    if (parts2.length < 2) {
                                        throw new BarbieNoDeadlineException();
                                    }
                                    desc = parts2[0];
                                    String by = parts2[1];
                                    list.add(indexNumber, new Deadlines(desc, by));
                                    addToList(path, "D", desc, by);

                                    break;

                                case PARTY:
                                    if (parts2.length < 3) {
                                        throw new BarbieNoTimingException();
                                    }
                                    desc = parts2[0];
                                    String from = parts2[1];
                                    String to = parts2[2];
                                    list.add(indexNumber, new Party(desc, from, to));
                                    addToList(path, "P", desc, from, to);
                                    break;

                                default:
                                    list.add(indexNumber, new Todo(desc));
                                    addToList(path, "T", desc);
                                    break;

                            }

                            System.out.println("\tGot you barbie! I've added this task to your Barbie list:\n"
                                    + "\t " + list.get(indexNumber));
                            indexNumber ++;
                            break;

                        case LIST:
                            // No variables to edit, only output (refer to listTasks func)
                            listTasks(list, indexNumber);
                            break;

                        case BYE:
                            break loop; // break out of the while loop, not switch statement

                        default:
                            // Editing variables
                            list.add(indexNumber, new Task(input)); // Create a new Task
                            indexNumber += 1; //Incrementing item counter
                            // Output
                            System.out.println("\t Okey Dokey! I've added this task into your list:\n"
                                    + "\t[ ] " + input);
                            break;

                    }

                } catch (BarbieException e) {
                    System.out.println("Barbie Error!! " + e.getMessage());

                } catch (Exception ex) {
                    System.out.println(ex.toString());

                }

                System.out.println("\t" + line);
                System.out.println("[you]:");

            }

            // Exit
            System.out.println(line + "Bye Barbie! Bye Ken!\n" + line);

    }

    protected static void listTasks(ArrayList<Task> list,int indexNumber) {
        if (indexNumber == 0) {
            System.out.println("Hmm.. your list looks empty. To add items, use the 'todo', 'deadline' or 'party' commands!");
        }
        // "list" command
        for (int i = 0; i < indexNumber; i++) {
            int itemNumber = i + 1;
            System.out.println("\t" + itemNumber + ". " + list.get(i));
        }
    }

    protected static ArrayList<Task> getLastList() {
        ArrayList<Task> finalList = new ArrayList<>();

        try {
            Path path = Paths.get("barbie.txt");

            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("[A new list created for current user]");
            } else {
                System.out.println("[A current list is being used for current user]");
                Files.readAllLines(path).forEach(x -> {

                    String[] taskParts = x.split(",");
                    String taskType = taskParts[0];
                    String taskStatus = taskParts[1];
                    String desc = taskParts[2];
                    Task task;

                    if (Objects.equals(taskType, "T")) {
                        task = new Todo(desc);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadlines(desc, taskParts[3]);
                    } else if (Objects.equals(taskType, "P")) {
                        task = new Party(desc, taskParts[3], taskParts[4]);
                    } else {
                        task = new Task(desc);
                    }
                    if (Integer.parseInt( taskStatus) == 1) { task.mark(); }
                    finalList.add(task);
                    System.out.println(finalList);
                });

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;

    }

    protected static void deleteLine(Path path, int lineToDelete) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToDelete >= 0 && lineToDelete < lines.size()) {
                lines.remove(lineToDelete);
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to delete.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void addToList(Path path, String type, String desc) {
        String line = type + "," + 0 + "," + desc + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void addToList(Path path, String type, String desc, String deadline) {
        String line = type + "," + 0 + "," + desc + "," + deadline + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void addToList(Path path, String type, String desc, String from, String to) {
        String line = type + "," + 0 + "," + desc + "," + from + "," + to + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void changeLineStatus(Path path, String status, int lineToChange) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToChange >= 0 && lineToChange < lines.size()) {
                String[] newContent = lines.get(lineToChange).split(",");
                newContent[1] = status;
                lines.set(lineToChange, Arrays.stream(newContent).reduce("", (x, acc) -> x + acc));
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to change.");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
