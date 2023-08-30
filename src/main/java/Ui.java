public class Ui {

    String divider = "------------------------------------\n";

    String greet = divider +
            "Hello! I'm Khaleelur!\n" +
            "What can I do for you?\n " +
            divider;

    String exit = divider +
            "Bye. Hope to see you again soon!\n" +
            divider;

    public void greet() {
        System.out.println(greet);
    }

    public void exit() {
        System.out.println(exit);
    }

    public void print(String toPrint) {
        System.out.println(divider + toPrint + "\n" + divider);
    }

    public void showLoadingError(Exception e) {
        System.out.println(divider + e.getMessage() + "\n" + divider);
    }
}
