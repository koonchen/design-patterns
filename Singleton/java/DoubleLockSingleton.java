// 双锁懒汉单例
// 懒汉的最好写法,保证了:延迟加载和线程安全
public class DoubleLockSingleton {

  private DoubleLockSingleton() {}

  private static DoubleLockSingleton instance;

  public static DoubleLockSingleton getInstance() {
    if (instance == null) {
      synchronized (DoubleLockSingleton.class) {
        if (instance == null) {
          instance = new DoubleLockSingleton();
        }
      }
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
