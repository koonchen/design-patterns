// 懒汉单例
// 节约内存，但线程不安全
// 修改方式是在 getInstance 前加上 synchronized 关键字，但是这样会造成很大的性能开销
public class LazySingleton {

  private LazySingleton() {}

  private static LazySingleton instance;

  public static synchronized LazySingleton getInstance() {
    if (instance == null) {
      instance = new LazySingleton();
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
