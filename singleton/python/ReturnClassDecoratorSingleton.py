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

# 是真正的单例了，但是问题在于创建一个类等于创建两个类，这是一个强行扭瓜的过程。