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

# 目前最优解，这里的 __call__ 更加灵活