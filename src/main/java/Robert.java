import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Robert {
	private static ArrayList<Task> tasks = new ArrayList<>();
	private static final Path tasksFilePath = Paths.get(System.getProperty("user.dir"),"src", "data", "tasks.txt");
	private static final File tasksFile = new File(Robert.tasksFilePath.toString());

    private static void greetUser() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        Robert.outputMessage(logo + text);
    }

	private static void downloadTasks() throws IOException {

		if (Robert.tasksFile.exists()) {
			Scanner scanner = new Scanner(Robert.tasksFile);

			while (scanner.hasNext()) {
				String[] taskParameters = scanner.nextLine().split(" \\| ");

				try {
					switch (taskParameters[0]) {
					case "T":
						ToDo newToDo = new ToDo(taskParameters[2], taskParameters[1].equals("1"));
						Robert.addTask(newToDo);
						break;

					case "E":
						Event newEvent = new Event(taskParameters[2],
								taskParameters[3], taskParameters[4], taskParameters[1].equals("1"));
						Robert.addTask(newEvent);
						break;

					case "D":
						Deadline newDeadline = new Deadline(taskParameters[2],
								taskParameters[3], taskParameters[1].equals("1"));
						Robert.addTask(newDeadline);
						break;

					default:
						throw new RobertException("An unknown task type was identified when parsing previously stored tasks.");
					}

				} catch (RobertException e) {
					Robert.resetTasks();
					Robert.outputMessage("WARNING: Your previously stored tasks seems to be corrupted.\n"
								+ "Reason: " + e + "\n"
								+ "Your lists of tasks will now be cleared. Apologies!");

				} catch (ArrayIndexOutOfBoundsException e) {
					Robert.resetTasks();
					Robert.outputMessage("WARNING: Your previously stored tasks seems to be corrupted.\n"
								+ "Reason: Number of parameters stored previously were incorrect.\n"
								+ "Your lists of tasks will now be cleared. Apologies!");

				}
			}
			scanner.close();
		}

		if (!Robert.tasksFile.delete()) {
			Robert.tasksFile.getParentFile().mkdirs();
		}
		Robert.tasksFile.createNewFile();
		Robert.uploadTasks();
	}

    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        boolean isUnderExecution = true;

        String[] userInput;
        while (isUnderExecution) {
            userInput = scanner.nextLine().split(" ", 2);

            String classification = userInput[0]; // First word indicates the type of action
			String parameters; // Second word onwards are used as parameters for commands
            
			try {
				switch (classification) {
				case "list":
					if (userInput.length > 1) {
						throw new RobertException("Unnecessary parameters added.\n"
								+ "Type 'list' if you intend to list your tasks.");
					}
					Robert.listTasks();
					break;
					
				case "bye":
					isUnderExecution = false;
					break;

				case "mark":
					if (userInput.length == 1) {
						throw new RobertException("The index used to mark a task cannot be empty.\n"
								+ "Please add an index.");
					}

					parameters = userInput[1];
					int markingTaskIndex = Integer.parseInt(parameters) - 1;
					if (markingTaskIndex < 0 || Task.taskCount <= markingTaskIndex) {
						throw new RobertException("Index is out of bounds.\n"
								+ "Please choose a valid index.");
					}

					Robert.markTask(markingTaskIndex);
        			Robert.outputMessage("Nice! I've marked this task as done:\n  " + Robert.tasks.get(markingTaskIndex));
					break;

				case "unmark":
					if (userInput.length == 1) {
						throw new RobertException("The index used to unmark a task cannot be empty.\n"
								+ "Please add an index.");
					}

					parameters = userInput[1];
					int unmarkingTaskIndex = Integer.parseInt(parameters) - 1;

					if (unmarkingTaskIndex < 0 || Task.taskCount <= unmarkingTaskIndex) {
						throw new RobertException("Index is out of bounds.\n"
								+ "Please choose a valid index.");
					}
					Robert.unmarkTask(unmarkingTaskIndex);
					Robert.outputMessage("Ok, I've marked this task as not done yet:\n  " + Robert.tasks.get(unmarkingTaskIndex));
					break;
				
				case "todo":
					if (userInput.length == 1) {
						throw new RobertException("The description of a todo cannot be empty.\n"
								+ "Please add a description to your todo task.");
					}
					ToDo newToDo = new ToDo(userInput[1]);
					Robert.addTask(newToDo);
					Robert.outputMessage("Got it. I have added this task:\n  " + newToDo
							+ "\nNow you have " + Task.taskCount + " " + (Task.taskCount > 1 ? "task" : "tasks") + " in the list.");
					break;

				case "event":
					if (userInput.length == 1) {
						throw new RobertException("The description of an event cannot be empty.\n"
								+ "Please add a description to your event task.");
					}

					parameters = userInput[1];

					if (!parameters.contains("/from") || !parameters.contains("/to")) {
						throw new RobertException("The event's start and/or end time is/are not specified properly.\n"
								+ "Please make sure '/from' and '/to' are properly indicated.");
					}

					String[] eventParameters = parameters.split(" /from ");

					String eventDescription = eventParameters[0];
					String from = eventParameters[1].split(" /to ")[0];
					String to = eventParameters[1].split(" /to ")[1];

					Event newEvent = new Event(eventDescription, from, to);
					Robert.addTask(newEvent);
					Robert.outputMessage("Got it. I have added this task:\n  " + newEvent
							+ "\nNow you have " + Task.taskCount + " " + (Task.taskCount > 1 ? "task" : "tasks") + " in the list.");
					break;

				case "deadline":
					if (userInput.length == 1) {
						throw new RobertException("The description of a deadline cannot be empty.\n"
								+ "Please add a description to your deadline task.");
					}

					parameters = userInput[1];
					
					if (!parameters.contains("/by")) {
						throw new RobertException("The deadline's due date is not specified properly.\n"
								+ "Please make sure '/by' is properly indicated.");
					}

					String[] deadlineParameters = parameters.split(" /by ");

					String deadlineDescription = deadlineParameters[0];
					String by = deadlineParameters[1];

					Deadline newDeadline = new Deadline(deadlineDescription, by);
					Robert.addTask(newDeadline);
					Robert.outputMessage("Got it. I have added this task:\n  " + newDeadline
							+ "\nNow you have " + Task.taskCount + " " + (Task.taskCount > 1 ? "task" : "tasks") + " in the list.");
					break;    

				case "delete":
					if (userInput.length == 1) {
						throw new RobertException("The index used to delete a task cannot be empty.\n"
								+ "Please add an index.");
					}

					parameters = userInput[1];
					int deletingTaskIndex = Integer.parseInt(parameters) - 1;

					if (deletingTaskIndex < 0 || Task.taskCount <= deletingTaskIndex) {
						throw new RobertException("Index is out of bounds.\n"
								+ "Please choose a valid index.");
					}

					Task deletedTask = Robert.deleteTask(deletingTaskIndex);
					Robert.outputMessage("Noted. I've removed this task:\n  " + deletedTask
							+ "\nNow you have " + Task.taskCount + " " + (Task.taskCount > 1 ? "task" : "tasks") + " in the list.");
					break;

				default:
					throw new RobertException("I'm sorry, but I don't know what that means :-(");
				}

			} catch (RobertException e) {
				Robert.outputMessage(e.toString());

			} catch (NumberFormatException e) {
				Robert.outputMessage("Cannot convert given index as integer. Please use proper integer as the index!");
			}
        }

        scanner.close();
    }

    private static void listTasks() {
		if (Task.taskCount == 0) {
			Robert.outputMessage("You do not have any tasks stored. Add one!");
			return;
		}

		int taskIndex = 1;
		StringBuilder taskListing = new StringBuilder("Here are the tasks in your list:\n");
		for (Task task : Robert.tasks) {
			taskListing.append(String.format("%d. %s\n", taskIndex, task));
			taskIndex++;
		}
		Robert.outputMessage(taskListing.toString());
    }

    private static void addTask(Task task) {
        Robert.tasks.add(task);
		Task.taskCount++;
		Robert.uploadTasks();
	}

    private static Task deleteTask(int taskIndex) {
		Task removedTask = Robert.tasks.get(taskIndex);
        Robert.tasks.remove(taskIndex);
		Task.taskCount--;
		Robert.uploadTasks();
		return removedTask;
    }	

    private static void markTask(int taskIndex) {
        Robert.tasks.get(taskIndex).markAsDone();
		Robert.uploadTasks();
	}

    private static void unmarkTask(int taskIndex) {
        Robert.tasks.get(taskIndex).markAsUndone();
		Robert.uploadTasks();
	}

    private static void exitChatbot() {
        Robert.outputMessage("Goodbye. Hope to see you again soon!");
    }

    private static void outputMessage(String message) {
        String output = Robert.formatOutput(message);
        System.out.println(output);
    }

	private static void uploadTasks() {
		try {
			FileWriter fw = new FileWriter(Robert.tasksFilePath.toString(), false);
			for (Task task : Robert.tasks) {
				String storedLine;
				String taskDone = task.isDone ? "1" : "0";

				if (task instanceof ToDo) {
					storedLine = "T | "
							+ taskDone + " | "
							+ task.description;

				} else if (task instanceof Event) {
					storedLine = "T | "
							+ taskDone + " | "
							+ task.description + " | "
							+ ((Event) task).from + " | "
							+ ((Event) task).to;

				} else {
					storedLine = "T | "
							+ taskDone + " | "
							+ task.description + " | "
							+ ((Deadline) task).by;
				}

				fw.write(storedLine + "\n");
			}

			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private static void resetTasks() {
		Robert.tasks = new ArrayList<>();
		Task.taskCount = 0;
	}

    private static String formatOutput(String output) {
        final String HORIZONTAL_LINE = "\t____________________________________________________________\n";

        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        String indentedOutput = String.join("", outputLines);
        return HORIZONTAL_LINE + indentedOutput + HORIZONTAL_LINE;
	}

    public static void main(String[] args) {
		try {
			Robert.downloadTasks();
		} catch (IOException e) {
			System.out.println(e);
		}
		Robert.greetUser();
        Robert.runChatbot();
        Robert.exitChatbot();
    }
}
