// 内部类单例
// 避免了线程不安全，延迟加载，效率高。
// 和饿汉类似，两者都是采用了类装载的机制来保证初始化实例时只有一个线程。
// 不同点在于：内部类是在需要实例化时，调用 getInstance 方法，才会装载 InHodler 类
public class InSingleton {

  private InSingleton() {}

  private static class InHodler {
    private static InSingleton instance = new InSingleton();
  }

  public static InSingleton getInstance() {
    return InHodler.instance;
  }

  public void check() {
    System.out.println("the instance has been created");
  }

  public static void main(String[] args) {
    getInstance().check();
  }

}
