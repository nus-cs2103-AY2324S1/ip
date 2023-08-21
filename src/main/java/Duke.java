import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Termina";
        System.out.println("Hello, I am your chatbot!\nMy name is " + name + "\nHow may I help?");
        Scanner scanner = new Scanner(System.in);
        String stuff;
        stuff = scanner.nextLine();

        while (!stuff.equalsIgnoreCase("bye")) {
            System.out.println(stuff);
            stuff = scanner.nextLine();
        }

        System.out.println("Byeeee! Use me again please!"); 
    }
}
