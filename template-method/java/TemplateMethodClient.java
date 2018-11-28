abstract class AbstractClass {
  void templateMethod() {
    primitiveOperation1();
    primitiveOperation2();
  }
  abstract void primitiveOperation1();
  abstract void primitiveOperation2();
}

class ConcreteClass extends AbstractClass {

  @Override
  void primitiveOperation1() {
    System.out.println("primitiveOperation1 method has been used");
  }

  @Override
  void primitiveOperation2() {
    System.out.println("primitiveOperation2 method has been used");
  }
}

public class TemplateMethodClient {
  public static void main(String[] args) {
    AbstractClass abstractClass = new ConcreteClass();
    abstractClass.templateMethod();
  }
}
