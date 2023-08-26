import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Storage<E> implements Iterable<E>  {
    private final ArrayList<E> mem;

    protected Storage() {
        this.mem = new ArrayList<E>();
    }

    private Storage(List<? extends E> mem) {
        this.mem = new ArrayList<E>(mem);
    }

    protected Storage<E> save(E elem) {
        Storage<E> newMem = new Storage<E>(this.mem);
        newMem.mem.add(elem);
        return newMem;
    }

    @Override
    public Iterator<E> iterator() {
        return this.mem.iterator();
    }

    protected String list() {
        String str = "____________________________________________________________\n";
        Iterator<E> iter = this.mem.iterator();
        int i = 1;
        while (iter.hasNext()) {
            str += Integer.toString(i) + ". " + iter.next() + "\n";
            i++;
        }
        str += "____________________________________________________________\n";
        return str;
    }
}
