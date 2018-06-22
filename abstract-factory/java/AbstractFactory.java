// 根据一般规律，先创建产品
interface AbstractProductA {
  void productAOperation();
}

interface AbstractProductB {
  void productBOperation();
}

// 创建工厂
interface Factory {
  AbstractProductA createProductA();
  AbstractProductB createProductB();
}

// 一般规律，先具体实现产品
class ProductA1 implements AbstractProductA {

  @Override
  public void productAOperation() {
    System.out.println("ProductA1 instance has been created");
  }
}

class ProductA2 implements AbstractProductA {

  @Override
  public void productAOperation() {
    System.out.println("ProductA2 instance has been created");
  }
}

class ProductB1 implements AbstractProductB {

  @Override
  public void productBOperation() {
    System.out.println("ProductB1 instance has been created");
  }
}

class ProductB2 implements AbstractProductB {

  @Override
  public void productBOperation() {
    System.out.println("ProductB2 instance has been created");
  }
}

// 具体实现工厂
class ConcreteFactory1 implements Factory {

  @Override
  public AbstractProductA createProductA() {
    return new ProductA1();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ProductB1();
  }
}

class ConcreteFactory2 implements Factory {

  @Override
  public AbstractProductA createProductA() {
    return new ProductA2();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ProductB2();
  }
}

public class AbstractFactory {
  public static void main(String[] args) {
    Factory factory = new ConcreteFactory1();
    factory.createProductA().productAOperation();
    factory.createProductB().productBOperation();
    factory = new ConcreteFactory2();
    factory.createProductA().productAOperation();
    factory.createProductB().productBOperation();
  }
}
