interface Visitor {
  void visitConcreteElement(Element element);
}

class ConcreteVisitor implements Visitor {

  @Override
  public void visitConcreteElement(Element element) {
    element.doSth();
  }
}

interface Element {
  void accept(Visitor visitor);
  void doSth();
}

class ConcreteElement implements Element {

  @Override
  public void accept(Visitor visitor) {
    visitor.visitConcreteElement(this);
  }

  @Override
  public void doSth() {
    System.out.println("the method has been used");
  }
}

public class VisitorClient {
  public static void main(String[] args) {
    // 省略存储全部的 element
    Element element = new ConcreteElement();
    Visitor visitor = new ConcreteVisitor();
    element.accept(visitor);
  }
}
