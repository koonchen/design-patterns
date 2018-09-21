#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/7/26
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

"""
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
"""


class Component(object):
    def operation(self):
        pass

    def add(self, obj):
        pass

    def remove(self, obj):
        pass

    def get_child(self, index):
        pass


class Leaf(Component):
    def __repr__(self):
        return "a leaf"

    def operation(self):
        print("Leaf instance has been created")


class Composite(Component):
    def __init__(self):
        self._array = list()

    def add(self, obj):
        self._array.append(obj)

    def remove(self, obj):
        self._array.remove(obj)

    def get_child(self, index):
        return self._array[index]


if __name__ == "__main__":
    composite = Composite()
    leaf = Leaf()
    composite.add(leaf)
    composite.get_child(0).operation()
