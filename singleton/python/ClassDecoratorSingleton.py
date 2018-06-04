#!/usr/bin/env python
# coding:utf8

class Singleton(object):
  def __init__(self, cls):
    self._cls = cls
    self._instance = {}

  def __call__(self):
    if self._cls not in self._instance:
      self._instance[self._cls] = self._cls()
    return self._instance[self._cls]

@Singleton
class ClassDecoratorSingleton(object):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  ClassDecoratorSingleton().check()