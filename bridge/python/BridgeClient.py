#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/7/22
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

'''
    ┌─┐       ┌─┐
 ┌──┘ ┴───────┘ ┴──┐
 │                 │
 │       ───       │
 │  ─┬┘       └┬─  │
 │                 │
 │       ─┴─       │
 │                 │
 └───┐         ┌───┘
     │         │
     │         │
     │         │
     │         └──────────────┐
     │                        │
     │                        ├─┐
     │                        ┌─┘
     │                        │
     └─┐  ┐  ┌───────┬──┐  ┌──┘
       │ ─┤ ─┤       │ ─┤ ─┤
       └──┴──┘       └──┴──┘
           神兽保佑
           永无BUG!
'''
class Implementor(object):
  def operationImp(self):
    print('Implementor instance has been created')

class Abstraction(object):
  def operation(self):
    self._imp.operationImp()

class ConcreteImplementorA(Implementor):
  def operationImp(self):
    print('ConcreteImplementorA instance has been created')

class ConcreteImplementorB(Implementor):
  def operationImp(self):
    print('ConcreteImplementorB instance has been created')

class RefinedAbstraction(Abstraction):
  def __init__(self, cls):
    self._imp = cls()

if __name__ == '__main__':
  abstraction = RefinedAbstraction(ConcreteImplementorA)
  abstraction.operation()
  abstraction = RefinedAbstraction(ConcreteImplementorB)
  abstraction.operation()