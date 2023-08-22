public class Bob {
    private String name = "Bob";
    private String[] list = new String[100];
    private String horizontal = "____________________________________________________________";
    private int count;

    public Bob() {
        this.count = 0;
    }

    public void greet() {
        String ln1 = String.format("Hello! I'm %s", this.name);
        String ln2 = "What can I do for you?";

        System.out.println(horizontal);
        System.out.println(ln1);
        System.out.println(ln2);
        System.out.println(horizontal);
    }
    public void bye() {
        String ln3 = "Bye. Hope to see you again soon!";

        System.out.println(horizontal);
        System.out.println(ln3);
        System.out.println(horizontal);
    }

    public void addTask(String task) {
        list[this.count] = task;
        this.count++;

        System.out.println(horizontal);
        System.out.println("added: " + task);
        System.out.println(horizontal);
    }

    public void listOut() {
        System.out.println(horizontal);

        for (int i = 1; i <= this.count; i++) {
            System.out.println(i + ". " + list[i - 1]);
        }

        System.out.println(horizontal);
    }

    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.addTask("help");
        bob.addTask("lol");

        bob.listOut();
    }
}
