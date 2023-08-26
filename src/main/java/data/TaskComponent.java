package data;

public abstract class TaskComponent { 
}

final class Description extends TaskComponent {
    protected String description;
    
    public Description(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return this.description;
    }
}

final class By extends TaskComponent {
    protected String by;

    public By(String by) {
        this.by = by;
    }
    
    @Override
    public String toString() {
        return this.by;
    }
}

final class From extends TaskComponent {
    protected String from;

    public From(String from) {
        this.from = from;
    }
    
    @Override
    public String toString() {
        return this.from;
    }
}

final class To extends TaskComponent {
    protected String to;

    public To(String to) {
        this.to = to;
    }
    
    @Override
    public String toString() {
        return this.to;
    }
}


