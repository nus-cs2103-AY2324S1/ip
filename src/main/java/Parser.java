import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {
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
