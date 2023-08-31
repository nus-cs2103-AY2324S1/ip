import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * This program is a chatbot, somebodyhaha, used to mark completion of tasks
 * marking the completion of tasks
 *
 * @author: Low Jun Yu
 * @version: CS2103T AY23/24 Semester 1
 */
public class Duke {
    /**
     * The program reads input given by the user to perform functions to help
     * add, edit, track and delete tasks inputted
     */
    public static void main(String[] args) throws NoTaskException, IOException {

        final String FILENAME = "./data/duke.txt"; //Default filename

        ArrayList<Task> tasklst = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Printer.printGreeting();

        //Scans the file and initialises info in the tasklst, creating a new file if one is not found
        File f = new File(FILENAME);
        if (f.exists()) {
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String info = fileReader.nextLine();
                String[] fields;
                fields = info.split(" \\| ");
                Task tempT;
                try {
                    switch (fields[0].trim()) {
                    case "T":
                        tempT = ToDo.of(fields[2]);
                        tasklst.add(tempT);
                        break;
                    case "D":
                        tempT = new Deadline(fields[2], fields[3]);
                        tasklst.add(tempT);
                        break;
                    case "E":
                        String[] time;
                        time = fields[3].split("->");
                        tempT = new Event(fields[2], time[0], time[1]);
                        tasklst.add(tempT);
                        break;
                    }
                } catch (InvalidFormatException e){
                    Printer.printLine();
                    System.out.println("Format Error! Please format the date in {yyyy mm dd} or {yyyy mm dd hhhh}");
                    Printer.printLine();
                }
            }
            fileReader.close();
        } else {
            File newFile = new File("./data/duke.txt");
            newFile.createNewFile();
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // while loop containing possible inputs
        while (!input.equals("bye")) {
            try {
                String[] words = input.split("\\s+");
                String[] fields;
                Task tempTask;
                switch (words[0]) {
                case "list":
                    if (words.length > 1) {
                        throw new InvalidArgumentException("Please enter 'list' without any extra arguments " +
                                "or use a different " +
                                "keyword");
                    }
                    Printer.printList(tasklst);
                    break;
                case "mark":
                    if (words.length != 2) {
                        throw new InvalidArgumentException("Please enter 'mark {task number}' or use a different " +
                                "keyword");
                    }
                    int temp = Character.getNumericValue(input.charAt(5));
                    tasklst.get(temp - 1).markAsDone();
                    TaskWriter.rewriteTask(FILENAME, tasklst);
                    break;
                case "unmark":
                    if (words.length != 2) {
                        throw new InvalidArgumentException("Please enter 'unmark {task number}' " +
                                "or use a different " +
                                "keyword");
                    }
                    int temp2 = Character.getNumericValue(input.charAt(7));
                    tasklst.get(temp2 - 1).unmarkAsDone();
                    TaskWriter.rewriteTask(FILENAME, tasklst);
                    break;
                case "delete":
                    if (words.length != 2) {
                        throw new InvalidArgumentException("Please enter 'delete {task number}' " +
                                "or use a different " +
                                "keyword");
                    }
                    int delNum = Character.getNumericValue(input.charAt(7));
                    tempTask = tasklst.remove(delNum - 1);
                    tempTask.deleteTask(tasklst.size());
                    TaskWriter.rewriteTask(FILENAME, tasklst);
                    break;
                case "todo":
                    tempTask = ToDo.of(input.substring(5));
                    tasklst.add(tempTask);
                    Printer.addTask(tempTask, tasklst.size());
                    TaskWriter.appendTask(FILENAME, tempTask);
                    break;
                case "deadline":
                    fields = input.substring(9).split(" /by ");
                    if (fields.length != 2) {
                        throw new InvalidArgumentException("Please enter 'deadline {task description} " +
                                "'/by' {date}' or use a different " +
                                "keyword");
                    }
                    tempTask = new Deadline(fields[0], fields[1]);
                    tasklst.add(tempTask);
                    Printer.addTask(tempTask, tasklst.size());
                    TaskWriter.appendTask(FILENAME, tempTask);
                    break;
                case "event":
                    fields = input.substring(6).split(" /from | /to ");
                    if (fields.length != 3) {
                        throw new InvalidArgumentException("Please enter 'event {task description} " +
                                "'/from' {start} '/to' {finish} " +
                                "or use a different " +
                                "keyword");
                    }
                    tempTask = new Event(fields[0], fields[1], fields[2]);
                    tasklst.add(tempTask);
                    Printer.addTask(tempTask, tasklst.size());
                    TaskWriter.appendTask(FILENAME, tempTask);
                    break;
                default:
                    throw new UnknownActionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (UnknownActionException | NoTaskException e) {
                Printer.printLine();
                System.out.println(e.getMessage());
                Printer.printLine();
            } catch (InvalidArgumentException e) {
                Printer.printLine();
                System.out.println("OOPS! Invalid number of arguments " + e.getMessage());
                Printer.printLine();
            } catch (IOException | InvalidFormatException e) {
                Printer.printLine();
                System.out.println("Error!");
                e.printStackTrace();
                Printer.printLine();
            }
            input = sc.nextLine();
        }

        Printer.printExit();
    }
}
