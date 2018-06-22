// 需要先定义产品
abstract class GenericsProduct {
  abstract void productOperation();
}

// 然后定义创建产品的生产者
interface GenericsCreator {
  GenericsProduct factoryMethod();
  void creatorOperation();
}

// 生产者 —— 模拟 c++ 模板
class StandardCreator<T extends GenericsProduct> implements GenericsCreator {

  Class<T> clazz;

  public StandardCreator(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public GenericsProduct factoryMethod() {
    try {
      T temp = clazz.newInstance();
      return temp;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void creatorOperation() {
    System.out.println("GenericsCreator instance has been created");
  }
}

// 具体产品的定义
class GenericsConcreteProduct extends GenericsProduct {
  @Override
  public void productOperation() {
    System.out.println("GenericsProduct instance has been created");
  }
}

public class GenericsFactoryMethod {
  public static void main(String[] args) {
    GenericsCreator creator = new StandardCreator(GenericsConcreteProduct.class);
    GenericsProduct product = creator.factoryMethod();
    creator.creatorOperation();
    product.productOperation();
  }
}
