// 注册表单例
import java.util.HashMap;
import java.util.Map;

public class RegistrySingleton {

  private RegistrySingleton() {}

  private static Map registry = new HashMap<String, Object>();

  public static RegistrySingleton getInstance(String classname){
    Object singleton = registry.get(classname);
    if (singleton == null) {
      try {
        singleton = Class.forName(classname).newInstance();
      } catch (Exception e) {
        e.printStackTrace();
      }
      registry.put(classname,singleton);
    }
    return (RegistrySingleton) singleton;
  }

  public void check() {
    System.out.println("the instance has been created");
  }

  public static void main(String[] args) {
    getInstance("RegistrySingleton").check();
  }
}
