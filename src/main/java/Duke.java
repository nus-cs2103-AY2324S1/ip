import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Termina";
        System.out.println("Hello, I am your chatbot!\nMy name is " + name + "\nHow may I help?");
        Scanner scanner = new Scanner(System.in);
        String stuff;
        ArrayList<String> items = new ArrayList();
        stuff = scanner.nextLine();

        while (!stuff.equalsIgnoreCase("bye")) {
            if(stuff.equalsIgnoreCase("list")) {

                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i+1) + ". " + items.get(i));
                }

            } else{
                System.out.println("added: " + stuff);
                items.add(stuff);
            }
            stuff = scanner.nextLine();
        }

        System.out.println("Byeeee! Use me again please!");
    }
}
