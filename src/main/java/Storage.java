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

    protected E get(int idx) {
        return this.mem.get(idx);
    }

    protected Storage<E> update(int idx, E elem) {
        Storage<E> newStore = new Storage<E>(this.mem);
        newStore.mem.set(idx, elem);
        return newStore;
    }

    @Override
    public Iterator<E> iterator() {
        return this.mem.iterator();
    }

    protected String list() {
        String str = "____________________________________________________________\n" +
                "Here are the tasks in your list:\n";
        Iterator<E> iter = this.mem.iterator();
        int i = 1;
        while (iter.hasNext()) {
            str += Integer.toString(i) + ". " + iter.next();
            i++;
        }
        str += "____________________________________________________________\n";
        return str;
    }
}
