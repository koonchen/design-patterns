# factory-method-pattern

## 博客链接

[空城/初探工厂方法模式](http://koon.cool/factory-method.html)

## 代码链接

> - [java 简单](./java/FactoryMethod.java) , [java 改进](./java/GenericsFactoryMethod.java)
> - [py 简单](./python/FactoryMethod.py) , [py 改进](./python/OverCreatorFactoryMethod.py)
> - [py 字典](./python/DictionaryFactoryMethod.py)

## 要点

> 定义一个创建对象的接口，让子类决定实例化哪一个类。让一个类的实例化延迟到其子类。

比如我们想在有一个应用，它可以显示多个文档。在这个应用框架下，我们创建了两个抽象类，分别是 `Application` 和 `Document` ， `Application` 类负责管理 `Document` 类，用户从菜单的选择或者 open 来创建相应的文档，也就是说， `Application` 类不能预测哪一个 `Document` 将会被创建。

Factory Method 给我们的解决方式是：由 `Application` 的子类来重定义  `CreateDocument` 方法，来返回相应的 `Document` ，一旦一个需要的 `Application` 子类被创建了，那么它就能创建相关的文档了。

用 `MyApplication` 和 `MyDocument` 来 **泛化** `Application` 和 `Document` ，而原本 `Application` 和 `Document` 之间的 **聚合** 变成了 **依赖** 。

> *什么情况才使用工厂方法模式？*
>
> - 一个类不知道它所创建对象的具体类的时候；
> - 一个类希望由其子类来指定创建对象的时候；
> - 当类所创建对象的职责被委托给多个帮助子类中的一个，并且希望将哪一个子类是代理者这一信息局部化的时候。

因此定义以下参与者：

> - Product ( Document ) — 定义工厂方法所创建对象的接口。
> - ConcreteProduct ( MyDocument ) — 实现 Product 接口。
> - Creator ( Application ) — 声明工厂方法，创建一个 Product 对象 ( 或省略 ) 并返回。
> - ConcreteCreator ( MyApplication ) — 重定义工厂方法，创建一个 ConcreateProduct 对象并返回。

工厂方法的一个 **潜在缺点** 在于用户可能仅仅为了创建一个特定的 ConcreteProduct 对象，就不得不从 Creator 开始创建，再创建相应的子类。但是当 Creator 的子类不是必需品时，我们还要考虑类的演化问题；或者就照着这繁复的公式完成。

工厂方法模式有另外两种效果：

> 一、为子类提供挂钩 ( hook )
>
> 所谓 hook ，指空实现或默认实现。用工厂方法在一个类的内部创建对象通常比直接创建对象更加灵活。比如我在之前的 `Document` 下再创建一个方法 `CreateFileDialog` ，这个方法可以创建一个文件对话框，那么对于 `MyDocument` 就能覆盖该方法，创建自己的文件对话框。
>
> 二、连接平行的类层次
>
> 假设现在有一个图形界面，我们可以对图形界面的文字操作，也能对线条进行操作，能托拉拽的内容非常多，但每个对象的操作又各不相同，我们就能通过一个 `Figure` 接口控制图形，用 `Manipulator` 接口定义操作，而只有 `Figure` 才能创建相应的 `Manipulator` ，一个平行的类层次就出现了。

## 实现

工厂方法的实现上，对于 Creator 是否需要给出默认的工厂方法实现给出了讨论，推荐的情况是 **不给出具体实现** ，因为在这避免了不可预见类的问题；关于参数，需要唯一标识，可以是 id 之类的东西 ( Unidraw 框架对于每一个类拥有一个类标识符 )，保证不重名用名字当然没有问题；不同的语言将会存在不同的变化和警告，这是当然的。

### 基础工厂方法实现

#### java 实现

```java
interface Product {
  void productOperation();
}

interface Creator {
  Product factoryMethod();
  void creatorOperation();
}

class ConcreteProduct implements Product {

  @Override
  public void productOperation() {
    System.out.println("Product instance has been created");
  }
}

class ConcreteCreator implements Creator {

  @Override
  public Product factoryMethod() {
    return new ConcreteProduct();
  }

  @Override
  public void creatorOperation() {
    System.out.println("Creator instance has been created");
  }
}

public class FactoryMethod {
  public static void main(String[] args) {
    Creator creator = new ConcreteCreator();
    Product product = creator.factoryMethod();
    creator.creatorOperation();
    product.productOperation();
  }
}
```

实现平行层次的类也就和上面一样的完成，这里不再赘述，值得注意的是，上面的代码显示出了该方法的问题，就是上文提及的：我们的目标如果仅仅只是创建一个 `ConcreteProduct` 类的实例，但是我们需要从 `Prodct` 这个接口开始，过于繁复。

现在我们的目标是跳过 `ConcreteCreator` 的创建，直接创建 `ConcreteProduct` 。

```java
abstract class GenericsProduct {
  abstract void productOperation();
}

interface GenericsCreator {
  GenericsProduct factoryMethod();
  void creatorOperation();
}

class StandardCreator<T extends GenericsProduct> implements GenericsCreator {

  Class<T> clazz;

  public StandardCreator(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public GenericsProduct factoryMethod() {
    try {
      T temp = clazz.newInstance();
      return temp;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void creatorOperation() {
    System.out.println("GenericsCreator instance has been created");
  }
}

class GenericsConcreteProduct extends GenericsProduct {
  @Override
  public void productOperation() {
    System.out.println("GenericsProduct instance has been created");
  }
}

public class GenericsFactoryMethod {
  public static void main(String[] args) {
    GenericsCreator creator = new StandardCreator(GenericsConcreteProduct.class);
    GenericsProduct product = creator.factoryMethod();
    creator.creatorOperation();
    product.productOperation();
  }
}
```

因为 java 中没有 c++ 的 `template` ，所以这里使用反射和泛型 ( 虽然个人认为这个都不能叫泛型， jvm 的类型擦除历史包袱沉重，暂且不讨论 ) 完成 ( 感谢这篇文章：[Reflecting generics](https://www.artima.com/weblogs/viewpost.jsp?thread=208860) )。

#### python 实现

python 因为多重继承的存在，其实并不需要接口的存在，这里使用 abc 模块来实现接口的功能。

```python
#!/usr/bin/env python
# coding:utf8

from abc import abstractmethod, ABCMeta

class Product(metaclass=ABCMeta):
  @abstractmethod
  def productOperation(self): pass

class Creator(metaclass=ABCMeta):
  @abstractmethod
  def creatorOperaton(self): pass
  @abstractmethod
  def factoryMethod(self): pass

class ConcreteProduct(Product):
  def productOperation(self):
    print('Product instance has been created')

class ConcreteCreator(Creator):
  def creatorOperaton(self):
    print('Creator instance has been created')
  def factoryMethod(self):
    return ConcreteProduct();

if __name__ == '__main__':
  creator = ConcreteCreator()
  product = creator.factoryMethod()
  creator.creatorOperaton()
  product.productOperation()
```

那么在 python 中如何省略 `ConcreteCreator` 的创建呢？

注意泛型是一种类型声明的方法，属于多态的概念， python 没有泛型，跟强弱、静态动态没有直接关联。

> PS: 静态语言利用多态机制 ( 包括泛型 ) ，实现动态语言的便利，同时避免动态语言的性能和安全问题。

```python
#!/usr/bin/env python
# coding:utf8

from abc import abstractmethod, ABCMeta

class Product(metaclass=ABCMeta):
  @abstractmethod
  def productOperation(self): pass

class Creator(object):
  __clazz = None
  def __init__(self,name):
    self.__clazz = name
  def creatorOperaton(self):
    print('Creator instance has been created')
  def factoryMethod(self):
    return self.__clazz()

class ConcreteProduct(Product):
  def productOperation(self):
    print('Product instance has been created')

if __name__ == '__main__':
  creator = Creator(ConcreteProduct)
  product = creator.factoryMethod().productOperation()
```

------

简单来说，工厂方法模式需要按部就班，可以认为是对构造函数的补充，当我们的实际类多样，而构造类不够明确表达时，这是一个好办法。

### python 的其他实现

从上面的内容我们得知 python 不需要接口，并且动态实例化非常的简单，那么如何确切在 python 中使用工厂方法呢？

我从 [python-patterns/creational/factory_method.py](https://github.com/faif/python-patterns/blob/master/creational/factory_method.py) 找到了一个例子，下面的代码实现了一个字典。

```python
#!/usr/bin/env python
# coding:utf8

class ChineseGetter(object):

  def __init__(self):
    self.trans = dict(dog="狗", cat="猫")

  def get(self, item):
    return self.trans.get(item, str(item))

class EnglishGetter(object):

  def get(self, item):
    return str(item)

def get_localizer(language="English"):
  languages = dict(English=EnglishGetter, Chinese=ChineseGetter)
  return languages[language]()

if __name__ == '__main__':
  e, c = get_localizer(), get_localizer(language="Chinese")
  for item in "dog parrot cat bear".split():
    print(e.get(item), c.get(item))
```

`get_localizer` 方法是工厂方法，它将返回的是我们将创建的语言实例，这里以英语为例，将其翻译成中文，其中 `dict.get` 方法的第二项为默认值。

在 django 框架中，也能看到工厂方法模式的身影—— [NEW Django Design Patterns](http://django.wikispaces.asu.edu/*NEW*+Django+Design+Patterns) ；之后的抽象工厂模式也是工厂方法模式的一个子集，也能看出这一模式的重要性。