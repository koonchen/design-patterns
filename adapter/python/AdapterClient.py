#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/7/19
# @Author  : Koon
# @Link    : chenzeping.com
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

from abc import abstractmethod, ABCMeta


class Target(metaclass=ABCMeta):
    @abstractmethod
    def request(self):
        pass


class Adaptee(object):
    def specificRequest(self):
        print('"Adaptee instance has been created"')


class Adapter(Target):
    def __init__(self):
        self._adaptee = Adaptee()

    def request(self):
        self._adaptee.specificRequest()


if __name__ == "__main__":
    adapter = Adapter()
    adapter.request()
