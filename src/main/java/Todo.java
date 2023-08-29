class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

//    @Override
//    public String getText() {
//        return super.getText() + "\n";
//    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
