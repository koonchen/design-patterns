interface Compositor {
  void compose();
}

class SimpleCompositor implements Compositor {
  public void compose() {
    System.out.println("SimpleCompositor instance has been created");
  }
}

class TeXCompositor implements Compositor {
  public void compose() {
    System.out.println("TeXCompositor instance has been created");
  }
}

class ArrayCompositor implements Compositor {
  public void compose() {
    System.out.println("ArrayCompositor instance has been created");
  }
}

class Composition {
  private Compositor compositor;

  public Composition(Compositor compositor) {
    this.compositor = compositor;
  }

  public void repair() {
    compositor.compose();
  }

}

public class StrategyClient {
  public static void main(String[] args) {
    Composition composition = new Composition(new SimpleCompositor());
    composition.repair();
    composition = new Composition(new TeXCompositor());
    composition.repair();
    composition = new Composition(new ArrayCompositor());
    composition.repair();
  }
}
