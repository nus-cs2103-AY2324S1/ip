public class Bye extends Instructions {
    public Bye(String str) {
    }
    @Override
    public void implement(User user) {
        System.out.println("---------------------------------------------------------------");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------------");
    }
}
