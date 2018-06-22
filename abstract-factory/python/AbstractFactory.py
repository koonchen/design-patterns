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