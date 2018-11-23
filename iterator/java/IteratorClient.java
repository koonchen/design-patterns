import java.util.ArrayList;
import java.util.List;

interface Aggregate {
  ConcreteIteraor createIterator();
  // add
  List getList();
}

interface Iterator<T> {
  T first();
  T next();
  boolean isDone();
  T currentItem();
}

class ConcreteIteraor implements Iterator<Integer> {

  private Aggregate aggregate;
  private int idx;


  public ConcreteIteraor(Aggregate aggregate) {
    this.aggregate = aggregate;
    this.idx = -1;
  }

  @Override
  public Integer first() {
    List list = this.aggregate.getList();
    return (Integer) list.get(0);
  }

  @Override
  public Integer next() {
    List list = this.aggregate.getList();
    return (Integer) list.get(++idx);
  }

  @Override
  public boolean isDone() {
    System.out.println("the method has been used");
    return true;
  }

  @Override
  public Integer currentItem() {
    List list = this.aggregate.getList();
    return (Integer) list.get(idx);
  }
}

// 聚合类
class ConcreteAggregate implements Aggregate {

  private List<Integer> list;

  public ConcreteAggregate() {
    list = new ArrayList();
    list.add(3);
    list.add(10);
    list.add(1);
  }

  @Override
  public ConcreteIteraor createIterator() {
    return new ConcreteIteraor(this);
  }

  @Override
  public List<Integer> getList() {
    return this.list;
  }
}

public class IteratorClient {
  public static void main(String[] args) {
    ConcreteAggregate aggregate = new ConcreteAggregate();
    Iterator iterator = aggregate.createIterator();
    Integer item = (Integer) iterator.next();
    System.out.println(item);
  }
}
