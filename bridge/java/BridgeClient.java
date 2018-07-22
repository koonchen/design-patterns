class Abstraction {
  Implementor implementor;
  void operation() {
    implementor.operationImp();
  }
}

class Implementor {
  void operationImp() {
    System.out.println("Implementor instance has been created");
  }
}

class RefinedAbstraction extends Abstraction {
  public RefinedAbstraction(String classname) {

    try {
      implementor = (Implementor) Class.forName(classname).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class ConcreteImplementorA extends Implementor {
  @Override
  void operationImp() {
    System.out.println("ConcreteImplementorA instance has been created");
  }
}

class ConcreteImplementorB extends Implementor {
  @Override
  void operationImp() {
    System.out.println("ConcreteImplementorB instance has been created");
  }
}

public class BridgeClient {
  public static void main(String[] args) {
    Abstraction abstraction = new RefinedAbstraction(ConcreteImplementorA.class.toString().substring(6));
    abstraction.operation();
    abstraction = new RefinedAbstraction((ConcreteImplementorB.class.toString().substring(6)));
    abstraction.operation();
  }
}
