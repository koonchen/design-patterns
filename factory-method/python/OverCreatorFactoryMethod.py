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