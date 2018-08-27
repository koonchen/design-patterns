interface Subject {
  void request();
}

class RealSubject implements Subject {

  @Override
  public void request() {
    System.out.println("RealSubject instance has been created");
  }
}

class Proxy implements Subject {

  private final Subject realSubject;

  public Proxy(Subject realSubject) {
    this.realSubject = realSubject;
  }

  @Override
  public void request() {
    realSubject.request();
  }
}

public class ProxyClient {
  public static void main(String[] args) {
    Proxy proxy = new Proxy(new RealSubject());
    proxy.request();
  }
}
