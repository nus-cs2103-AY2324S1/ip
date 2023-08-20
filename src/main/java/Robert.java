import java.util.Scanner;
import java.util.ArrayList;

public class Robert {
	private static ArrayList<Task> tasks = new ArrayList<>();

    private static void greetUser() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        String output = Robert.formatOutput(logo + text);
        System.out.println(output);
    }

    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        Boolean isUnderExecution = true;

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
					break;
				
				case "todo":
					if (userInput.length == 1) {
						throw new RobertException("The description of a todo cannot be empty.\n"
								+ "Please add a description to your todo task.");
					}
					ToDo newToDo = new ToDo(userInput[1]);
					Robert.addTask(newToDo);
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

					Robert.deleteTask(deletingTaskIndex);
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
		int taskIndex = 1;
        String taskListing = "Here are the tasks in your list:\n";
        for (Task task : Robert.tasks) {
            taskListing += String.format("%d. %s\n", taskIndex, task);
			taskIndex++;
        }
        Robert.outputMessage(taskListing);
    }

    private static void addTask(Task task) {
        Robert.tasks.add(task);
		Task.taskCount++;

        String taskPlurality = "task";
        if (Task.taskCount > 1) {
            taskPlurality = "tasks";
        }
        String text = "Got it. I have added this task:\n  " + task
                + "\nNow you have " + Task.taskCount + " " + taskPlurality + " in the list.";
        Robert.outputMessage(text);
    }

    private static void deleteTask(int taskIndex) {
		Task removedTask = Robert.tasks.get(taskIndex);
        Robert.tasks.remove(taskIndex);
		Task.taskCount--;

        String taskPlurality = "task";
        if (Task.taskCount > 1) {
            taskPlurality = "tasks";
        }
        String text = "Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + Task.taskCount + " " + taskPlurality + " in the list.";
        Robert.outputMessage(text);
    }	

    private static void markTask(int taskIndex) {
        Robert.tasks.get(taskIndex).markAsDone();
        String text = "Nice! I've marked this task as done:\n  " + Robert.tasks.get(taskIndex);
        Robert.outputMessage(text);
    }

    private static void unmarkTask(int taskIndex) {
        Robert.tasks.get(taskIndex).markAsUndone();
        String text = "Ok, I've marked this task as not done yet:\n  " + Robert.tasks.get(taskIndex);
        Robert.outputMessage(text);
    }

    private static void exitChatbot() {
        Robert.outputMessage("Goodbye. Hope to see you again soon!");
    }

    private static void outputMessage(String message) {
        String output = Robert.formatOutput(message);
        System.out.println(output);
    }

    private static String formatOutput(String output) {
        final String HORIZONTAL_LINE = "\t____________________________________________________________\n";

        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        String indentedOutput = String.join("", outputLines);
        String formattedOutput = HORIZONTAL_LINE + indentedOutput + HORIZONTAL_LINE;

        return formattedOutput;
    }

    public static void main(String[] args) {
        Robert.greetUser();
        Robert.runChatbot();
        Robert.exitChatbot();
    }
}
