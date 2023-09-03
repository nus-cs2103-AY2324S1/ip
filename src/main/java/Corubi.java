                import java.io.*;
                import java.util.ArrayList;
                import java.util.Collections;
                import java.util.Scanner;
                import java.time.LocalDate;
                import java.time.format.DateTimeFormatter;
                

                public class Corubi {
                    public static void main(String[] args) throws IOException {
                        // Setting of final parameters
                        final String name = "Corubi";
                        final String divider = "---------------------------------------------------";

                        // Array of Tasks that user has entered
                        ArrayList<Task> enteredText = new ArrayList();

                        try (Scanner fileScanner = new Scanner(new File("./src/main/java/OUTPUT.txt"))) {
                            System.out.println("Your leftover tasks are:");
                            int i = 0;
                            while (fileScanner.hasNextLine()) {
                                i++;
                                String taskInfo = fileScanner.nextLine();
                                boolean isDone = taskInfo.contains("[X] ");
                                if (taskInfo.contains("[T] ")) {
                                    String taskName = taskInfo.split("[T] ")[0].split("] ")[2].split(" \\(")[0] + " ";
                                    Task newTask = new ToDos(taskName, isDone);
                                    enteredText.add(newTask);
                                    System.out.println(i + ". "+ newTask);
                                } else if (taskInfo.contains("[E]")) {
                                    String taskName = taskInfo.split("[E] ")[0].split("] ")[2].split(" \\(")[0] + " ";

                                    // Extract the "from" value
                                    String fromPrefix = "from : ";
                                    int fromIndex = taskInfo.indexOf(fromPrefix) + fromPrefix.length();
                                    int toIndex = taskInfo.indexOf(" to:");
                                    String taskFromInput = taskInfo.substring(fromIndex, toIndex);

                                    // Parse the From input string to a LocalDate object
                                    LocalDate from = LocalDate.parse(taskFromInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

                                    // Format the LocalDate object to the desired output format
                                    String taskFrom = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


                                    // Extract the "to" value
                                    String toPrefix = "to: ";
                                    int toValueIndex = taskInfo.indexOf(toPrefix) + toPrefix.length();
                                    String taskToInput = taskInfo.substring(toValueIndex, toValueIndex + "MMM d yyyy".length() + 1);

                                    // Parse the To input string to a LocalDate object
                                    LocalDate to= LocalDate.parse(taskFromInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

                                    // Format the LocalDate object to the desired output format
                                    String taskTo = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                                    Task newTask = new Events(taskName, taskFrom, taskTo, isDone);
                                    enteredText.add(newTask);
                                    System.out.println(i + ". "+ newTask);
                                } else if (taskInfo.contains("[D]")) {
                                    String taskName = taskInfo.split("[D] ")[0].split("] ")[2].split(" \\(")[0] + " ";
                                    String byPrefix = "by : ";
                                    int byIndex = taskInfo.indexOf(byPrefix) + byPrefix.length();
                                    String taskByInput = taskInfo.substring(byIndex, byIndex + "MMM d yyyy".length() + 1);

                                    // Parse the By string to a LocalDate object
                                    LocalDate date = LocalDate.parse(taskByInput, DateTimeFormatter.ofPattern("MMM d yyyy"));

                                    // Format the LocalDate object to the desired output format
                                    String taskBy = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                                    Task newTask = new Deadlines(taskName, taskBy, isDone);
                                    enteredText.add(newTask);
                                    System.out.println(i + ". "+ newTask);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("\nERROR: OUTPUT.txt file is not found in directory ./src/main/java/OUTPUT.txt!\n" +
                                    "Creating OUTPUT.txt in the given directory now.");
                        } finally {
                            // Instance of PrintWriter to write new outputs to the file
                            FileWriter pw = new FileWriter("./src/main/java/OUTPUT.txt", true);

                            // Initiate the bot greeting
                            Scanner sc = new Scanner(System.in);
                            System.out.println(divider);
                            System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
                            System.out.println(divider);

                            // Allow user input
                            String input = sc.nextLine();

                            // List of accpeted commands
                            ArrayList<String> commands = new ArrayList<>();
                            String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
                            Collections.addAll(commands, commandList);

                            // Exit the chatbot if the user says "bye"
                            while (!input.equals("bye") && !input.equals("Bye")) {

                                // If input is "list" command, show the list.
                                if (input.equals("list") || input.equals("List")) {
                                    for (int i = 0; i < enteredText.size(); i++) {
                                        System.out.printf("%d. %s \n", i + 1, enteredText.get(i).toString());
                                    }
                                    System.out.println(divider);
                                    input = sc.nextLine();
                                } else if (input.contains("unmark") || input.contains("Unmark")) {
                                    // If command is unmark, then unmark the item

                                    // Split the input by spaces
                                    String[] splitInput = input.split(" ");

                                    // Iterate through the parts to find the number
                                    for (String num : splitInput) {
                                        try {
                                            int number = Integer.parseInt(num);

                                            // Handle the exception if number provided is beyond the size of list
                                            try {
                                                enteredText.get(number - 1).unmarkDone();
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println(number + " is too high! List size is only " + enteredText.size());
                                            } finally {
                                                System.out.println(divider);
                                                FileWriter nw = new FileWriter("./src/main/java/OUTPUT.txt");
                                                pw = nw;
                                                pw.flush();
                                                for (Task tasks : enteredText) {
                                                    pw.write(tasks.toString() + "\n");
                                                    pw.flush();
                                                }
                                                input = sc.nextLine();
                                                break; // Stop searching after first number is found
                                            }
                                        } catch (NumberFormatException e) {
                                            // Not a number, continue searching
                                        }
                                    }
                                } else if (input.contains("mark") || input.contains("Mark")) {
                                    // If the input contains the word mark, mark the item number as done

                                    // Split the input by spaces
                                    String[] splitInput = input.split(" ");

                                    // Iterate through the parts to find the number
                                    for (String num : splitInput) {
                                        try {
                                            int number = Integer.parseInt(num);

                                            // Handle the exception if number provided is beyond the size of list
                                            try {
                                                enteredText.get(number - 1).markDone();
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println(number + " is too high! List size is only " + enteredText.size());
                                            } finally {
                                                System.out.println(divider);
                                                FileWriter nw = new FileWriter("./src/main/java/OUTPUT.txt");
                                                pw = nw;
                                                pw.flush();
                                                for (Task tasks : enteredText) {
                                                    pw.write(tasks.toString() + "\n");
                                                    pw.flush();
                                                }
                                                input = sc.nextLine();
                                                break; // Stop searching after first number is found
                                            }
                                        } catch (NumberFormatException e) {
                                            // Not a number, continue searching
                                        }
                                    }
                                } else if (input.contains("delete")) {
                                    // The delete command

                                    String[] splitInput = input.split("delete ");

                                    for (String num : splitInput) {
                                        try {
                                            int number = Integer.parseInt(num);
                                            Task index;

                                            // Handle the exception if number provided is beyond the size of list
                                            try {
                                                index = enteredText.get(number - 1);
                                                enteredText.remove(index);
                                                System.out.printf("I have deleted the following task:\n" +
                                                        "%s\n" +
                                                        "Your list has %d items left\n\n", index.toString(), enteredText.size());
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println(number + " is too high! List size is only " + enteredText.size());
                                            }
                                            System.out.println(divider);
                                            FileWriter nw = new FileWriter("./src/main/java/OUTPUT.txt");
                                            pw = nw;
                                            pw.flush();
                                            for (Task tasks : enteredText) {
                                                pw.write(tasks.toString() + "\n");
                                                pw.flush();
                                            }
                                            input = sc.nextLine();
                                            break; // Stop searching after first number is found
                                        } catch (NumberFormatException e) {
                                            // Not a number, continue searching
                                        }
                                    }
                                } else {
                                    // Add the input to the list
                                    if (input.contains("todo ")) {
                                        input = input.split("todo ")[1];
                                        Task newTask = new ToDos(input, false);
                                        enteredText.add(newTask);
                                        System.out.println("Okay! I have added the following task\n" + newTask);
                                        pw.write(newTask.toString() + "\n");
                                        pw.flush();
                                    } else if (input.contains("deadline ")) {
                                        input = input.split("deadline ")[1];
                                        String[] splitString = input.split("/by");
                                        Task newTask = new Deadlines(splitString[0], splitString[1], false);
                                        enteredText.add(newTask);
                                        System.out.println("Okay! I have added the following task\n" + newTask);
                                        pw.write(newTask + "\n");
                                        pw.flush();
                                    } else if (input.contains("event ")) {
                                        input = input.split("event ")[1];
                                        String taskName = input.split("/from")[0];
                                        String to = input.split("/to")[1];
                                        String from = input.split(" /to ")[0].split("/from ")[1];
                                        Task newTask = new Events(taskName, from, to, false);
                                        enteredText.add(newTask);
                                        System.out.println("Okay! I have added the following task\n" + newTask.toString());
                                        pw.write(newTask.toString() + "\n");
                                        pw.flush();
                                    } else {
                                        // Check if input command is in the list of accepted commands
                                        try {
                                            if (!commands.contains(input.split(" ")[0])) {
                                                throw new WrongCommandException(input);
                                            }
                                        } catch (WrongCommandException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    System.out.println(divider);
                                    input = sc.nextLine();
                                }
                            }
                            System.out.println(input + " " + input + "...please come back soon :(");
                        }
                    }
                }
