class Product {
  private String describe;
  private String name;

  void withNameDes(String name, String describe) {
    this.name = name;
    this.describe = describe;
  }

  @Override
  public String toString() {
    return "Product{" +
        "describe='" + describe + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}

abstract class Builder {
  Product product = null;
  abstract void buildPart();
  Product getResult(){
    System.out.println(product);
    return product;
  }
}

class ConcreteBuilder extends Builder {
  @Override
  void buildPart() {
    product = new Product();
    product.withNameDes("product", "Product instance has been created");
  }
}

class Director {
  Builder builder;
  Director(Builder builder) {
    this.builder = builder;
  }

  void construct() {
    builder.buildPart();
  }
}

public class BuilderBasic {
  public static void main(String[] args) {
    Builder builder = new ConcreteBuilder();
    Director director = new Director(builder);
    director.construct();
    builder.getResult();
  }
}