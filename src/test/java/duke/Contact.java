package duke;

public class Contact {
    private String name;
    private int contactNum;

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, int contactNum) {
        this.name = name;
        this.contactNum = contactNum;
    }

    public String getName() {
        return this.name;
    }

    public int getContactNum() {
        return this.contactNum;
    }
}
