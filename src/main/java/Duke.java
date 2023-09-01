import exception.DukeException;
import exception.InvalidCommandException;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidFormatException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

public class Duke {
    private static String separator = "\n-----------------------------------------------------------------";
    ArrayList<Task> ls;
    public Duke() {
        this.ls = Duke.initTasks();
    }
    public static void main(String[] args) {
        String logo =" ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣶⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⠿⠛⠋⠉⠩⣄⠘⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡏⠑⠒⠀⠀⣀⣀⠀⠀⢹⠈⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣷⡀⢀⣰⣿⡿⣿⣧⠀⠀⢡⣾⣧⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣯⣴⣿⠿⣄⣤⣾⡿⠟⠛⠛⠿⢿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⠿⠛⠋⠙⣿⣏⠀⠀⢻⣿⣡⣀⣀⠀⠀⠀⠀⢹⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⠿⠋⠁⠀⣀⣤⣶⣾⣿⣿⣤⣤⣾⣿⠉⠉⠙⠻⣿⠆⢀⣾⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠋⠁⠀⣀⣴⣿⠿⠛⠉⠀⢀⣿⡿⠿⠟⢿⣆⠀⢀⣴⣯⣴⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠋⠀⠀⣠⣾⠟⠋⠀⠀⠀⠀⠀⣈⣿⣷⣤⣴⣾⣿⣈⣻⣿⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⣰⡿⠁⠀⣠⡾⠋⠁⠀⠀⢀⣠⣴⠶⠞⠛⠛⠋⠉⠉⠉⠉⠙⠛⠻⠷⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠰⣿⠁⠀⠀⣿⣄⣀⣠⣴⡾⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⠿⣶⣄⠀⠀⠀⢀⣠⡄⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⠶⢾⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢷⣄⠉⠙⠻⠿⠟⢹⡇⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣄⠀⠀⠀⠀⠘⣿⣿⣦⣀⠀⠲⣾⣁⠀⠀⠀⠀⠀⡀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣦⡀⠀⠀⠀⣿⡿⣿⣿⣿⡆⠀⠉⠛⠛⠛⠛⢻⡏⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⣠⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⡄⣸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠛⢦⡀⠰⣿⣿⣿⣽⣿⡇⠀⠀⠀⠀⠀⢠⡿⠀⠀ \n"
                + "⠀⠀⠀⠀⣀⣤⡾⢻⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⡏⠙⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠰⣽⣶⣄⠉⠻⣿⣿⣧⠀⠀⢀⣤⣾⠟⠁⠀⠀ \n"
                + "⢰⣶⡾⠛⠋⠉⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⢸⣸⡇⠀⣨⣿⣾⡋⠀⠀⠀⠀⢀⠀⠀⣿⡀⠀⠈⠛⢷⣄⠈⠛⣿⡆⠀⠘⣿⡀⠀⠀⠀⠀ \n"
                + "⠀⠙⠿⣦⣀⠀⠀⠀⠀⠀⠀⡾⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠋⠀⠙⢿⣦⣀⠀⠀⠘⣷⣄⣹⣧⠀⠀⠀⠈⢻⣦⠀⠈⠋⠀⠀⠘⣧⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠈⠛⠿⢶⡶⠃⠀⣰⠃⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⠉⠻⢷⣦⣤⣘⣿⡛⠛⠀⢀⣴⣶⣦⡹⣷⡀⠀⠀⠀⠀⠸⣧⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⢠⡿⠃⠀⢀⡟⠀⠀⠀⠀⠀⠀⣼⣿⠀⠀⢀⣴⣿⣿⣷⡄⠀⠀⠈⠉⠉⠉⠉⠀⠀⢸⣿⣿⣿⣷⠻⣧⠀⠀⠀⠀⠀⢿⡆⠀⠀ \n"
                + "⠀⠀⠀⠀⢰⣿⠁⠀⠀⢸⠁⠀⠀⠀⠀⠈⠋⣿⠀⠀⠸⣿⣿⣿⣿⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀  ⠈⠛⠿⠟⠃⠀⢻⣧⠀⠀⠀⠸⣧⠀⠀ \n"
                + "⠀⠀⠀⠀⣿⡇⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠉⠻⠿⠿⠋⠀⢠⡀⠀⠀⣀⣀⣀⣸⠇⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⣿⡀⠀ \n"
                + "⠀⠀⠀⢰⣿⠁⠀⠀⢰⡏⠀⠀⠀⠀⠀⠀⠀⠀⢿⡀⠀⠀⠀⠀⠀ ⠀⠀⠈⠛⠒⠛⠉⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⠀⢸⡇⠀ \n"
                + "⠀⠀⠀⢸⣿⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⠁⠀⠀⠀⠀⢸⣿⠀ \n"
                + "⠀⠀⠀⢸⣿⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣤⣶⡿⠋⣿⠀⠀⠀⠀⠀⠀⣿⠀ \n"
                + "⠀⠀⠀⠘⣿⡄⠀⠀⢸⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣧⣄⣀⣀⣀⣠⣤⣶⣶⣾⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⣠⠀⠀⢠⣿⡄ \n"
                + "⠀⠀⠀⠀⢻⣧⠀⠀⠸⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣟⠛⢻⡟⢻⡉⠉⣫⣀⠀⠀⠀⠉⠉⠛⠙⠿⣷⣿⡿⠁⢀⣰⡟⠀⠀⢸⣿⠀ \n"
                + "⠀⠀⠀⠀⠈⢻⣧⡀⠀⠹⣷⡀⠀⠀⠀⠀⢠⠀⠀⠀⠘⣿⣿⣿⢻⡿⠀⠈⠷⠟⠁⠘⢾⣿⣶⣤⣶⣾⡇⠀⣿⣿⣤⣶⣿⣿⠃⠀⠀⣾⡟⠀ \n"
                + "⠀⠀⠀⠀⠀⠈⠻⣷⣄⡀⠹⣷⣄⠀⠀⠀⢸⣷⣤⡀⠀⠈⢻⣿⣯⣤⠀⠀⣠⡀⠀⢀⣼⣿⣿⣿⣿⣟⠁⠐⠿⣿⣿⣿⣿⠋⠀⢀⣾⠟⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣷⣮⣽⣷⣶⣤⣤⣿⣿⣿⣷⣶⣦⣭⣿⣿⣧⣠⠵⢯⡆⠚⣯⢿⠋⠛⠛⢫⣀⣠⣾⣿⢿⣿⣥⣤⠶⠛⠁⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⢁⣾⣿⣿⣿⠿⠿⠿⠿⠻⢿⣿⣿⣷⣦⣤⣤⣀⣤⣤⣄⣶⣿⣿⡿⠟⠉⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⡇⠀⠀⣀⡀⠈⢿⣧⠀⠉⠙⠛⠛⠛⠛⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣴⣿⣿⡏⠛⠉⢻⣿⣿⣿⣾⣿⣿⠀⢸⣿⠀⠀⠀⠀⠀⠙⠓⠢⠀⠀⠀⠀⠀⠸⠀⠀⠀⠘⣿⡄⠀⠀⠀⠀⠀ \n";

        String msg = separator
                + "\nHello! I'm Paimon!"
                + "\nWhat can I do for you?"
                + separator;

        System.out.println(logo + "\n" + msg);

        Duke paimon = new Duke();

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            String input = sc.nextLine();
            try {
                String command = input.split(" ")[0];
                Command c = Command.valueOf(command.toUpperCase());
                switch (c) {
                case MARK:
                    Duke.markTask(input, paimon.ls);
                    break;
                case UNMARK:
                    Duke.unmarkTask(input, paimon.ls);
                    break;
                case LIST:
                    Duke.listItems(paimon.ls);
                    break;
                case BYE:
                    System.out.println("Bye Bye! See you soon :D"
                            + separator);
                    running = false;
                    break;
                case TODO:
                    Duke.todo(input, paimon.ls);
                    break;
                case DEADLINE:
                    Duke.deadline(input, paimon.ls);
                    break;
                case EVENT:
                    Duke.event(input, paimon.ls);
                    break;
                case DELETE:
                    Duke.delete(input, paimon.ls);
                    break;
                default:
                    throw new InvalidCommandException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                                    + separator);
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println("☹ OOPS!!! ");
            } catch (Exception e) {
                System.out.println("☹ OOPS!!! Something went wrong D:"
                        + separator);
            } finally {
                writeTasks(paimon.ls);
            }
        }
        sc.close();
    }

    private static void writeTasks(ArrayList<Task> ls) {
        try {
            FileWriter fw = new FileWriter("./data/paimon.txt");
            for (Task t : ls) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    private static ArrayList<Task> readTasks(File file) {
        ArrayList<Task> inputList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                char taskType = input.charAt(1);
                boolean isDone = input.charAt(4) == 'X';
                String des = input.split("] ")[1];
                if (taskType == 'T') {
                    Todo t = new Todo(des);
                    if (isDone) {
                        t.markDone();
                    }
                    inputList.add(t);
                } else if (taskType == 'D') {
                    int byIndex = des.indexOf("(by: ");
                    int endIndex = des.indexOf(")");
                    String deadlineDes = des.substring(0, byIndex);
                    String by = des.substring(byIndex + 5, endIndex);
                    Deadline d = new Deadline(deadlineDes, by);
                    if (isDone) {
                        d.markDone();
                    }
                    inputList.add(d);
                } else if (taskType == 'E') {
                    int fromIndex = des.indexOf("(from: ");
                    int toIndex = des.indexOf(" to: ");
                    int endIndex = des.indexOf(")");
                    String eventDes = des.substring(0, fromIndex - 1);
                    String start = des.substring(fromIndex + 7, toIndex);
                    String end = des.substring(toIndex + 5, endIndex);
                    Event e = new Event(eventDes, start, end);
                    if (isDone) {
                        e.markDone();
                    }
                    inputList.add(e);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            return inputList;
        }
    }

    private static ArrayList<Task> initTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File inputFile = new File("./data/paimon.txt");
            if (inputFile.createNewFile()) {
                // creates new file
            } else {
                tasks = readTasks(inputFile);
            }
        } catch (IOException e) {
            System.out.println("IOException occured");
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }

    private static void markTask(String input, ArrayList<Task> ls) {
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        Task t = ls.get(num - 1 );
        t.markDone();
        System.out.println("Nice! I've marked this task as done: \n"
                + t
                + separator);
    }

    private static void unmarkTask(String input, ArrayList<Task> ls) {
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        Task t = ls.get(num - 1 );
        t.markUndone();
        System.out.println("Nice! I've marked this task as done: \n"
                + t
                + separator);
    }

    private static void listItems(ArrayList<Task> ls) {
        int i = 1;
        System.out.println("Here are the tasks in your list: ");
        for (Task t : ls) {
            System.out.println(i + ". " + t.toString());
            i++;
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private static void todo(String input, ArrayList<Task> ls) throws EmptyInputException {
        if (input.split(" ").length > 1) {
            String des = input.split(" ", 2)[1];
            Todo t = new Todo(des);
            ls.add(t);
            System.out.println(
                    "Got it. Task successfully added: \n"
                            + t.toString()
                            + "\nNow you have " + ls.size() + " tasks in the list"
                            + separator);
        } else {
            throw new EmptyInputException("todo");
        }
    }

    private static void deadline(String input, ArrayList<Task> ls)
            throws EmptyInputException, InvalidFormatException, EmptyDateTimeException {
        if (input.split(" ").length < 2) {
            throw new EmptyInputException("deadline");
        } else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        } else if (input.length() <= input.indexOf("/by") + 4) {
            throw new EmptyDateTimeException("deadline");
        } else {
            String tempDes = input.split(" ", 2)[1];
            System.out.println(tempDes);
            String des = tempDes.split(" /by " )[0];
            String by = tempDes.split(" /by ")[1];
            Deadline d = new Deadline(des, by);
            ls.add(d);
            System.out.println(
                    "Got it. Task successfully added: \n"
                            + d.toString()
                            + "\nNow you have " + ls.size() + " tasks in the list"
                            + separator);
        }
    }

    private static void event(String input, ArrayList<Task> ls)
            throws EmptyInputException, InvalidFormatException, EmptyDateTimeException {
        if (input.split(" ").length < 2) {
            throw new EmptyInputException("event");
        } else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        } else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        } else if (input.length() <= input.indexOf("/from") + 6) {
            throw new EmptyDateTimeException("event");
        } else if (input.length() <= input.indexOf("/to") + 4) {
            throw new EmptyDateTimeException("event");
        } else {
            String tempDes = input.split(" ", 2)[1];
            String des = tempDes.split(" /from ")[0];
            String start = tempDes.split(" /from ")[0].split(" /to ")[0];
            String end = tempDes.split(" /to ")[1];
            Event e = new Event(des, start, end);
            ls.add(e);
            System.out.println(
                    "Got it. Task successfully added: \n"
                            + e
                            + "\nNow you have " + ls.size() + " tasks in the list"
                            + separator);
        }
    }

    private static void delete(String input, ArrayList<Task> ls) {
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        Task task = ls.get(num - 1);
        ls.remove(num - 1);
        System.out.println("Noted. I've removed this task: \n"
                + task.toString()
                + "\nNow you have " + ls.size() + " tasks in the list");
    }
}

