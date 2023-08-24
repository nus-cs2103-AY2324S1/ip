import java.util.Scanner;

public class JermBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm JermBot");
        System.out.println("What can I do for you?");

        Task[] storage = new Task[100];
        int numOfItems = 0;
        Scanner sc = new Scanner(System.in);
        String currStr = sc.nextLine();
        while (true) {
            if (currStr.equals("bye")) {
                break;
            }

            if (currStr.equals("list")) {
                for (int i = 0; i < numOfItems; i++) {
                    System.out.print(i + 1);
                    System.out.print(". " + storage[i].toString() + "\n");
                }
            } else {
                try {
                    String[] splitStr = currStr.split(" ");

                    if (splitStr.length == 2 && (splitStr[0].equals("mark") || splitStr[0].equals("unmark"))) {
                        try {
                            int itemNumber = Integer.parseInt(splitStr[1]);
                            if (splitStr[0].equals("mark")) {
                                storage[itemNumber - 1].markDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println("   " + storage[itemNumber - 1].toString());
                            } else {
                                storage[itemNumber - 1].markUndone();
                                System.out.println("Ok, I've marked this task as not done yet:");
                                System.out.println("   " + storage[itemNumber - 1].toString());
                            }
                        } catch (NumberFormatException e) {
                            throw new WrongInputException();
                        }
                    } else if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")) {
                        Task addedTask = new Task("");
                        switch (splitStr[0]) {
                            case "todo":
                                String[] todoSplit = currStr.split("todo");
                                if (todoSplit.length <= 1 || todoSplit[1].trim().equals("")) {
                                    throw new EmptyTodoException();
                                }
                                addedTask = new Todo(todoSplit[1].trim());
                                break;
                            case "deadline":
                                String subStr1 = currStr.split("deadline ")[1];
                                String[] subStr2 = subStr1.split(" /by ");
                                addedTask = new Deadline(subStr2[0], subStr2[1]);
                                break;
                            case "event":
                                String splitStr3 = currStr.split("event ")[1];
                                String[] splitStr4 = splitStr3.split(" /");
                                addedTask = new Event(splitStr4[0], splitStr4[1].substring(5), splitStr4[2].substring(3));
                                break;
                        }
                        storage[numOfItems] = addedTask;
                        numOfItems++;
                        System.out.printf("Got it. I've added this task:\n   %s\nNow you have %d tasks in the list.\n", addedTask, numOfItems);
                    } else {
                        throw new WrongInputException();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }




            currStr = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
