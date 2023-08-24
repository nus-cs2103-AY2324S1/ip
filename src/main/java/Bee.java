import java.util.Scanner;
public class Bee {
    private static TaskList listOfTasks = new TaskList();

    public static void main(String[] args) {
        String logo = "\n" +
                "__________\n" +
                "\\______   \\ ____   ____\n" +
                " |    |  _// __ \\_/ __ \\\n" +
                " |    |   \\  ___/\\  ___/\n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/\n";
        System.out.println("Hello! I'm" + logo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            try {
                // If user enters "bye", ends the program and says goodbye to the user.
                if (splitInput[0].equals("bye")) {
                    System.out.println("By-ee!. Hope to see you soon! ~Bzzz~");
                    break;
                } else if (splitInput[0].equals("list")) {
                    listOfTasks.listAllTasks();
                } else if (splitInput[0].equals("todo")) {
                    // Take the input after "todo "
                    try {
                        String editedInput = userInput.substring(5);
                        if (editedInput.isEmpty()) {
                            throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(editedInput);
                        listOfTasks.addTask(todo);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The description of a todo cannot be empty.");
                    }
                } else if (splitInput[0].equals("deadline")) {
                    // Take the input after "deadline "
                    try {
                        String editedInput = userInput.substring(9);
                        if (editedInput.isEmpty()) {
                            throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                        }
                        String[] splitEditedInput = editedInput.split(" /by ");
                        String deadlineDescription = splitEditedInput[0];
                        String deadlineDate = splitEditedInput[1];
                        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                        listOfTasks.addTask(deadlineTask);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The date of the deadline cannot be empty.");
                    }
                } else if (splitInput[0].equals("event")) {
                    // Take the input after "event "
                    try {
                        String editedInput = userInput.substring(6);
                        if (editedInput.isEmpty()) {
                            throw new BeeException("OOPS!! The description of an event cannot be empty.");
                        }
                        String[] splitEditedInput = editedInput.split(" /from ");
                        String[] splitEditedInput2 = splitEditedInput[1].split(" /to ");
                        String eventDescription = splitEditedInput[0];
                        String eventStartDate = splitEditedInput2[0];
                        String eventEndDate = splitEditedInput2[1];
                        Event event = new Event(eventDescription, eventStartDate, eventEndDate);
                        listOfTasks.addTask(event);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The description of an event cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The date of an event cannot be empty.");
                    }
                }
                else if (splitInput[0].startsWith("mark")) {
                    try {
                        int taskIndex = Integer.parseInt(splitInput[1]);
                        listOfTasks.setTaskDone(taskIndex);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The task number cannot be empty.");
                    } catch (NumberFormatException e) {
                        throw new BeeException("OOPS!! You must have entered an invalid task number.");
                    }
                } else if (splitInput[0].startsWith("unmark")) {
                    try {
                        int taskIndex = Integer.parseInt(splitInput[1]);
                        listOfTasks.setTaskNotDone(taskIndex);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new BeeException("OOPS!! The task number cannot be empty.");
                    } catch (NumberFormatException e) {
                        throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                    }
                } else if (splitInput[0].startsWith("delete")) {
                    try {
                        int taskIndex = Integer.parseInt(splitInput[1]);
                        listOfTasks.deleteTask(taskIndex);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new BeeException("OOPSS!! Please enter a task number");
                    } catch (NumberFormatException e) {
                        throw new BeeException("OOPSS!! You must have entered an invalid task number.");
                    }
                } else {
                    // Else, echo back the user input and add to list
                    listOfTasks.addTask(new Task(userInput));
                }
            } catch (BeeException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println("    _  _\n" +
                        "   | )/ )\n" +
                        "\\\\ |//,' __\n" +
                        "(\")(_)-\"()))=-\n" +
                        "   (\\\\ BZZZZZZZ!!!! Something went very wrong!!");
            }
        }
    }
}