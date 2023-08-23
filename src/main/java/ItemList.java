public class ItemList {
    private Task[] items;
    private int len;
    public ItemList() {
        this.items = new Task[100];
        this.len = 0;
    }

    public void additems(String newitem) {
        if (newitem.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[this.len] = new Task(newitem);
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
            System.out.println(index + ". " + this.items[i].showTaskinList());
        }
        System.out.println(Greeting.linebreak);
    }

    public void markDone(int index) {
        int i = index - 1;
        if (i < 0 || i >= this.len) {
            System.out.println(Greeting.linebreak);
            System.out.println("No such Task");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[i].setDone();
        System.out.println(Greeting.linebreak);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.items[i].showTaskinList());
        System.out.println(Greeting.linebreak);
    }

    public void markUndone(int index) {
        int i = index - 1;
        if (i < 0 || i >= this.len) {
            System.out.println(Greeting.linebreak);
            System.out.println("No such Task");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[i].setUndone();
        System.out.println(Greeting.linebreak);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.items[i].showTaskinList());
        System.out.println(Greeting.linebreak);
    }




}
