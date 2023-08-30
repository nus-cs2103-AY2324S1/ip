import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();

        ArrayList<Task> list = new ArrayList<Task>();

        try {
            File taskFile = new File("tasks.txt");
            Scanner taskScanner = new Scanner(taskFile);

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
                        String deadlineDate = taskComponents[3];
                        Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
                        deadlineTask.changeStatus(taskStatus);
                        list.add(deadlineTask);
                        break;
                    case "E":
                        String[] taskDates = taskComponents[3].split(" - ");
                        Event event = new Event(taskDescription, taskDates[0], taskDates[1]);
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
                ui.showError("Error creating new file.");
            }
        }

        String command = "";

        ui.startMessage();

        while (true) {
            command = ui.commandPrompt();
            if (command.equals("bye")) {
                // End conversation
                break;
            } else if (command.equals("list")) {
                // List all tasks
                ui.listTasks(list);
            } else if (command.startsWith("mark")) {
                // Mark a task as done
                ui.printDivider();
                int taskNum = Integer.parseInt(command.substring(5));
                try {
                    list.get(taskNum - 1).changeStatus(true);
                    ui.markTaskMessage(list.get(taskNum - 1));
                } catch (IndexOutOfBoundsException e) {
                    ui.showError("You have no such task, mortal.");
                }
                ui.printDivider();
            } else if (command.startsWith("unmark")) {
                // Mark a task as not done
                ui.printDivider();
                int taskNum = Integer.parseInt(command.substring(7));
                try {
                    list.get(taskNum - 1).changeStatus(false);
                    ui.unmarkTaskMessage(list.get(taskNum - 1));
                } catch (IndexOutOfBoundsException e) {
                    ui.showError("You have no such task, mortal.");
                } finally {
                    ui.printDivider();
                }
            } else if (command.startsWith("todo")) {
                // Add a todo task
                ui.printDivider();
                try {
                    if (command.substring(5).equals("")) {
                        ui.showError("The description of a todo cannot be empty.");
                    }
                    list.add(new ToDo(command.substring(5)));
                    ui.todoMessage(list.get(list.size() - 1));
                    ui.taskListSizeMessage(list.size(), true);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.printDivider();
                }
            } else if (command.startsWith("deadline")) {
                // Add a deadline task
                ui.printDivider();
                try {
                    String[] deadlineTaskComponents = command.substring(9).split(" /by ");

                    if (deadlineTaskComponents[0].equals("")) {
                        ui.showError("The description of a deadline cannot be empty.");
                    } else if (deadlineTaskComponents.length == 1) {
                        ui.showError("The deadline of a deadline cannot be empty.");
                    } else if (deadlineTaskComponents.length > 2) {
                        ui.showError("The deadline of a deadline cannot contain more than one date.");
                    } else if (deadlineTaskComponents[1].equals("")) {
                        ui.showError("The deadline of a deadline cannot be empty.");
                    }

                    list.add(new Deadline(deadlineTaskComponents[0], deadlineTaskComponents[1]));
                    ui.deadlineMessage(list.get(list.size() - 1));
                    ui.taskListSizeMessage(list.size(), true);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.printDivider();
                }
            } else if (command.startsWith("event")) {
                // Add an event task
                ui.printDivider();

                try {
                    String[] eventTaskComponents = command.substring(6).split(" /from ");
                    String eventDescription = eventTaskComponents[0];
                    if (eventDescription.equals("")) {
                        ui.showError("The description of an event cannot be empty.");
                    } else if (eventTaskComponents.length == 1) {
                        ui.showError("The start time of an event cannot be empty.");
                    } else if (eventTaskComponents.length > 2) {
                        ui.showError("The start time of an event cannot contain more than one date.");
                    }

                    String[] eventTaskDates = eventTaskComponents[1].split(" /to ");

                    if (eventTaskDates[1].equals("")) {
                        ui.showError("The end time of an event cannot be empty.");
                    } else if (eventTaskDates.length > 2) {
                        ui.showError("The end time of an event cannot contain more than one date.");
                    }

                    list.add(new Event(eventDescription, eventTaskDates[0], eventTaskDates[1]));
                    ui.eventMessage(list.get(list.size() - 1));
                    ui.taskListSizeMessage(list.size(), true);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.printDivider();
                }
            } else if (command.startsWith("delete")) {
                // Delete a task
                ui.printDivider();
                int taskNum = Integer.parseInt(command.substring(7));
                try {
                    ui.deleteMessage(list.get(taskNum - 1));
                    list.remove(taskNum - 1);
                    ui.taskListSizeMessage(list.size(), false);
                } catch (IndexOutOfBoundsException e) {
                    ui.showError("You have no such task, mortal.");
                } finally {
                    ui.printDivider();
                }
            } else {
                // Error message (unknown command)
                ui.printDivider();
                ui.showError("Do not test my patience, mortal. Speak clearly.");
                ui.printDivider();
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

        ui.endMessage();
    }
}
