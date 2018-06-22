// 一般规律，先创建产品接口
interface ProductUnify {
  void productOperation();
}
// 工厂接口
interface FactoryUnify {
  ProductUnify make();
}
// 实现产品
class ProductUnifyA1 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyA1 instance has been created");
  }
}

class ProductUnifyA2 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyA2 instance has been created");
  }
}

class ProductUnifyB1 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyB1 instance has been created");
  }
}

class ProductUnifyB2 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyB2 instance has been created");
  }
}
// 实现工厂
class ConcreteFactoryUnifyA1 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyA1();
  }
}

class ConcreteFactoryUnifyA2 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyA2();
  }
}

class ConcreteFactoryUnifyB1 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyB1();
  }
}

class ConcreteFactoryUnifyB2 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyB2();
  }
}

public class AbstractFactoryUnify {
  public static void main(String[] args) {
    FactoryUnify factory = new ConcreteFactoryUnifyA1();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyA2();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyB1();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyB2();
    factory.make().productOperation();
  }
}
