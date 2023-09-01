import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Alex {
    public static void main(String[] args) {
        String horizontalLine = "_____________________________________________________________\n";
        String greeting = horizontalLine
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + horizontalLine;

        System.out.println(greeting);

        try {
            File userDataFile = new File("../data/Alex.txt");
            if (!userDataFile.getParentFile().exists()) {
                userDataFile.getParentFile().mkdir();
            }
            Scanner userDataScanner = new Scanner(userDataFile);

            while (userDataScanner.hasNextLine()) {
                String userData = userDataScanner.nextLine();
                userData = userData.stripTrailing();
                String taskType = userData.substring(0, 1);
                int dataLength = userData.length();
                Task taskToBeStored = new Task("temp");
                int isDoneSymbol = Integer.parseInt(userData.substring(dataLength - 1, dataLength));
                boolean isDone = isDoneSymbol == 1 ? true : false;
                if (!userData.substring(1, 2).equals(" ") || (isDoneSymbol != 1 && isDoneSymbol != 0)) {
                    throw new AlexException("");
                }

                if (taskType.equals("T")) {
                    try {
                        String description = userData.substring(2, dataLength - 2);
                        taskToBeStored = new ToDos(description);
                        UserInputStorage.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (UserInputStorage.getNumOfElement() + 1)
                                + "is wrong" + "\n"
                                + "The correct data format for a todo task should be: \n"
                                + "   "
                                + "T (description) (1/0) where 1 indicates done and 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else if (taskType.equals("D")) {
                    try {
                        String descriptionWithTime = userData.substring(0, dataLength - 2);
                        String regex = "\\b /by \\b";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(descriptionWithTime);
                        if (!matcher.find()) {
                            throw new AlexException("");
                        }
                        int startIndex = matcher.start();
                        int endIndex = matcher.end();
                        String description = startIndex > 2 ? userData.substring(2, startIndex) : "";
                        String by = userData.substring(endIndex, dataLength - 2);
                        taskToBeStored = new Deadline(description, by);
                        UserInputStorage.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (UserInputStorage.getNumOfElement() + 1)
                                + " is wrong" + "\n"
                                + "The correct data format for a deadline task should be: \n"
                                + "   "
                                + "D (description) /by yyyy-mm-dd (1/0) where 1 indicates done and 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else if (taskType.equals("E")) {
                    try {
                        String descriptionWithTime = userData.substring(0, dataLength - 2);
                        String regex = "\\b /from \\b";
                        Pattern pattern1 = Pattern.compile(regex);
                        Matcher matcher1 = pattern1.matcher(descriptionWithTime);
                        if (!matcher1.find()) {
                            throw new AlexException("");
                        }
                        int firstStart = matcher1.start();
                        int firstEnd = matcher1.end();

                        String regex2 = "\\b /to \\b";
                        Pattern pattern2 = Pattern.compile(regex2);
                        Matcher matcher2 = pattern2.matcher(descriptionWithTime);
                        if (!matcher2.find()) {
                            throw new AlexException("");
                        }
                        int secondStart = matcher2.start();
                        int secondEnd = matcher2.end();

                        String description = firstStart > 2 ? userData.substring(2, firstStart) : "";
                        String fromTime = userData.substring(firstEnd, secondStart);
                        String toTime = userData.substring(secondEnd, dataLength - 2);

                        taskToBeStored = new Event(description, fromTime, toTime);
                        UserInputStorage.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (UserInputStorage.getNumOfElement() + 1)
                                + "is wrong" + "\n"
                                + "The correct data format for an event task should be: \n"
                                + "   "
                                + "E (description) /from yyyy-mm-dd /to yyyy-mm-dd (1/0) " +
                                "where 1 indicates done and 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else {
                    throw new AlexException("");
                }

                if (isDone) {
                    taskToBeStored.mark(false);
                }
            }

        } catch(FileNotFoundException e) {
            File userDataFile = new File("../data/Alex.txt");
            try {
                userDataFile.createNewFile();
            } catch (IOException e2) {
                System.err.println("The file Alex.txt does not exits and there is an error creating the file: "
                        + e2.getMessage());
                System.exit(0);
            }
        } catch (AlexException | NumberFormatException | IndexOutOfBoundsException e) {
            String message = "OOPS!!! The program terminates because the data format at line "
                    + (UserInputStorage.getNumOfElement() + 1)
                    + "is wrong" +"\n"
                    + "The correct data format for a todo task should be: \n"
                    + "   "
                    + "T (description) (1/0) where 1 indicates done and 0 indicates undone\n"
                    + "The correct data format for a deadline task should be: \n"
                    + "   "
                    + "D (description) /by yyyy-mm-dd (1/0) where 1 indicates done and 0 indicates undone\n"
                    + "The correct data format for a event task should be: \n"
                    + "   "
                    + "E (description) /from yyyy-mm-dd /to yyyy-mm-dd (1/0)" +
                    "where 1 indicates done and 0 indicates undone";
            System.out.println(message);
            System.exit(0);
        }


        Scanner userInputScanner = new Scanner(System.in);
        while (true) {
            String userInput = userInputScanner.nextLine();
            String userInputStripped = userInput.stripTrailing();
            int inputLength = userInput.length();
            if (userInput.equals("bye") || userInputStripped.equals("bye")) {
                String bye = horizontalLine
                        + "Bye! Hope to see you again soon!!\n"
                        + horizontalLine;
                System.out.println(bye);
                UserInputStorage.storeToFile();
                break;
            } else if(userInput.equals("list") || userInputStripped.equals("list")) {
                UserInputStorage.printAllContent();
            } else if (inputLength >= 4 && userInput.substring(0, 4).equals("mark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5));
                    Task targetedTask = UserInputStorage.getTaskByIndex(index);
                    targetedTask.mark();
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    String message = "To mark certain task to be done, please use the following format:\n"
                                   + "   " + "mark (task number in non-negative integer)\n";
                    System.out.println(horizontalLine
                                     + message
                                     + horizontalLine
                    );
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                                     + e.getMessage() + "\n"
                                     + horizontalLine
                    );
                }
            } else if (inputLength >= 6 && userInput.substring(0, 6).equals("unmark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7));
                    Task targetedTask = UserInputStorage.getTaskByIndex(index);
                    targetedTask.unmark();
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    String message = "To unmark certain task to be undone, please use the following format:\n"
                            + "   " + "unmark (task number in non-negative integer)\n";
                    System.out.println(horizontalLine
                            + message
                            + horizontalLine
                    );
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                            + e.getMessage() + "\n"
                            + horizontalLine
                    );
                }
            } else if (inputLength >= 6 && userInput.substring(0, 6).equals("delete")) {
                try {
                    int toDeleteIndex = Integer.parseInt(userInput.substring(7, 8));
                    UserInputStorage.delete(toDeleteIndex);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    String message = "To delete a task, please use the following format:\n"
                            + "   " + "delete (task number in non-negative integer)\n";
                    System.out.println(horizontalLine
                            + message
                            + horizontalLine
                    );
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                            + e.getMessage() + "\n"
                            + horizontalLine
                    );
                }
            } else if (inputLength >= 4 && userInput.substring(0, 4).equals("todo")) {
                try {
                    if (!userInput.substring(4, 5).equals(" ")) {
                        String message = "OOPS!!! Please enter a todo task in the following format:\n"
                                       + "   " + "todo (description)";
                        throw new AlexException(message);
                    }
                    Task todo = new ToDos(userInput.substring(5));
                    UserInputStorage.store(todo);
                } catch (IndexOutOfBoundsException e) {
                    String message = "OOPS!!! Please enter a todo task in the following format:\n"
                            + "   " + "todo (description)";
                    System.out.println(horizontalLine
                                     + message + "\n"
                                     + horizontalLine
                    );
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                                     + e.getMessage() + "\n"
                                     + horizontalLine
                    );
                }
            } else if (inputLength >= 8 && userInput.substring(0, 8).equals("deadline")) {
                try {
                    String regex = "\\b /by \\b";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userInput);
                    if (!matcher.find()) {
                        String message = "Please enter a deadline task in the format: deadline (description) /by (time)";
                        throw new AlexException(message);
                    }
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    String description = startIndex > 9 ? userInput.substring(9, startIndex) : "";
                    String by = userInput.substring(endIndex);
                    Task deadline = new Deadline(description, by);
                    UserInputStorage.store(deadline);
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                                     + e.getMessage() + "\n"
                                     + horizontalLine
                    );
                }
            } else if (inputLength >= 5 && userInput.substring(0, 5).equals("event")) {
                try {
                    String regex = "\\b /from \\b";
                    Pattern pattern1 = Pattern.compile(regex);
                    Matcher matcher1 = pattern1.matcher(userInput);
                    if (!matcher1.find()) {
                        String message = "Please enter an event task in the format: "
                                        + "event (description) /from (from_time) /to (to_time)";
                        throw new AlexException(message);
                    }
                    int firstStart = matcher1.start();
                    int firstEnd = matcher1.end();

                    String regex2 = "\\b /to \\b";
                    Pattern pattern2 = Pattern.compile(regex2);
                    Matcher matcher2 = pattern2.matcher(userInput);
                    if (!matcher2.find()) {
                        String message = "Please enter an event task in the format: "
                                + "event (description) /from (from_time) /to (to_time)";
                        throw new AlexException(message);
                    }
                    int secondStart = matcher2.start();
                    int secondEnd = matcher2.end();

                    String description = firstStart > 6 ? userInput.substring(6, firstStart) : "";
                    String fromTime = userInput.substring(firstEnd, secondStart);
                    String toTime = userInput.substring(secondEnd);

                    Task event = new Event(description, fromTime, toTime);
                    UserInputStorage.store(event);
                } catch (AlexException e) {
                    System.out.println(horizontalLine
                                     + e.getMessage() + "\n"
                                     + horizontalLine
                    );
                }
            } else {
                String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
                System.out.println(horizontalLine
                                 + message
                                 + horizontalLine
                );
            }
        }
    }
}
