#!/usr/bin/env python
# coding:utf8

def singleton(cls):
  __instances = {}
  def getinstance():
    if cls not in __instances:
      __instances[cls] = cls()
    print(__instances[cls])
    return __instances[cls]
  return getinstance

@singleton
class FunctionDecoratorSingleton(object):
  def check(self):
    print('the instance has been created')

if __name__ == '__main__':
  FunctionDecoratorSingleton().check()

# 这里无法通过 o m n 测试的原因是，装饰器本身等于一个函数调用，
# 而用 type 创建不经过装饰器，所以不一样。