public class ItemList {
    private String[] items;
    private int len;
    public ItemList() {
        this.items = new String[100];
        this.len = 0;
    }

    public void additems(String newitem) {
        this.items[this.len] = newitem;
        this.len++;
        System.out.println(Greeting.linebreak);
        System.out.println("add: " + newitem);
        System.out.println(Greeting.linebreak);
    }

    public void showitems() {
        if (this.len == 0) {
            System.out.println(Greeting.linebreak);
            System.out.println("No item in the list.");
            System.out.println(Greeting.linebreak);
        }
        System.out.println(Greeting.linebreak);
        for(int i = 0; i < this.len; i++) {
            String index = String.valueOf(i + 1);
            System.out.println(index + ". " + this.items[i]);
        }
        System.out.println(Greeting.linebreak);
    }

}
