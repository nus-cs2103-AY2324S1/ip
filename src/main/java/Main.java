import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.printIntro();

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();
            
            if (command.equalsIgnoreCase("bye")) {
                jarvis.printBye();
                System.exit(0);
            } else {
                jarvis.echo(command);
            }
        }

        
    } 
}