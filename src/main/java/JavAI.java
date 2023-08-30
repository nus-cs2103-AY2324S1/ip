import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * JavAI is a simple chatbot that allows users to add, mark, unmark, delete,
 * and list tasks.
 */
public class JavAI {

    public static ArrayList<Task> taskListReader() {

        File file = new File("./src/main/txtFolder/JavAI.txt");
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        else {

            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String load = sc.nextLine();
                    String taskType = load.substring(1, 2);
                    String completionType = load.substring(4, 5);
                    String description = "";
                    String[] words = load.substring(7).split(" ");
                    int iterator = 0;

                    if (taskType.equals("T")) {
                        while (iterator < words.length) {
                            description += words[iterator] + " ";
                            iterator++;
                        }
                        Todo todo = new Todo(description);
                        if (completionType.equals("X")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (taskType.equals("D")) {
                        String endDate = "";
                        String endTime = "";
                        while (!words[iterator].equals("by:")) {
                            if (!words[iterator].equals("(")) {
                                description += words[iterator] + " ";
                                iterator++;
                            } else {
                                iterator++;
                            }
                        }
                        iterator++;
                        for (int i = 0; i < 3; i++) {
                            endDate += words[iterator] + " ";
                            iterator++;
                        }
                        endTime = words[iterator];
                        String endTimeWithoutColon = endTime.replace(":", "");
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy ");
                        LocalDate date = LocalDate.parse(endDate, inputFormatter);
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = date.format(outputFormatter);
                        Deadline deadline = new Deadline(description.trim(), formattedDate, endTimeWithoutColon);
                        if (completionType.equals("X")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else if (taskType.equals("E")) {
                        String from = "";
                        String to = "";
                        while (!words[iterator].equals("from:")) {
                            if (!words[iterator].equals("(")) {
                                description += words[iterator] + " ";
                                iterator++;
                            } else {
                                iterator++;
                            }
                        }
                        iterator++;
                        while (!words[iterator].equals("to:")) {
                            from += words[iterator] + " ";
                            iterator++;
                        }
                        iterator++;
                        while (iterator < words.length) {
                            if (words[iterator].equals(")")) {
                                iterator++;
                            } else {
                                to += words[iterator] + " ";
                                iterator++;
                            }
                        }
                        Event event = new Event(description.trim(), from, to);
                        if (completionType.equals("X")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
        return tasks;
    }

    public static void taskListWriter(ArrayList<Task> tasks) {

        File file = new File("./src/main/txtFolder/JavAI.txt");

        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * The main method that starts the JavAI chatbot.
     *
     * @param args Command-line arguments.
     * @throws JavAIException If there's an exception in the JavAI program.
     * @throws Exception If there's an exception in the JavAI program.
     */
    public static void main(String[] args) throws JavAIException, Exception {

        ArrayList<Task> arr = taskListReader();
        int counter = arr.size();
        Scanner sc = new Scanner(System.in);
        String line = "     ____________________________________________________________";

        System.out.println(line + "\n      Hello, I'm JavAI.\n      What can I do for you?\n" + line);
        String output = sc.nextLine();

        while (!output.equals("bye")) {
            String[] words = output.split(" ");
            String description = "";
            int iterator = 1;

            if (words[0].equals("todo")) {
                try {
                    while (iterator < words.length) {
                        description += words[iterator] + " ";
                        iterator++;
                    }
                    Todo todo = new Todo(description);
                    if (description.equals("")) {
                        throw new JavAIException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    arr.add(todo);
                    System.out.println(line + "\n      Got it. I've added this task:\n" +
                            "       " + arr.get(counter) +
                            "\n      Now you have " + (counter + 1) + " task(s) in the list.\n" + line);
                    counter++;
                } catch (JavAIException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("deadline")) {
                String endDate = "";
                String endTime = "";
                try {
                    if(!output.contains("/by")) {
                        throw new JavAIException("☹ OOPS!!! Please input a valid deadline using '/by'.");
                    }
                    while (!words[iterator].equals("/by")) {
                        description += words[iterator] + " ";
                        iterator++;
                    }
                    iterator++;
                    if (iterator + 1 > words.length) {
                        throw new JavAIException("☹ OOPS!!! Please specify both date and time in following " +
                                "format after after /by: yyyy-mm-dd hh:mm'.");
                    }
                    endDate = words[iterator];
                    endTime = words[iterator + 1];
                    Deadline deadline = new Deadline(description, endDate, endTime);
                    arr.add(deadline);
                    System.out.println(line + "\n      Got it. I've added this task:\n" +
                            "       " + arr.get(counter) +
                            "\n      Now you have " + (counter + 1) + " task(s) in the list.\n" + line);
                    counter++;
                } catch (JavAIException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("event")) {
                try {
                    if(!output.contains("/from") || !output.contains("/to")) {
                        throw new JavAIException("☹ OOPS!!! Please input a valid event using '/from' and '/to'.");
                    }
                    String from = "";
                    String to = "";
                    while (!words[iterator].equals("/from")) {
                        description += words[iterator] + " ";
                        iterator++;
                    }
                    iterator++;
                    while (!words[iterator].equals("/to")) {
                        from += words[iterator] + " ";
                        iterator++;
                    }
                    iterator++;
                    while (iterator < words.length) {
                        to += words[iterator] + " ";
                        iterator++;
                    }
                    Event event = new Event(description, from, to);
                    arr.add(event);
                    System.out.println(line + "\n      Got it. I've added this task:\n" +
                            "       " + arr.get(counter) +
                            "\n      Now you have " + (counter + 1) + " task(s) in the list.\n" + line);
                    counter++;
                } catch (JavAIException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("mark")) {
                try {

                    int iden = Integer.parseInt(words[1]) - 1;
                    arr.get(iden).markAsDone();
                    System.out.println(line + "\n" + "     " + "  Nice! I've marked this task as done:");
                    System.out.println("       " + arr.get(iden).toString());
                    System.out.println(line);

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'mark'.");
                }
            } else if (words[0].equals("unmark")) {
                try {

                    int iden = Integer.parseInt(words[1]) - 1;
                    arr.get(iden).markAsUndone();
                    System.out.println(line + "\n" + "     " + "  OK, I've marked this task as not done yet:");
                    System.out.println("       " + arr.get(iden).toString());
                    System.out.println(line);

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numeric value after 'unmark'.");
                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'unmark'.");
                }

            } else if (words[0].equals("delete")) {
                try {
                    System.out.println(line + "\n" + "     " + "  Noted. I've removed this task:\n" +
                            "       " + arr.get(Integer.parseInt(words[1]) - 1).toString() +
                            "\n       Now you have " + (counter - 1) + " task(s) in the list.\n" + line);
                    arr.remove(Integer.parseInt(words[1]) - 1);
                    counter--;

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please input a valid numerical value after 'delete'.");
                }

            } else if (output.equals("list")) {
                System.out.println(line);
                System.out.println("      " + "Here are the tasks in your list:");
                for ( int i = 0; i < counter ; i++ ) {
                    System.out.println("      " + (i+1) + "." + arr.get(i).toString());
                }
                System.out.println(line);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            output = sc.nextLine();
        }
        taskListWriter(arr);
        System.out.println(line + "\n" + "      Bye. Hope to see you again soon!\n" + line);

    }
}