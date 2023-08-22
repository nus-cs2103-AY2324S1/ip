import java.util.Scanner;
public class Kora {

    public Kora() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am your chatbot Kora!\nHow can I help you today?");
        System.out.println("------------------------------");
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(getResponse(scanner.next()));
        }

        //System.out.println("Byebye. See you again!");
    }

    public String getResponse(String userInput) {
        String output;
        try {
            if (userInput.equals("bye")) {
                output = "Byebye. See you again!";
            } else {
                output = userInput;
            }
        } catch (Exception e) {
            output = e.getMessage();
        }
        return output;
    }
    public static void main(String[] args) {
        Kora kora = new Kora();
    }
}
