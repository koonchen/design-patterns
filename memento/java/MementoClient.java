import java.util.ArrayList;
import java.util.List;

class Memento {
  private String state;
  public Memento(String state) {
    this.state = state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getState() {
    return this.state;
  }
}

class Originator {
  private String state = "before";
  public void setMemento(Memento memento) {
    state = memento.getState();
  }
  public Memento createMemento() {
    return new Memento(state);
  }
}

class Caretaker {
  private List<Memento> list;
  public Caretaker() {
    this.list = new ArrayList<>();
  }
  public void add(Memento memento) {
    list.add(memento);
  }
  public List<Memento> getList() {
    return this.list;
  }
}

public class MementoClient {
  public static void main(String[] args) {
    Caretaker caretaker = new Caretaker();
    Originator originator = new Originator();
    caretaker.add(originator.createMemento());
    List<Memento> list = caretaker.getList();
    System.out.println(list.get(0).getState());
  }
}
