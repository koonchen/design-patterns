# singleton_patterns

## 要点


> 保证一个类中仅含有一个实例，并提供一个访问它的全局访问点。

生活中有非常多这样的例子，一个学生管理系统只专注于一所学校，一个人只能拥有一双眼睛、一颗心脏...

单例模式中，最重要的是**保证一个类只有一个实例并且这个实例可以被访问**。答案是让类自身负责保存它的唯一实例，这就是 Singleton 模式了。

*什么情况下可以使用单例模式？*

- 当一个类中只含有一个实例的同时，可以从一个访问点访问它。
- 当唯一实例可以通过子类扩展时，我们无需更改代码就能使用一个扩展了的实例。(待验证)

其关系属于依赖关系，唯一实例受控访问，造成其拥有: 

**缩小名空间**、**子类易于配置**、**可变实例数**、**比类操作灵活**的优点。

## 实现

### 基础实现

```java
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
```

这样是不够的，