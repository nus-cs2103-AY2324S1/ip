import java.util.Scanner;
import java.io.File;

public class Duke {

    /**
     * Handles commands and messages to ChatBot
     * @param args CLI args. No implementation.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ChatBotList list = new ChatBotList();
        Parser parser = new Parser();
        list.loadList();
        System.out.println(new File(DukeEnvironmentConstants.FILE_PATH));
        System.out.println(DukeEnvironmentConstants.GREETINGS);
        while (true) {
            String input = ui.getNextLine().trim();
            try {
                Command c = parser.parse(input);
                c.execute(list, ui);
                if (c instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showException(e);
            }
            // if (input.equals("bye")) {
            //     break;
            // } else if (input.equals("list")) {
            //     System.out.println(list);
            // } else {
            //     String[] splitInput = input.split(" +", 2);
            //     if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") || splitInput[0].equals("delete")) {
            //         if (splitInput.length == 1) {
            //             System.out.printf(ParserErrorMessages.EMPTY_MARK_QUERY, splitInput[0],
            //                     splitInput[0]);
            //         } else {
            //             try {
            //                 int idx = Integer.parseInt(splitInput[1]);
            //                 if (splitInput[0].equals("mark")) {
            //                     String res = list.markItem(idx);
            //                     System.out.printf("Nice! I've marked this task as done:\n" + "  " + res);
            //                 } else if (splitInput[0].equals("unmark")) {
            //                     String res = list.unmarkItem(idx);
            //                     System.out.println("OK, I've marked this task as not done yet:\n" + "  " + res);
            //                 } else {
            //                     String res = list.deleteItem(idx);
            //                     System.out.println("Noted. I've removed this task:\n" + "  " + res);
            //                 }
            //             } catch (NumberFormatException e) {
            //                 System.out.println("Please input a valid number for your index!");
            //             } catch (NotInChatBotListException e) {
            //                 System.out.println("Make sure your item is in the list!\n" +
            //                         "You may check using the \"list\" command.");
            //             }
            //         }
            //     } else if (splitInput[0].equals("event")) {
            //         if (splitInput.length < 2) {
            //             System.out.printf(ParserErrorMessages.EMPTY_EVENT_DESC);
            //         } else {
            //             try {
            //                 String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.EVENT);
            //                 System.out.println("Got it. I've added this task:\n" + taskStr);
            //                 System.out.println("Now you have " + list.getLength() + " tasks in the list.");
            //             } catch (IllegalChatBotListArgumentException e) {
            //                 System.out.println(e.getMessage());
            //             }
            //         }
            //     } else if (splitInput[0].equals("deadline")) {
            //         if (splitInput.length < 2) {
            //             System.out.printf(ParserErrorMessages.EMPTY_DEADLINE_DESC);
            //         } else {
            //             try {
            //                 String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.DEADLINE);
            //                 System.out.println("Got it. I've added this task:\n" + taskStr);
            //                 System.out.println("Now you have " + list.getLength() + " tasks in the list.");
            //             } catch (IllegalChatBotListArgumentException e) {
            //                 System.out.println(e.getMessage());
            //             }
            //         }
            //     } else if (splitInput[0].equals("todo")) {
            //         if (splitInput.length < 2) {
            //             System.out.printf(ParserErrorMessages.EMPTY_TODO_DESC);
            //         } else {
            //             try {
            //             String taskStr = list.addToList(splitInput[1], DukeEnvironmentConstants.taskType.TODO);
            //             System.out.println("Got it. I've added this task:\n" + taskStr);
            //             System.out.println("Now you have " + list.getLength() + " tasks in the list.");
            //             } catch (IllegalChatBotListArgumentException e) {
            //                 System.out.println(e.getMessage());
            //             }
            //         }
            //     } else {
            //         System.out.printf(ParserErrorMessages.NO_VALID_COMMAND);
            //     }
            // }
        }
        ui.close();
    }
}
