import java.util.Scanner;

public class Ducky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserInterface ui = new UserInterface(sc);
        ui.start();
    }
}
