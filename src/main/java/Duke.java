import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) {
        String logo = " ___  __    ________  ________  ________   ________  ________      \r\n" + //
                "|\\  \\|\\  \\ |\\   __  \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\     \r\n" + //
                "\\ \\  \\/  /|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \r\n" + //
                " \\ \\   ___  \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \r\n" + //
                "  \\ \\  \\\\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \r\n" + //
                "   \\ \\__\\\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \r\n" + //
                "    \\|__| \\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\_________\\\r\n" + //
                "                                                       \\|_________|";

        String divider = "\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        // Date input format: dd/MM/yyyy HHmm
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        ArrayList<Task> list = new ArrayList<Task>();

        // Read from file
        try {
            File taskFile = new File("tasks.txt");
            Scanner taskScanner = new Scanner(taskFile);
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            while (taskScanner.hasNextLine()) {
                String task = taskScanner.nextLine();
                String[] taskComponents = task.split(" \\| ");
                String taskType = taskComponents[0];
                boolean taskStatus = taskComponents[1].equals("1");
                String taskDescription = taskComponents[2];

                switch (taskType) {
                    case "T":
                        ToDo todoTask = new ToDo(taskDescription);
                        todoTask.changeStatus(taskStatus);
                        list.add(todoTask);
                        break;
                    case "D":
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(taskComponents[3], fileFormatter);
                        Deadline deadlineTask = new Deadline(taskDescription, deadlineDateTime);
                        deadlineTask.changeStatus(taskStatus);
                        list.add(deadlineTask);
                        break;
                    case "E":
                        String[] taskDates = taskComponents[3].split(" - ");
                        LocalDateTime eventStartDateTime = LocalDateTime.parse(taskDates[0], fileFormatter);
                        LocalDateTime eventEndDateTime = LocalDateTime.parse(taskDates[1], fileFormatter);

                        Event event = new Event(taskDescription, eventStartDateTime, eventEndDateTime);
                        event.changeStatus(taskStatus);
                        list.add(event);
                        break;
                }
            }

            taskScanner.close();

        } catch (FileNotFoundException e) {
            // File does not exist
            try {
                // Create new file
                File taskFile = new File("tasks.txt");
                taskFile.createNewFile();
            } catch (Exception f) {
                System.out.println("Error creating new file.");
            }
        }

        String command = "";

        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat foolish errand do you seek to accomplish with my immense powers?");
        System.out.println(divider);

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                // End conversation
                break;
            } else if (command.equals("list")) {
                // List all tasks
                System.out.println(divider);
                System.out.println(
                        "You have somehow found the audacity to conjure up this laughable list of inconsequential endeavours:\n");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(divider);
            } else if (command.startsWith("mark")) {
                // Mark a task as done
                System.out.println(divider);
                int taskNum = Integer.parseInt(command.substring(5));
                try {
                    list.get(taskNum - 1).changeStatus(true);
                    System.out.println(
                            "Astonishingly enough, you have managed to triumph over this mind-bogglingly simple task:\n");
                    System.out.println(list.get(taskNum - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You have no such task, mortal.");
                }
                System.out.println(divider);
            } else if (command.startsWith("unmark")) {
                // Mark a task as not done
                System.out.println(divider);
                int taskNum = Integer.parseInt(command.substring(7));
                try {
                    list.get(taskNum - 1).changeStatus(false);
                    System.out.println(
                            "In a stunning twist, this task remains untouched by the hands of progress:\n");
                    System.out.println(list.get(taskNum - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You have no such task, mortal.");
                } finally {
                    System.out.println(divider);
                }
            } else if (command.startsWith("todo")) {
                // Add a todo task
                System.out.println(divider);
                try {
                    System.out.println("This task has been reluctantly bestowed upon your ever-growing list:\n");
                    if (command.substring(5).equals("")) {
                        throw new Exception("The description of a todo cannot be empty.");
                    }
                    list.add(new ToDo(command.substring(5)));
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out
                            .println("Congratulations, your pile of tasks has swelled to a whopping " + list.size()
                                    + ".");
                    ;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(divider);
                }
            } else if (command.startsWith("deadline")) {
                // Add a deadline task
                System.out.println(divider);
                try {
                    String[] deadlineTaskComponents = command.substring(9).split(" /by ");

                    if (deadlineTaskComponents[0].equals("")) {
                        throw new Exception("The description of a deadline cannot be empty.");
                    } else if (deadlineTaskComponents.length == 1) {
                        throw new Exception("The deadline of a deadline cannot be empty.");
                    } else if (deadlineTaskComponents.length > 2) {
                        throw new Exception("The deadline of a deadline cannot contain more than one date.");
                    } else if (deadlineTaskComponents[1].equals("")) {
                        throw new Exception("The deadline of a deadline cannot be empty.");
                    }

                    System.out.println(
                            "With your constant mediocrity, it is entirely unlikely that you will be able to meet this deadline I have just added: \n");
                    list.add(new Deadline(deadlineTaskComponents[0],
                            LocalDateTime.parse(deadlineTaskComponents[1], displayFormatter)));
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out
                            .println("Congratulations, your pile of tasks has swelled to a whopping " + list.size()
                                    + ".");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(divider);
                }
            } else if (command.startsWith("event")) {
                // Add an event task
                System.out.println(divider);
                try {
                    String[] eventTaskComponents = command.substring(6).split(" /from ");
                    String eventDescription = eventTaskComponents[0];
                    if (eventDescription.equals("")) {
                        throw new Exception("The description of an event cannot be empty.");
                    } else if (eventTaskComponents.length == 1) {
                        throw new Exception("The start time of an event cannot be empty.");
                    } else if (eventTaskComponents.length > 2) {
                        throw new Exception("The start time of an event cannot contain more than one date.");
                    }

                    String[] eventTaskDates = eventTaskComponents[1].split(" /to ");

                    if (eventTaskDates[1].equals("")) {
                        throw new Exception("The end time of an event cannot be empty.");
                    } else if (eventTaskDates.length > 2) {
                        throw new Exception("The end time of an event cannot contain more than one date.");
                    }

                    System.out.println(
                            "Looks like I will have to slow time down myself if you wish to make it to this event I just added:\n");
                    list.add(new Event(eventDescription, LocalDateTime.parse(eventTaskDates[0], displayFormatter),
                            LocalDateTime.parse(eventTaskDates[1], displayFormatter)));
                    System.out.println(list.get(list.size() - 1).toString());
                    System.out
                            .println("Congratulations, your pile of tasks has swelled to a whopping " + list.size()
                                    + ".");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(divider);
                }
            } else if (command.startsWith("delete")) {
                // Delete a task
                System.out.println(divider);
                int taskNum = Integer.parseInt(command.substring(7));
                try {
                    System.out.println(
                            "One less annoyance to plague your feeble list. This task has been banished:\n");
                    System.out.println(list.get(taskNum - 1).toString());
                    list.remove(taskNum - 1);
                    System.out.println(
                            "Congratulations, your pile of tasks has shrunk to a measly " + list.size() + ".");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You have no such task, mortal.");
                } finally {
                    System.out.println(divider);
                }
            } else {
                // Error message (unknown command)
                System.out.println(divider);
                System.out.println("Do not test my patience, mortal. Speak clearly.");
                System.out.println(divider);
            }
        }

        // Write to file
        try {
            FileWriter taskWriter = new FileWriter("tasks.txt");
            for (Task task : list) {
                taskWriter.write(task.toFileString() + "\n");
            }
            taskWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }

        System.out.println(divider);
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        System.out.println(divider);
        sc.close();
    }
}
