import java.util.ArrayList;
import java.util.List;

class Component {
  void operation() {
    System.out.println("Component instance has been created");
  }
  void add(Component component) {}
  void remove(Component component) {}
  Component getChild(int index) {
    return null;
  }
}

class Leaf extends Component {

  @Override
  void operation() {
    System.out.println("Leaf instance has been created");
  }
}

class Composite extends Component {
  List list = new ArrayList<Component>();
  @Override
  void add(Component component) {
    list.add(component);
  }

  @Override
  void remove(Component component) {
    list.remove(component);
  }

  @Override
  Component getChild(int index) {
    return (Component) list.get(index);
  }
}

public class CompositeClient {
  public static void main(String[] args) {
    Leaf leaf = new Leaf();
    Composite composite = new Composite();
    composite.add(leaf);
    composite.getChild(0).operation();
  }
}
