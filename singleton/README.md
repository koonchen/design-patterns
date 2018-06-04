# singleton_patterns

## 代码链接

>- [java 懒汉](./java/LazySingleton.java) , [py 懒汉](./python/LazySingleton.py)
>- [java 注册表](./java/RegistrySingleton.java) , [py 注册表](./python/RegistrySingleton.py)
>- [java 饿汉](./java/HungrySingleton.java)
>- [java 双锁懒汉](./java/DoubleLockSingleton.java)
>- [java 内部类](./java/InSingleton.java)
>- [java 枚举](./java/EnumSingleton.java)
>- [py 函数修饰器](./python/FunctionDecoratorSingleton.py)
>- [py 类修饰器](./python/ClassDecoratorSingleton.py)
>- [py 返回类的函数修饰器](./python/ReturnClassDecoratorSingleton.py)
>- [py 基类](./python/BaseClassSingleton.py)
>- [py 元类](./python/MetaclassSingleton.py)
>- [py model](./python/ModelSingleton.py)

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

#### java 实现

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

#### python 实现

````python
#!/usr/bin/env python
# coding:utf8

class LazySingleton(object):
  __instance = None

  @classmethod
  def getInstance(cls):
    if LazySingleton.__instance is None:
      LazySingleton.__instance = object.__new__(cls)
    return LazySingleton.__instance

  def check(self):
    print('the instance has been created')

  def __init__(self):
    raise 'createError'

if __name__ == '__main__':
  LazySingleton.getInstance().check()
````

这样是不够的，原因如下：

> 1. 我们不能保证静态对象只有一个实例会被声明。
> 2. 我们可能没有足够的信息在静态初始时实例化每一个组件，有些值可能需要在程序执行后才能被计算。
> 3. 如果语言没有定义转换单元 ( translation unit ) 上全局对象的构造器的调用顺序，那么单件之间就不存在依赖关系；如果有，那么错误是不可避免的。
> 4. 无论单件是否用到，它都将被创建。

***

### 注册表单例实现

主要问题是限制唯一实例，再使用，现在使用一种注册表的方法，维护目标从一个实例对象转换成一个注册表，单例类可以根据名字在一个注册表中注册它们的单例实例。注册表也可以查询相应的单例并返回它。

#### java实现

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

#### python实现

```python
#!/usr/bin/env python
# coding:utf8

class RegistrySingleton(object):
  registry = {}

  @classmethod
  def getSingleton(cls):
    if cls not in cls.registry:
      RegistrySingleton.registry[cls] = object.__new__(cls)
    return cls.registry[cls]

  def check(self):
    print('the instance has been created')

  def __init__(self):
    raise 'createError'

if __name__ == '__main__':
  RegistrySingleton.getSingleton().check()
```

这是 GoF 书中较为推荐的单例实现。

***

### java 的其他单例

#### 饿汉单例

上面的代码中的 **基础实现** 的 java 代码属于 java 中的 **懒汉** 单例，与之名称呼应的还有一种 **饿汉** 单例：

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

这应该是 **目前最佳的单例实践** 。

***

### python 的其他单例

上面的 python 代码实现书上的单例模式可以说非常的牵强，为了达到这种不能实例化的要求，强行在 `__init__` 里报错，因为 python 根本不需要这么复杂，它对于单例模式有着天生的优势。下面我们好好讨论一下，不强行报错了… 这里的实现参考 stackover 的一个问题：[Creating a singleton in Python](https://stackoverflow.com/questions/6760685/creating-a-singleton-in-python)

#### 函数装饰器单例

定义一个函数装饰器，让单例模式可以在多个类上进行复用。

```python
#!/usr/bin/env python
# coding:utf8

def singleton(cls):
  instances = {}
  def getinstance():
    if cls not in instances:
      instances[cls] = cls()
    return instances[cls]
  return getinstance

@singleton
class FunctionDecoratorSingleton(object):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  FunctionDecoratorSingleton().check()
```

这里使用的是 **函数装饰器** ，它的实现原理和上面的 **注册表单例** 中的 python 本质相同，这种写法和 `classmethod` 也完全等价，假设我们创建两个实例，判断它们的 id ，也将是一样的。

看起来很对，但是有一个很致命的问题，这个类并不是真正的类，它是一个方法，我们实例化创建的和使用装饰器实现的实例并不是同一个实例，所以我们在进行如下操作会发现问题：

```python
m = MyClass()
n = MyClass() 
o = type(n)() 
# m is n -> true
# m is o -> false
```

#### 类装饰器单例

谁说只能用函数装饰器？类装饰器也行啊。

```python
#!/usr/bin/env python
# coding:utf8

class singleton(object):
  def __init__(self, cls):
    self._cls = cls
    self._instance = {}

  def __call__(self):
    if self._cls not in self._instance:
      self._instance[self._cls] = self._cls()
    return self._instance[self._cls]

@singleton
class ClassDecoratorSingleton(object):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  ClassDecoratorSingleton().check()
```

看上去很对，但是和函数装饰器有着相同的问题。

统一总结一下装饰器的单例实现，可以说它们是比继承成为直观的附加，但是这样做还是没有解决根本问题，它的返回还是对象，无法通过上面的 m , n , o 测试。

并且到目前为止，所有的 python 单例都仅仅是形似，它们都不是真正的单例模式。我们顺着思路往下走

> *PS: 如何修改成真正的单例呢？*
> 其实就是 python 的文字游戏，非装饰器的实现下，只需要将 `getInstnce` 的 `classmethod` 改成 `__new__ ` 魔术方法即可。装饰器下怎么实现呢？请看下文。

#### 最佳修饰器实例

现在我们尝试解决修饰器的问题。

```python
#!/usr/bin/env python
# coding:utf8

def singleton(cls):
  class TempClass(cls):
    __instance = None

    def __new__(cls, *args, **kwargs):
      if TempClass.__instance is None:
        TempClass.__instance = super(TempClass,cls).__new__(cls, *args, **kwargs)
      return TempClass.__instance

    def __init__(self, *args, **kwargs):
      super(TempClass, self).__init__(*args, **kwargs)

  TempClass.__name__ = cls.__name__
  return TempClass

@singleton
class ReturnClassDecoratorSingleton:
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  ReturnClassDecoratorSingleton().check()
```

问题的本质在于，我们需要像 `__init__` 那样，创建一个类，而不是一个实例，现在创建一个返回类的装饰器，所以它是真正的单例实现了，但是问题也很明显，强扭的瓜不甜呢。

#### base class 单例

基于一个已经存在的类完成单例操作。

```python
#!/usr/bin/env python
# coding:utf8

class Singleton(object):
  __instance = None
  def __new__(cls, *args, **kwargs):
    if not isinstance(cls.__instance, cls):
      cls.__instance = object.__new__(cls, *args, **kwargs)
    return cls.__instance

class BaseClassSingleton(Singleton):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  BaseClassSingleton().check()
```

这是一个真正的类了，实际上我想说的是，它已经将实例化和单例取出的方法统一，没有将单例的获取复杂化，因此可以通过上面的 m , n , o 测试，但是因为多重继承的存在，我们如果对 `BaseClassSingleton` 进行多重继承，那么 `__new__` 可能被覆盖。 

#### metaclass 单例

我们尝试在类的创建上下文章。

```python
#!/usr/bin/env python
# coding:utf8

class Singleton(type):
  __instances = {}
  def __call__(cls, *args, **kwargs):
    if cls not in cls.__instances:
      cls.__instances[cls] = super(Singleton, cls).__call__(*args, **kwargs)
    return cls.__instances[cls]

class MetaclassSingleton(metaclass=Singleton):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  MetaclassSingleton().check()
```

这是一个真正的类，存在继承也没有影响，似乎这是 **最优解** 。

#### model 单例

事实上，我们真的需要在 python 中实现单例吗？不见得一定。 Models 在 python 中只 import 一次，所以尽量不要使用全局变量，不要过度思考...

```python
#!/usr/bin/env python
# coding:utf8

class ModelSingleton(object):
  def check(self):
    print('the instance has been created')
instance = ModelSingleton()

from ModelSingleton import instance

if __name__ == '__main__':
  instance.check()
```

它就是单例了。