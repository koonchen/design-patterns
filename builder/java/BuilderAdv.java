class ProductAdv {
  private String describe;
  private String name;
  ProductAdv(ConcreteBuilderAdv builder) {
    this.describe = builder.getName();
    this.name = builder.getDescribe();
  }

  @Override
  public String toString() {
    return "ProductAdv{" +
        "describe='" + describe + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}

class ConcreteBuilderAdv {
  private String describe;
  private String name;
  ConcreteBuilderAdv withName(String name) {
    this.name = name;
    return this;
  }

  ConcreteBuilderAdv withDes(String describe) {
    this.describe = describe;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getDescribe() {
    return describe;
  }

  ProductAdv getResult() {
    return new ProductAdv(this);
  }
}

public class BuilderAdv {
  public static void main(String[] args) {
    ProductAdv product = new ConcreteBuilderAdv()
        .withName("productAdv")
        .withDes("ProductAdv instance has been created")
        .getResult();
    System.out.println(product);
  }
}
