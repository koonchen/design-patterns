import java.util.EnumMap;
import java.util.Map;

enum FlyweightType {
  SHARED, UNSHARED
}

interface Flyweight {
  void operation();
}

class ConcreteFlyweight implements Flyweight {

  @Override
  public void operation() {
    System.out.println("ConcreteFlyweight instance has been created");
  }
}

class UnsharedConcreteFlyweight implements Flyweight {

  @Override
  public void operation() {
    System.out.println("UnsharedConcreteFlyweight instance has been created");
  }
}

class FlyweightFactory {
  private final Map<FlyweightType, Flyweight> factory;

  public FlyweightFactory() {
    factory = new EnumMap(FlyweightType.class);
  }

  Flyweight getFlyweight(FlyweightType type) {
    Flyweight flyweight = factory.get(type);
    if (flyweight == null) {
      switch (type) {
        case SHARED:
          flyweight = new ConcreteFlyweight();
          factory.put(type, flyweight);
          break;
        case UNSHARED:
          flyweight = new UnsharedConcreteFlyweight();
          factory.put(type, flyweight);
          break;
      }
    }
    return flyweight;
  }
}


public class FlyweightClient {
  public static void main(String[] args) {
    FlyweightFactory factory = new FlyweightFactory();
    factory.getFlyweight(FlyweightType.SHARED).operation();
    factory.getFlyweight(FlyweightType.UNSHARED).operation();
  }
}
