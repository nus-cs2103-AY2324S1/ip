public class Ui {
    String name = "Harry Potter";
    String question = "Introducing the Wizarding World Organizer: Your Trusted Guide" +
            " to Efficient & Effective Magical Planning";
    String hello = "Hello muggle! I'm " + name + "\n" + question;
    String bye = "\t" + "Expelliarmus! Hope to see you again muggle! :D";
    public Ui() {}
    public void printHello() {
        System.out.println(hello);
    }
    public void printBye() {
        System.out.println(bye);
    }
}
