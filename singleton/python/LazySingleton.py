#!/usr/bin/env python
# coding:utf8

class LazySingleton(object):
  __instance = None

  @classmethod
  def getInstance(cls):
    if LazySingleton.__instance is None:
      LazySingleton.__instance = object.__new__(cls)
    return LazySingleton.__instance

  def check(self):
    print('the instance has been created')

  def __init__(self):
    raise 'createError'

if __name__ == '__main__':
  LazySingleton.getInstance().check()

# 无法通过 m n o 的原因是单例的获取有多种方式。