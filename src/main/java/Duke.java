import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;  
import java.io.FileWriter;  
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;  


public class Duke {
    
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";
    


    /**
     * Handles commands and messages to ChatBot
     * @param args CLI args. No implementation.
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ChatBotList list = new ChatBotList();
        list.loadList();
        System.out.println(new File(DukeEnvironmentConstants.FILE_PATH));
        System.out.println(GREETINGS);
        while (true) {
            String input = s.nextLine().trim();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                String[] splitInput = input.split(" +", 2);
                if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") || splitInput[0].equals("delete")) {
                    if (splitInput.length == 1) {
                        System.out.println("☹ OOPS!!! You did not specify which task you want to " + splitInput[0] + ".\nPlease use this syntax:\n" +
                                splitInput[0] + " <index>");
                    } else {
                        try {
                            int idx = Integer.parseInt(splitInput[1]);
                            if (splitInput[0].equals("mark")) {
                                String res = list.markItem(idx);
                                System.out.println("Nice! I've marked this task as done:\n" + "  " + res);
                            } else if (splitInput[0].equals("unmark")) {
                                String res = list.unmarkItem(idx);
                                System.out.println("OK, I've marked this task as not done yet:\n" + "  " + res);
                            } else {
                                String res = list.deleteItem(idx);
                                System.out.println("Noted. I've removed this task:\n" + "  " + res);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a valid number for your index!");
                        } catch (NotInChatBotListException e) {
                            System.out.println("Make sure your item is in the list!\n" +
                                    "You may check using the \"list\" command.");
                        }
                    }
                } else if (splitInput[0].equals("event")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.\nPlease use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.EVENT);
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalChatBotListArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("deadline")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.\nPlease use this syntax:\n"+
                                "deadline <desc> /by <deadline>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.DEADLINE);
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalChatBotListArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("todo")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        try {
                        String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.TODO);
                        System.out.println("Got it. I've added this task:\n" + taskStr);
                        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalChatBotListArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        System.out.println(FAREWELL);
        s.close();
    }
}
