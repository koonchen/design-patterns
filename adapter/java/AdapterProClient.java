interface TargetPro {
  void requestPro();
}

class AdapteePro {
  void specificRequestPro() {
    System.out.println("AdapteePro instance has been created");
  }
}

class AdapterPro implements TargetPro {

  AdapteePro adapteePro;

  public AdapterPro() {
    this.adapteePro = new AdapteePro();
  }

  @Override
  public void requestPro() {
    adapteePro.specificRequestPro();
  }
}

class ClientPro implements TargetPro {
  TargetPro targetPro;

  public ClientPro(TargetPro targetPro) {
    this.targetPro = targetPro;
  }

  @Override
  public void requestPro() {
    targetPro.requestPro();
  }
}

public class AdapterProClient {
  public static void main(String args[]) {
    ClientPro client = new ClientPro(new AdapterPro());
    client.requestPro();
  }
}
