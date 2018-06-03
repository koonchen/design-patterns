public class Singleton {

  private static Singleton instance;

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }

  public void check() {
    System.out.println("the instance has been created");
  }

  public static void main(String[] args) {
    getInstance().check();
  }
}
