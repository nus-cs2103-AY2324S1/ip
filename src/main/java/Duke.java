import java.util.Scanner;

public class Duke {
    final private static String name = "Iris";

    private static boolean running = true;
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        Scanner scanner = new Scanner(System.in);
        Message.OnGreeting(name).Print();
        while( running ){
            if(scanner.hasNext()) {
                inputHandler.HandleInput(scanner.nextLine());
            }
        }
    }

    protected static void Exit()
    {
        running = false;
    }
}
