import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Duke {
    static List<Task> tasks;
    static int count;
    public static void main(String[] args) {
        tasks = new ArrayList<>();
        Duke buddy = new Duke();

        try {
            if (!Files.isDirectory(Paths.get("data/"))) {
                Files.createDirectories(Paths.get("data/"));
            }

            if (!Files.exists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
                System.out.println("New file created");
            }
            buddy.saveTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }



        Scanner scanner = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("\t " + "Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n" +
                "\t " + "What can I do for you?");
        printHorizontalLine();
        int count = 0;

        while (count <=100 ) {
            String input = scanner.nextLine();


            if (input.equals("bye")) {
                System.out.println("\t " + "Bye! Hope to see you again soon!");
                printHorizontalLine();
                break;
            }

            //marking task
            else if (input.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5));
                    tasks.get(taskIndex - 1).markAsDone();
                    printHorizontalLine();
                    System.out.println("\tGreat! I've marked this task as done:");
                    System.out.println("\t" + taskIndex + "." + tasks.get(taskIndex - 1).toString());
                    printHorizontalLine();
                    buddy.writeTasksToFile();
                }
                catch (IOException i) {
                    System.out.println(i);
                }

            }

            //unmarking task
            else if (input.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7));
                    tasks.get(taskIndex - 1).markAsNotDone();
                    printHorizontalLine();
                    System.out.println("\tOk! I've marked this task as not done yet:");
                    System.out.println("\t" + taskIndex + "." + tasks.get(taskIndex - 1).toString());
                    printHorizontalLine();
                    buddy.writeTasksToFile();
                }
                catch (IOException i) {
                    System.out.println(i);
                }

            }

            //displaying list
            else if (input.equals("list")) {
                try {
                    printHorizontalLine();
                    System.out.println("\tHere are the tasks in your list:");
                    if (tasks.size() == 0) {
                        throw new DukeException("\t Seems like you have no tasks at the moment :) ");

                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("\t" + i + ". " + tasks.get(i - 1).toString());
                    }
                    printHorizontalLine();
                }
                catch (DukeException e) {
                    e.printMessage();
                }
            }

            // deleting task from list
            else if (input.startsWith("delete ")) {
                try {
                    int pos = 0;
                    pos = Integer.parseInt(input.substring(7).trim());
                    if (pos > tasks.size() || pos == 0) {
                        throw new DukeException("\tThis number is out of bounds! ");
                    }
                    Task element = tasks.get(pos - 1);
                    tasks.remove(pos - 1);
                    printHorizontalLine();
                    System.out.println("\tOkie I've removed this task:\n\t" + element.toString());
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                    buddy.writeTasksToFile();
                }
                catch (DukeException e) {
                    e.printMessage();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }

            // adding task to list
            else {
                try {
                    addTaskToList(input);
                    buddy.writeTasksToFile();
                }
                catch (DukeException e){
                    e.printMessage();
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
        }

    }

    public static void addTaskToList(String input) throws DukeException {
        if (input.startsWith("todo")) {
            if (input.trim().length() <= 4 ) {
                throw new DukeException("\t Sorry! The description of a todo cannot be empty :(");
            }
            Task task = new ToDo(input.substring(5));
            tasks.add(task);
            printHorizontalLine();
            System.out.println("\tNo problem! I have added this task:");
            System.out.println("\t" + task.toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
            printHorizontalLine();
            count++;
        }

        else if (input.startsWith("deadline")) {
            if (input.trim().length() <= 8) {
                throw new DukeException("\t Sorry! The description of a deadline cannot be empty :(");
            }
            int index = input.lastIndexOf("/by");
            Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
            tasks.add(task);
            printHorizontalLine();
            System.out.println("\tNo problem! I have added this task:");
            System.out.println("\t" + task.toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
            printHorizontalLine();
            count++;
        }

        else if (input.startsWith("event")) {
            if (input.trim().length() <= 5) {
                throw new DukeException("\t Sorry! The description of an event cannot be empty :(");
            }
            int indexFrom = input.lastIndexOf("/from");
            int indexTo = input.lastIndexOf("/to");
            Task task = new Event(input.substring(6, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1), input.substring(indexTo + 4));
            tasks.add(task);
            printHorizontalLine();
            System.out.println("\tNo problem! I have added this task:");
            System.out.println("\t" + task.toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
            printHorizontalLine();
            count++;
        }

        else {
            throw new DukeException("\tHey bud! Sorry I don't quite know what you mean :-(");
        }

    }

    private void saveTasks() throws IOException {
        Scanner scanner = new Scanner(new File("data/duke.txt"));
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split("\\|");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].strip();
            }
            String description = split[2];

            switch (split[0]) {
                case "T":
                    tasks.add(new ToDo(description));
                    break;
                case "D":
                    String by = split[3];
                    tasks.add(new Deadline(description, by));
                    break;
                case "E":
                    String from = split[3];
                    String to = split[4];
                    tasks.add(new Event(description, from, to));
                    break;
            }
        }
    }

    private void writeTasksToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("data/duke.txt");
        for (Task t: tasks) {
            fileWriter.write(t.toWriteString()+"\n");
        }
        fileWriter.close();
    }



    public static void printHorizontalLine() {
        System.out.println("    __________________________________________________________________");
    }


}