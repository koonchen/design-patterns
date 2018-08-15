import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class BaseSystem {
  void operation() {
    System.out.println("BaseSystem instance has been created");
  }
  void otherOperation() {}
}

class Scanner extends BaseSystem {
  @Override
  void operation() {
    System.out.println("Scanner instance has been created");
  }
}

class Stream extends BaseSystem {
  @Override
  void operation() {
    System.out.println("Stream instance has been created");
  }
}

class CompilerFacade {
  List<BaseSystem> subSystems;

  public CompilerFacade() {
    subSystems = new CopyOnWriteArrayList<>();
    subSystems.add(new Scanner());
    subSystems.add(new Stream());
  }

  void makeActions() {
    subSystems.forEach(item -> {
      item.operation();
    });
  }
}

public class FacadeClient {
  public static void main(String[] args) {
    CompilerFacade facade = new CompilerFacade();
    facade.makeActions();
  }
}
