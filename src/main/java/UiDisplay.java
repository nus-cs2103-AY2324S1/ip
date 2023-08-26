public class UiDisplay {
    public void greetUser() {
        printMessage(String.format("Dook here.\nWhat can I do for you?"));
    }
    public void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    public void printDivider() {
        System.out.println("_______________________________________");
    }
    public void bidFarewell() {
        printMessage("Goodbye.");
    }
}
