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
                    boolean done = storage[i].done;
                    if (done) {
                        System.out.print(". [X] " + storage[i].name + "\n");
                    } else {
                        System.out.print(". [ ] " + storage[i].name + "\n");
                    }
                }
            } else {
                String[] splitStr = currStr.split(" ");
                if (splitStr.length == 2 && (splitStr[0].equals("mark") || splitStr[0].equals("unmark"))) {
                    try {
                        int itemNumber = Integer.parseInt(splitStr[1]);
                        if (splitStr[0].equals("mark")) {
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("   [X]: " + storage[itemNumber - 1].name);
                            storage[itemNumber - 1].done = true;
                        } else {
                            System.out.println("Ok, I've marked this task as not done yet:");
                            System.out.println("   [ ]: " + storage[itemNumber - 1].name);
                            storage[itemNumber - 1].done = false;
                        }
                    } catch (NumberFormatException e){
                        storage[numOfItems] = new Task(currStr);
                        numOfItems++;
                        System.out.println("added: " + currStr);
                    }
                } else {
                    storage[numOfItems] = new Task(currStr);
                    numOfItems++;
                    System.out.println("added: " + currStr);
                }
            }

            currStr = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
