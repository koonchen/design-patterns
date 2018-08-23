#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/8/23
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

from enum import Enum, unique

@unique
class FlyweightType(Enum):
    SHARED = 0
    UNSHARED = 1

class Flyweight(object):
    def operation(self):
        print('Flyweight instance has been created')

class ConcreteFlyweight(Flyweight):
    def operation(self):
        print('ConcreteFlyweight instance has been created')

class UnsharedConcreteFlyweight(Flyweight):
    def operation(self):
        print('UnsharedConcreteFlyweight instance has been created')

class FlyweightFactory(object):
    def __init__(self):
        self._flyweight_factory = dict()
    def get_flyweight(self, flyweight_type):
        flyweight = self._flyweight_factory.get(flyweight_type)
        if flyweight is None:
            if flyweight_type == FlyweightType.SHARED:
                flyweight = ConcreteFlyweight()
                self._flyweight_factory[flyweight_type] = flyweight
            elif flyweight_type == FlyweightType.UNSHARED:
                flyweight = UnsharedConcreteFlyweight()
                self._flyweight_factory[flyweight_type] = flyweight
        return flyweight

if __name__ == '__main__':
    factory = FlyweightFactory()
    factory.get_flyweight(FlyweightType.SHARED).operation()
    factory.get_flyweight(FlyweightType.UNSHARED).operation()