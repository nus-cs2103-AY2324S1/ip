import types.Deadlines;
import types.Party;
import types.Task;
import types.Todo;
import exceptions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;


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
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = Storage.getLastList();
        Path path = Paths.get("barbie.txt");
        int indexNumber = list.size(); // Starting from 1 reduces the need to subtract and add 1 for usability.


        // Intro
        Ui.intro(Utils.getDateList(LocalDate.now(), list));

            loop:
            while (true) {
                try {

                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    Command command = Command.valueOf(parts[0].toUpperCase());

                    Ui.barbie();

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
                                    Storage.changeLineStatus(path, "1", taskNumber);

                                    // Output
                                    Ui.mark(list.get(taskNumber));
                                    break;

                                case UNMARK:
                                    // Editing variables
                                    list.get(taskNumber).unmark();
                                    Storage.changeLineStatus(path, "2", taskNumber);

                                    // Output
                                    Ui.unmark(list.get(taskNumber));

                                    break;

                                case DEL:
                                    // Editing variables
                                    list.remove(taskNumber);
                                    indexNumber -= 1;
                                    Storage.deleteLine(path, taskNumber);

                                    // Output
                                    Ui.del();
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
                                    LocalDate by = LocalDate.parse(parts2[1]);
                                    list.add(indexNumber, new Deadlines(desc, by));
                                    Storage.addToList(path, "D", desc, by);

                                    break;

                                case PARTY:
                                    if (parts2.length < 3) {
                                        throw new BarbieNoTimingException();
                                    }

                                    desc = parts2[0];
                                    LocalDate from = LocalDate.parse(parts2[1].strip());
                                    LocalDate to = LocalDate.parse(parts2[2].strip());

                                    list.add(indexNumber, new Party(desc, from, to));
                                    Storage.addToList(path, "P", desc, from, to);
                                    break;

                                default:
                                    list.add(indexNumber, new Todo(desc));
                                    Storage.addToList(path, "T", desc);
                                    break;

                            }

                            Ui.taskAdded(list.get(indexNumber));
                            indexNumber ++;
                            break;

                        case LIST:
                            // No variables to edit, only output (refer to listTasks func)
                            Ui.listTasks(list, indexNumber);
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

                } catch (DateTimeParseException e) {
                    System.out.println("Hey Barbie,, make sure to give dates in the format YYYY-MM-DD alright! ");
                    System.out.println(e.getMessage());

                } catch (Exception ex) {
                    System.out.println(ex.toString());

                }

                System.out.println("[you]:");

            }

            // Exit
            Ui.exit();

    }


}
