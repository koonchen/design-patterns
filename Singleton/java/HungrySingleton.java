// 饿汉单例
// 线程安全，但耗费内存
public class HungrySingleton {

  private HungrySingleton() {}

  private static HungrySingleton instance = new HungrySingleton();

  public static HungrySingleton getInstance() {
    return instance;
  }

  public void check() {
    System.out.println("the instance has been created");
  }

  public static void main(String[] args) {
    getInstance().check();
  }
}
