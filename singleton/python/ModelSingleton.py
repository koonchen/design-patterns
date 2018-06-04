#!/usr/bin/env python
# coding:utf8

class ModelSingleton(object):
  def check(self):
    print('the instance has been created')
instance = ModelSingleton()

from ModelSingleton import instance

if __name__ == '__main__':
  instance.check()