# singleton_patterns

## 代码链接

- [java 懒汉](./java/LazySingleton.java)
- [java 注册表](./java/RegistrySingleton.java)
- [java 饿汉](./java/HungrySingleton.java)
- [java 双锁懒汉](./java/DoubleLockSingleton.java)

- [java 内部类](./java/InSingleton.java)
- [java 枚举](./java/EnumSingleton.java)

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
public class LazySingleton {

  private LazySingleton() {}

  private static LazySingleton instance;

  public static LazySingleton getInstance() {
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

### java 的其他单例

#### 饿汉单例

上面的代码中的 **基础实现** 属于 java 中的 **懒汉** 单例，与之名称呼应的还有一种 **饿汉** 单例：

```java
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

```

这种单例模式虽然线程安全了，但是耗费内存，无法延迟加载。

#### 懒汉单例

于是我们回到 **懒汉** 单例上，为了解决其线程不安全的问题，我们对其进行修改，使其线程安全。

```java
...
public static synchronized LazySingleton getInstance() {
  if (instance == null) {
    instance = new LazySingleton();
  }
  return instance;
}
...
```

但是这样的单例，性能开销极大。

#### 单锁懒汉单例

经过上面的探讨，我们发现将 synchronized 套用在方法上代价太大，不如将其作为块使用。

```java
...
public static synchronized LazySingleton getInstance() {
  if (instance == null) {
    synchronized (LazySingleton.class) {
	  instance = new LazySingleton();
    }
  }
  return instance;
}
...
```

虽然加上了锁，但是等到第一个线程实例化完成，跳出，第二个线程还是可能进入 if 实例化另一个实例，因此线程还是不安全的。

#### 双锁懒汉单例

既然如此，不如上两层判断。

```java
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
```

现在对了吗？并不一定，当语句不满足原子性时，语句将重排，于是我们需要的 instance 可能存在一种 **不为 null 但是仍然未被初始化** 的状态，这样会报错。

只需要修改变量的可见性即可破解：

```java
...
private static volatile DoubleLockSingleton instance;
...
```

> volatile 关键字具有屏蔽指令重排的功能，即对 instance 加上了一把锁，在完成写操作之前不会允许其他线程进行读操作，因此，在初始化完成前，无法对其进行读操作

这就组成了完整形态的双锁懒汉。

#### 内部类单例

这样的单例与饿汉相似，但是却又不同。

```java
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
```

饿汉单例在类加载实例化对象，内部类单例在需要的时候进行实例化，线程安全，延迟加载，效率高。

#### 枚举单例

在 Google I/O 2008 上，Joshua Bloch 介绍了这种方法。

```java
public enum EnumSingleton {

  INSTANCE;

  public void check() {
    System.out.println("the instance has been created");
  }

  public static void main(String[] args) {
    INSTANCE.check();
  }

}
```

这应该是现在最佳的单例实践。

***

### python 的其他单例

