package dogebot;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Parser class handles user commands and calls the next appropriate method for its respective action.
 *
 * @author Kenvyn Kwek
 */
public class Parser {
    /**
     * Reads user command and calls the next appropriate method for its respective action. If "bye" command is entered,
     * false is returned to end the DogeBot.run() method's input loop.
     *
     * @return Loop status.
     */
    public boolean scan() {
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;

        try {
            switch (sc.next().toLowerCase()) {
            case "bye":
                isLoop = false;
                System.out.println("Bye~ See you again");
                break;
            case "list":
                TaskList.list();
                break;
            case "mark":
                TaskList.mark(sc.nextInt() - 1);
                break;
            case "unmark":
                TaskList.unmark(sc.nextInt() - 1);
                break;
            case "todo":
                TaskList.todo(sc.nextLine().trim()); // sc.nextLine() to get the remaining words
                break;
            case "deadline":
                TaskList.deadline(sc.nextLine().trim());
                break;
            case "event":
                TaskList.event(sc.nextLine().trim());
                break;
            case "delete":
                TaskList.delete(sc.nextInt() - 1);
                break;
            default:
                System.out.println("Wuff, I'm not sure what that means :(");
                sc.nextLine(); // absorb remaining words so 'default' block doesn't act up
            }
        } catch (InputMismatchException e) {
            sc.nextLine(); // absorb remaining words so 'default' block doesn't act up
            System.out.println("Oops ! Integers only please :c");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh no :( I think that number is too big~");
        } catch (DogeBotException e) {
            System.out.println(e.getMessage());
        }

        if (isLoop) {
            return true;
        } else {
            sc.close();
            return false;
        }
    }
}
