public class ItemList {
    private Task[] items;
    private int len;
    public ItemList() {
        this.items = new Task[100];
        this.len = 0;
    }

    public void addDeadline(String name, String by) {
        if (name.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (by.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an end date");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[this.len] = new Deadline(name, by);
        this.len++;
        System.out.println(Greeting.linebreak);
        System.out.println("Got it. I've added this task:");
        System.out.println(items[this.len - 1].showTaskinList());
        System.out.println("Now you have " + String.valueOf(len) + " tasks in this list");
        System.out.println(Greeting.linebreak);

    }

    public void addTodo(String newitem) {
        if (newitem.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[this.len] = new ToDo(newitem);
        this.len++;
        System.out.println(Greeting.linebreak);
        System.out.println("Got it. I've added this task:");
        System.out.println(items[this.len - 1].showTaskinList());
        System.out.println("Now you have " + String.valueOf(len) + " tasks in this list.");
        System.out.println(Greeting.linebreak);
    }

    public void addEvent(String newitem, String from, String to) {
        if (newitem.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter an item");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (from.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter a start date");
            System.out.println(Greeting.linebreak);
            return;
        }

        if (to.equals("")) {
            System.out.println(Greeting.linebreak);
            System.out.println("Please enter a end date");
            System.out.println(Greeting.linebreak);
            return;
        }
        this.items[this.len] = new Event(newitem, from ,to);
        this.len++;
        System.out.println(Greeting.linebreak);
        System.out.println("Got it. I've added this task:");
        System.out.println(items[this.len - 1].showTaskinList());
        System.out.println("Now you have " + String.valueOf(len) + " tasks in this list");
        System.out.println(Greeting.linebreak);
    }

    public void showitems() {
        if (this.len == 0) {
            System.out.println(Greeting.linebreak);
            System.out.println("No item in the list.");
            System.out.println(Greeting.linebreak);
        }
        System.out.println(Greeting.linebreak);
        System.out.println("Here are the tasks in your list:");
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
