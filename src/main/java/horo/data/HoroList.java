package horo.data;

import java.util.ArrayList;

public abstract class HoroList<T extends Data> {
  protected ArrayList<T> list = new ArrayList<>();

  public HoroList() {
  }

  public HoroList(ArrayList<T> list) {
    this.list = list;
  }

  public abstract void add(T list);

  public abstract void remove(int i);
}
