interface Target {
  void request();
}

class Adaptee {
  void specificRequest() {
    System.out.println("Adaptee instance has been created");
  }
}

class Adapter implements Target {
  Adaptee adaptee = new Adaptee();
  @Override
  public void request() {
    adaptee.specificRequest();
  }
}

class Client {
  void execute() {
    Adapter adapter = new Adapter();
    adapter.request();
  }
}

public class AdapterClient {
  public static void main(String args[]) {
    Client client = new Client();
    client.execute();
  }
}
