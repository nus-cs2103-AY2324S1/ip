import exception.DukeException;
import exception.InvalidCommandException;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
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

        String msg = "----------------------------------------------------------------- \n"
                + "Hello! I'm Paimon! \n"
                + "What can I do for you? \n"
                + "-----------------------------------------------------------------";

        System.out.println(logo + "\n" + msg);

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        String markCommand = "mark";
        String unmarkCommand = "unmark";
        String listCommand = "list";
        String deleteCommand = "delete";
        String byeCommand = "bye";
        String todoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";

        String command;

        ArrayList<Task> ls = new ArrayList<>();

        while (running) {
            command = sc.nextLine();
            try {
                if (command.startsWith(markCommand)) {
                    String indexStr = command.substring(markCommand.length() + 1);
                    int num = Integer.valueOf(indexStr);
                    Task t = ls.get(num - 1);
                    t.markDone();
                    System.out.println("Nice! I've marked this task as done: \n"
                            + t.toString()
                            + "\n-----------------------------------------------------------------");
                } else if (command.startsWith(unmarkCommand)) {
                    String indexStr = command.substring(unmarkCommand.length() + 1);
                    int num = Integer.valueOf(indexStr);
                    Task t = ls.get(num - 1);
                    t.markUndone();
                    System.out.println("OK, I've marked this task as not done yet: \n"
                            + t.toString()
                            + "\n-----------------------------------------------------------------");
                } else if (command.equalsIgnoreCase(byeCommand)) {
                    System.out.println("Bye Bye! See you soon :D"
                            + "\n-----------------------------------------------------------------");
                    running = false;
                } else if (command.equalsIgnoreCase(listCommand)) {
                    int i = 1;
                    System.out.println("Here are the tasks in your list: ");
                    for (Task t : ls) {
                        System.out.println(i + ". " + t.toString());
                        i++;
                    }
                    System.out.println("-----------------------------------------------------------------");
                } else if (command.startsWith(todoCommand)) {
                    if (command.length() <= todoCommand.length() + 1) {
                        throw new EmptyInputException("todo");
                    }
                    String des = command.substring(todoCommand.length() + 1);
                    Todo t = new Todo(des);
                    ls.add(t);
                    System.out.println(
                            "Got it. Task successfully added: \n"
                                    + t.toString()
                                    + "\nNow you have " + ls.size() + " tasks in the list"
                                    + "\n-----------------------------------------------------------------");
                } else if (command.startsWith(deadlineCommand)) {
                    if (command.length() <= deadlineCommand.length() + 1) {
                        throw new EmptyInputException("deadline");
                    } else if (!command.contains("/by")) {
                        throw new InvalidFormatException("deadline", "/by");
                    }
                    int byIndex = command.indexOf("/by") ;
                    String des = command.substring(deadlineCommand.length() + 1, byIndex - 1);
                    if (command.length() <= byIndex + 4) {
                        throw new EmptyDateTimeException("deadline");
                    }
                    String by = command.substring(byIndex + 4);
                    Deadline d = new Deadline(des, by);
                    ls.add(d);
                    System.out.println(
                            "Got it. Task successfully added: \n"
                                    + d.toString()
                                    + "\nNow you have " + ls.size() + " tasks in the list"
                                    + "\n-----------------------------------------------------------------");
                } else if (command.startsWith(eventCommand)) {
                    if (command.length() <= eventCommand.length() + 1) {
                        throw new EmptyInputException("event");
                    } else if (!command.contains("/from")) {
                        throw new InvalidFormatException("event", "/from");
                    } else if (!command.contains("/to")) {
                        throw new InvalidFormatException("event", "/to");
                    }
                    int fromIndex = command.indexOf("/from");
                    int toIndex = command.indexOf("/to");
                    if (command.length() <= fromIndex + 6) {
                        throw new EmptyDateTimeException("event");
                    } else if (command.length() <= toIndex + 4) {
                        throw new EmptyDateTimeException("event");
                    }
                    String des = command.substring(eventCommand.length() + 1, fromIndex - 1);
                    String start = command.substring(fromIndex + 6, toIndex - 1);
                    String end = command.substring(toIndex + 4);
                    Event e = new Event(des, start, end);
                    ls.add(e);
                    System.out.println(
                            "Got it. Task successfully added: \n"
                                    + e.toString()
                                    + "\nNow you have " + ls.size() + " tasks in the list"
                                    + "\n-----------------------------------------------------------------");
                } else if (command.startsWith(deleteCommand)) {
                    String indexStr = command.substring(deleteCommand.length() + 1);
                    int num = Integer.valueOf(indexStr);
                    Task task = ls.get(num - 1);
                    ls.remove(num - 1);
                    System.out.println("Noted. I've removed this task: \n"
                            + task.toString()
                            + "\nNow you have " + ls.size() + " tasks in the list");
                } else {
                    throw new InvalidCommandException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                                    + "\n-----------------------------------------------------------------");
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("☹ OOPS!!! Something went wrong D:");
            }
        }
        sc.close();
    }
}

