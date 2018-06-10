// 需要先定义产品
interface Product {
  void productOperation();
}
// 然后定义创建产品的生产者
interface Creator {
  Product factoryMethod();
  void creatorOperation();
}
// 具体产品的定义
class ConcreteProduct implements Product {

  @Override
  public void productOperation() {
    System.out.println("Product instance has been created");
  }
}
// 具体生产者的定义
class ConcreteCreator implements Creator {

  @Override
  public Product factoryMethod() {
    return new ConcreteProduct();
  }

  @Override
  public void creatorOperation() {
    System.out.println("Creator instance has been created");
  }
}

public class FactoryMethod {
  public static void main(String[] args) {
    Creator creator = new ConcreteCreator();
    Product product = creator.factoryMethod();
    creator.creatorOperation();
    product.productOperation();
  }
}
