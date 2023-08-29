import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The main class for Duke Chatbot
 */
public class Duke {
    /** Variable to show horizontal lines */
    public static final String LINE_BREAK = ("--------------------------------------------------"
            + "---------------------------------");
	
	/**
	 * Manipulates the data of existing tasks
	 * Includes marking, unmarking, and deleting tasks
	 * 
	 * @param command Command to be executed
	 * @param list List of tasks where task data are to be changed
	 * @param beginIndex Beginning index of the command description excluding the command itself
	 * @throws InvalidTaskNumberException
	 */
	public static void manipulateTasks(String fullInput, String command, ArrayList<Task> list, int beginIndex) throws InvalidTaskNumberException {
		int taskNum = Integer.parseInt(fullInput.substring(beginIndex));
		if (taskNum > list.size() || taskNum < 1) {
			throw new InvalidTaskNumberException("Task number is out of bounds, please try again");
		}
		switch (command) {
        case "mark":
            list.get(taskNum - 1).markAsDone();
            break;
        case "unmark":
            list.get(taskNum - 1).markAsNotDone();
            break;
        case "delete":
            Task.deleteTask(list.get(taskNum - 1), list);
            break;
		}
	}

    public static void overwriteTasksData(File file, ArrayList<Task> list) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    public static ArrayList<Task> loadTasksData(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Task> list = (ArrayList<Task>) ois.readObject();
        ois.close();
        return list;
    }

	public static String formatDate(String strDeadline) throws DateTimeParseException {
		LocalDate dateDeadline = LocalDate.parse(strDeadline);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
		return dateDeadline.format(formatter);
	}

    public static void main(String[] args) {
        try {
            File tasks = new File("./tasks.txt");
            Scanner sc = new Scanner(System.in);
            ArrayList<Task> list;
            if (tasks.createNewFile()) {
                list = new ArrayList<Task>();
            } else {
                list = loadTasksData(tasks);
            }

            System.out.println("\n");
            System.out.println(LINE_BREAK);
            System.out.println("Welcome. My name is Duke");
            System.out.println("What will you do today?");

            while (true) {
                try {
                    System.out.println(LINE_BREAK);
                    System.out.println("\n");
                    String input = sc.nextLine().trim();
                    System.out.println(LINE_BREAK);
                    String[] split = input.split(" ");
                    if (split[0].toLowerCase().equals("list")) {
                        if (split.length > 1) {
                            throw new InvalidCommandSyntaxException("'list' command must not be followed by anything");
                        }
                        if (list.size() == 0) {
                            System.out.println("List is empty");
                        } else {
                            for (int i = 1; i < list.size() + 1; i++) {
                                Task current = list.get(i - 1);
                                System.out.println(i + ". " + current.convertToString());
                            }
                        }
                    } else if (split[0].toLowerCase().equals("mark")
                            || split[0].toLowerCase().equals("unmark")
                            || split[0].toLowerCase().equals("delete")) {
                        String command = split[0].toLowerCase();
                        if (split.length == 1) {
                            throw new MissingTaskNumberException("Task number cannot be empty");
                        } else if (list.size() < 1) {
                            throw new EmptyListException("List is currently empty");
                        }
                        switch (command) {
                        case "mark":
                            manipulateTasks(input, "mark", list, 5);
                            break;
                        case "unmark":
                            manipulateTasks(input, "unmark", list, 7);
                            break;
                        case "delete":
                            manipulateTasks(input,  "delete", list, 7);
                            break;
                        }
                        overwriteTasksData(tasks, list);
                    } else if (split[0].toLowerCase().equals("todo")) {
                        if (input.trim().length() <= 4) {
                            throw new MissingTaskDescriptionException("Todo task description cannot be empty");
                        }
                        String taskName = input.substring(5);
                        ToDo.printTaskAdded(taskName, list);
                        ToDo task = new ToDo(taskName);
                        list.add(task);
                        overwriteTasksData(tasks, list);
                    } else if (split[0].toLowerCase().equals("deadline")) {
                        if (input.trim().length() <= 8) {
                            throw new MissingTaskDescriptionException("Deadline task description cannot be empty");
                        }
                        String[] taskDesc = input.substring(9).split("/by");
                        if (taskDesc.length < 2) {
                            throw new InvalidTaskTimeException("Deadline task must have exactly one /by deadline");
                        }
                        String taskName = taskDesc[0].trim();
                        if (taskName.length() == 0) {
                            throw new MissingTaskNameException("Deadline task name cannot be empty");
                        }
                        String strDeadline = taskDesc[1].trim();
						String deadline = formatDate(strDeadline);
                        Deadline.printTaskAdded(taskName, deadline, list);
                        list.add(new Deadline(taskName, deadline));
                        overwriteTasksData(tasks, list);
                    } else if (split[0].toLowerCase().equals("event")) {
                        if (input.trim().length() <= 5) {
                            throw new MissingTaskDescriptionException("Event task description cannot be empty");
                        }
                        String[] taskDesc = input.substring(6).split("/from");
                        if (taskDesc.length < 2 || taskDesc.length > 2) {
                            throw new InvalidTaskTimeException(
                                    "Event task must have exactly one /from and one /to times, in that order");
                        }
                        String taskName = taskDesc[0].trim();
                        String[] fromAndTo = taskDesc[1].split("/to");
                        if (fromAndTo.length < 2 || fromAndTo.length > 2) {
                            throw new InvalidTaskTimeException(
                                    "Event task must have exactly one /from and one /to times, in that order");
                        }
                        if (taskName.length() == 0) {
                            throw new MissingTaskNameException("Event task name cannot be empty");
                        }
                        String strStart = fromAndTo[0].trim();
                        String strEnd = fromAndTo[1].trim();
						String start = formatDate(strStart);
						String end = formatDate(strEnd);
                        Event.printTaskAdded(taskName, start, end, list);
                        list.add(new Event(taskName, start, end));
                        overwriteTasksData(tasks, list);
                    } else if (split[0].toLowerCase().equals("bye")) {
                        if (split.length > 1) {
                            throw new InvalidCommandSyntaxException("'bye' command must not be followed by anything");
                        }
                        System.out.println("I hope you enjoy my service. Thank you and goodbye");
                        System.out.println(LINE_BREAK);
						overwriteTasksData(tasks, list);
                        break;
                    } else {
                        throw new InvalidCommandException("No such command exists, please try again");
                    }
                } catch (InvalidCommandException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (InvalidCommandSyntaxException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (MissingTaskDescriptionException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (MissingTaskNameException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (InvalidTaskTimeException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (InvalidTaskNumberException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (MissingTaskNumberException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (EmptyListException exception) {
                    System.out.println("!ERROR! " + exception);
                } catch (DateTimeParseException exception) {
					System.out.println("!ERROR! Please input date with format yyyy-mm-dd!");
				}
            }

            // Close the scanner
            sc.close();
        } catch (IOException exception) {
            System.out.println("File missing or corrupted");
        } catch (ClassNotFoundException exception) {
			System.out.println("Class is not found");
		}
    }
}
