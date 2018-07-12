#!/usr/bin/env python
# coding:utf8

class Product(object):
  def __init__(self, builder):
    self._name = builder._name
    self._describe = builder._describe

  def __repr__(self, *args, **kwargs):
    return 'name: '+self._name+'\ndescribe: '+self._describe

class ConcreteBuilder(object):
  def withName(self, name):
    self._name = name
    return self
  def withDescribe(self, describe):
    self._describe = describe
    return self
  def getsult(self):
    return Product(self)

if __name__ == '__main__':
  product = ConcreteBuilder()\
    .withName('product')\
    .withDescribe('Product instance has been created')\
    .getsult()
  print(product)