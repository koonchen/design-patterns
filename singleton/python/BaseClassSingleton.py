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

# 多继承可能覆盖已有的 __new__ 逻辑