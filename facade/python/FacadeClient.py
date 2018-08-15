#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/8/15
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

class BaseSystem(object):
    def operation(self):
        print('BaseSystem instance has been created')

class Scanner(BaseSystem):
    def operation(self):
        print('Scanner instance has been created')

class Stream(BaseSystem):
    def operation(self):
        print('Stream instance has been created')

class CompilerFacade(object):
    def __init__(self):
        self._scanner = Scanner()
        self._stream = Stream()
        self._sub_systems = [self._scanner, self._stream]
    def operation(self):
        [i.operation() for i in self._sub_systems]

if __name__ == '__main__':
    facade = CompilerFacade()
    facade.operation()