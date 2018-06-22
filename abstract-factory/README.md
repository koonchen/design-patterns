# 抽象工厂模式

## 博客链接

[空城/初探抽象工厂模式](http://koon.cool/abstract-factory.html)

## 代码链接

> - [java 简单](./java/AbstractFactory.java) , [java 改进](./java/AbstractFactoryUnify.java)
> - [py 简单](./python/AbstractFactory.py)
> - [py 宠物店](./python/PetAbstractFactory.py)

## 要点

> 提供一个创建一系列相关或相互依赖对象的接口，且无需指定它们具体的类。

假设现在有一个应用界面，比如一个视频应用。其中包含了许多视觉模块，比如视频栏、评论栏。这些不同的模块拥有各式的外观，比如按钮、滚动条等。因此我们如果对某一个外观进行编码，在复用的时候可能存在难以改变视感风格的问题。

因此可以定义一个抽象类 `WidgetFactory` ，通过泛化的子类 `MotifWidgeFactory` 或者 `PMWidgeFactory` 可以创建每一类视感风格的窗口组件操作比如 `CreateScrollBar` 等 ，这样我们在获取某一个具体的窗口组件时，对工厂子类和具体实现之间只有依赖关系，而客户仅与抽象类（工厂）进行交互，而不需要使用特定的具体类的接口。

> *什么时候适用这种模式呢？*
> - 一个系统要独立于它的产品的创建、组合和表示时；
> - 一个系统要用多个产品系列中的一个来配置时；
> - 当你要强调一系列相关的产品对象的设计以便进行联合适用的时候；
> - 当你提供一个产品类库，而只想显示它们的接口而不是实现的时候。

他拥有以下参与者：

> - AbstractFactory—创建抽象产品对象的操作接口。
> - ConcreteFactory—实现创建具体产品对象的操作。
> - AbstractProduct—为一类产品对象声明的接口。
> - ConcreteProduct—实现 AbstractProduct 接口，具体创建的产品对象。
> - Client —仅使用 AbstractFactory 和 AbstractProduct 类声明的接口。

所以抽象工厂模式的三个优点和一个缺点：

1. 有效分离了具体的类，它将客户与类的实现进行了分离，客户仅仅使用接口操纵实例，而具体实现不会再客户代码中。
2. 配置简单化，一个具体工厂的实现在应用中仅出现了一次，就是它初始化的时候。这使得改变一个实现很容易。
3. 有利于产品一致性，当一个系列中的产品对象被设计成一起工作时，一个类中只有一个对象被实现，好比只需要某一个形式的按钮配上某一个形式的主题，而不会出现特定情况下的两种按钮，这在抽象工厂容易实现。
4. 难以支持新种类的产品，或者说是新产品的实现，因为一旦接口上进行了改变，子类也要进行改变。

> *他与工厂方法有什么区别呢？*
>
> 初看起来他们很像，他们的区别在于：
>
> 首先工厂方法，工厂被实现在方法中，准确地讲是实现在 Creater 中，只有一个 Creater 所以他创建的内容 Product 是一个类别的，而后在生产者子类的方法中实例化不同的产品。
>
> ![cover](http://p9ffq89zz.bkt.clouddn.com/img/blog/factory-method.png)
>
> 抽象工厂，工厂被实现在类中，从创建不同的工厂类就已经决定了未来我们将实例化不同的一系列产品，我们有一个很明确的目的，比如说中国货或者美国货，他们就可以代表是两个系列。
>
> ![cover](http://p9ffq89zz.bkt.clouddn.com/img/blog/abstract-factory.jpg)
>
> 在抽象工厂中，**最通常的方法是为每一个产品定义一个工厂方法**，从上图我们可以发现，抽象工厂仅仅是多个工厂方法的结合。从工厂方法的平行层次我们能更直观发现这一点。
>
> ![cover](http://p9ffq89zz.bkt.clouddn.com/img/blog/horizontal-factory-method.jpg)
>
> **所以最重要的区别是，抽象工厂目的是生产不同系列的产品，而工厂方法目的是生产相同系列的产品。**
>
> 也因此，虽然工厂方法是抽象工厂的一部分，但是工厂方法中没有抽象工厂的缺陷。

## 实现

> 当有多个可能的产品系列，具体工厂也可以使用 Prototype 模式实现，这里暂且不讨论。

*关于抽象工厂的缺陷如何处理？*

前面说道抽象工厂对于扩展有着一定缺陷，如果我们想增加一类新的产品，需要改变 `AbstractFactory` 来创建新的方法，然后在子类实现上实现这个方法用以创建新的产品。一个更加灵活但不太安全的设计是给创建对象的方法一个参数，该参数指定创建对象的种类，所有的产品将返回相同的接口，客户也就不能区分一个特定的产品类别，可读性会变差，但是这是折衷可行的方法。

### 基础抽象工厂实现

#### java 实现

```java
interface AbstractProductA {
  void productAOperation();
}

interface AbstractProductB {
  void productBOperation();
}

interface Factory {
  AbstractProductA createProductA();
  AbstractProductB createProductB();
}

class ProductA1 implements AbstractProductA {

  @Override
  public void productAOperation() {
    System.out.println("ProductA1 instance has been created");
  }
}

class ProductA2 implements AbstractProductA {

  @Override
  public void productAOperation() {
    System.out.println("ProductA2 instance has been created");
  }
}

class ProductB1 implements AbstractProductB {

  @Override
  public void productBOperation() {
    System.out.println("ProductB1 instance has been created");
  }
}

class ProductB2 implements AbstractProductB {

  @Override
  public void productBOperation() {
    System.out.println("ProductB2 instance has been created");
  }
}

class ConcreteFactory1 implements Factory {

  @Override
  public AbstractProductA createProductA() {
    return new ProductA1();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ProductB1();
  }
}

class ConcreteFactory2 implements Factory {

  @Override
  public AbstractProductA createProductA() {
    return new ProductA2();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ProductB2();
  }
}

public class AbstractFactory {
  public static void main(String[] args) {
    Factory factory = new ConcreteFactory1();
    factory.createProductA().productAOperation();
    factory.createProductB().productBOperation();
    factory = new ConcreteFactory2();
    factory.createProductA().productAOperation();
    factory.createProductB().productBOperation();
  }
}
```

根据书上对于抽象工厂的改进，我们下一步需要对工厂中创建实例的方法加上参数，统一成一个方法，所有的创建都通过这里，这样一来我们再添加新的产品也就无需改进接口了，此外还需要统一产品接口。

```java
interface ProductUnify {
  void productOperation();
}

interface FactoryUnify {
  ProductUnify make();
}

class ProductUnifyA1 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyA1 instance has been created");
  }
}

class ProductUnifyA2 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyA2 instance has been created");
  }
}

class ProductUnifyB1 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyB1 instance has been created");
  }
}

class ProductUnifyB2 implements ProductUnify {

  @Override
  public void productOperation() {
    System.out.println("ProductUnifyB2 instance has been created");
  }
}

class ConcreteFactoryUnifyA1 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyA1();
  }
}

class ConcreteFactoryUnifyA2 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyA2();
  }
}

class ConcreteFactoryUnifyB1 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyB1();
  }
}

class ConcreteFactoryUnifyB2 implements FactoryUnify {

  @Override
  public ProductUnify make() {
    return new ProductUnifyB2();
  }
}

public class AbstractFactoryUnify {
  public static void main(String[] args) {
    FactoryUnify factory = new ConcreteFactoryUnifyA1();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyA2();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyB1();
    factory.make().productOperation();
    factory = new ConcreteFactoryUnifyB2();
    factory.make().productOperation();
  }
}
```

> 我们可以发现，现在的抽象工厂和工厂方法非常非常像，代码逻辑完全相同，含义也近似相同，但注意目的是不一样的，再次强调，前者的目的是创造多个系列，而后者的目的是创造一个系列！

个人认为，如果项目庞大，产品系列很多，那么不如用修改麻烦的方法（前者），因为这样创建在内存的工厂会少；相对的，如果系列不大，用工厂方法实现的抽象工厂更加灵活。我会选择前者。

#### python 实现

```python
#!/usr/bin/env python
# coding:utf8

from abc import abstractmethod, ABCMeta

class AbstractProductA(metaclass=ABCMeta):
  @abstractmethod
  def productAOperation(self): pass

class AbstractProductB(metaclass=ABCMeta):
  @abstractmethod
  def productBOperation(self): pass

class Factory(metaclass=ABCMeta):
  @abstractmethod
  def createProductA(self): pass
  @abstractmethod
  def createProductB(self): pass

class ProductA1(AbstractProductA):
  def productAOperation(self):
    print('ProductA1 instance has been created')

class ProductA2(AbstractProductA):
  def productAOperation(self):
    print('ProductA2 instance has been created')

class ProductB1(AbstractProductB):
  def productBOperation(self):
    print('ProductB1 instance has been created')

class ProductB2(AbstractProductB):
  def productBOperation(self):
    print('ProductB2 instance has been created')

class ConcreteFactory1(Factory):
  def createProductA(self):
    return ProductA1()
  def createProductB(self):
    return ProductB1()

class ConcreteFactory2(Factory):
  def createProductA(self):
    return ProductA2()
  def createProductB(self):
    return ProductB2()

if __name__ == '__main__':
  factory = ConcreteFactory1()
  factory.createProductA().productAOperation()
  factory.createProductB().productBOperation()
  factory = ConcreteFactory2()
  factory.createProductA().productAOperation()
  factory.createProductB().productBOperation()
```

如上 java 改进的 python 代码和之前实现的 [工厂方法实现](http://koon.cool/factory-method.html#%E5%9F%BA%E7%A1%80%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95%E5%AE%9E%E7%8E%B0) 也是一回事情，这里的代码就省略了。

***

### python 其他实现

python 真的只能这样完成抽象工厂吗？是否可以完成抽象工厂的同时满足添加产品的任务？答案是可以的。

我从 [python-patterns/creational/abstract_factory.py](https://github.com/faif/python-patterns/blob/master/creational/abstract_factory.py) 找到了一个例子，下面的代码实现了一个宠物店。

```python
#!/usr/bin/env python
# coding:utf8

class PetShop(object):
  def __init__(self, animal_factory = None):
    self.pet_factory = animal_factory

  def show_pet(self):
    pet = self.pet_factory()
    pet.show()

class Dog(object):
  def show(self):
    print('Dog instance has been created')

class Cat(object):
  def show(self):
    print('Cat instance has been created')

if __name__ == '__main__':
  cat_shop = PetShop(Cat)
  cat_shop.show_pet()
  dog_shop = PetShop(Dog)
  dog_shop.show_pet()
```

在这里 `Cat` 和 `Dog` 是抽象工厂的两个实现，当增加宠物类别，也不会出现更改已有特定子类的事情。

和上文提及的抽象工厂包含的缺陷及解决一样，这样处理和工厂方法本质上没有区别，将多个系列的产品混杂在一起，是一种灵活但耗费内存的处理方法。