import java.util.Hashtable;

class Prototype implements Cloneable {
  @Override
  protected Prototype clone() {
    Prototype clone = null;
    try {
      clone = (Prototype) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return clone;
  }

  public void check() {
    System.out.println("Prototype instance has been created");
  }
}

class ConcretePrototype1 extends Prototype {
  public void check() {
    System.out.println("ConcretePrototype1 instance has been created");
  }
}

class ConcretePrototype2 extends Prototype {
  public void check() {
    System.out.println("ConcretePrototype2 instance has been created");
  }
}

public class PrototypeClient {
  public static void main(String[] args) {
    Hashtable map = new Hashtable<String,Prototype>();
    map.put("Prototype",new Prototype());
    map.put("ConcretePrototype1",new ConcretePrototype1());
    map.put("ConcretePrototype2",new ConcretePrototype2());

    ((Prototype) map.get("Prototype")).clone().check();
    ((Prototype) map.get("ConcretePrototype1")).clone().check();
    ((Prototype) map.get("ConcretePrototype2")).clone().check();
  }
}
