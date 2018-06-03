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

### 基础单例实现

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

这样是不够的，原因如下：

> 1. 我们不能保证静态对象只有一个实例会被声明。
> 2. 我们可能没有足够的信息在静态初始时实例化每一个组件，有些值可能需要在程序执行后才能被计算。
> 3. 如果语言没有定义转换单元 ( translation unit ) 上全局对象的构造器的调用顺序，那么单件之间就不存在依赖关系；如果有，那么错误是不可避免的。
> 4. 无论单件是否用到，它都将被创建。

***

### 注册表单例实现

主要问题是限制唯一实例，再使用，现在使用一种注册表的方法，维护目标从一个实例对象转换成一个注册表，单例类可以根据名字在一个注册表中注册它们的单例实例。注册表也可以查询相应的单例并返回它。

```java
import java.util.HashMap;
import java.util.Map;

public class RegistrySingleton {

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
```

这是 GoF 书中较为推荐的单例实现。

***

