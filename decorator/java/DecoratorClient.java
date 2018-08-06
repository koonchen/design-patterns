class Component {
  void operation() {
    System.out.println("Component instance has been created");
  }
}

class ConcreteComponent extends Component {
  @Override
  public void operation() {
    System.out.println("DecoratorClient instance has been created");
  }
}

class Decorator extends Component {
  Component component;

  public Decorator(Component component) {
    this.component = component;
  }

  @Override
  public void operation() {
    component.operation();
  }
}

class ConcreteDecoratorA extends Decorator {

  public ConcreteDecoratorA(Component component) {
    super(component);
  }

  void addedState() {
    System.out.println("Added state");
  }
}

class ConcreteDecoratorB extends Decorator {

  public ConcreteDecoratorB(Component component) {
    super(component);
  }

  void addedBehavior() {
    System.out.println("Added behavior");
  }
}

public class DecoratorClient {
  public static void main(String[] args) {
    ConcreteComponent component = new ConcreteComponent();
    ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA(component);
    ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(component);
    component.operation();
    concreteDecoratorA.addedState();
    concreteDecoratorB.addedBehavior();
  }
}