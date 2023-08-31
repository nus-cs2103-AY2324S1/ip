public class Ui {

    static String line = "____________________________________________________________";

    public Ui() {

    }
    public void greet() {
        System.out.println("     Hello from");
        System.out.println("       /\\/\\   __ ___  __");
        System.out.println("      /    \\ / _` \\ \\/ /");
        System.out.println("     / /\\/\\ \\ (_| |>  <");
        System.out.println("     \\/    \\/\\__,_/_/\\_\\");
        System.out.println("     How may I assist you?");
        System.out.println(line);
    }

    public void exit() {
        System.out.println("     Bye! Please come again!");
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("No tasks were found! Making you a new list real quick xd");
    }

}
