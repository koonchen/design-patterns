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


class Dog(object):
    def __init__(self):
        self._name = "Dog"

    def bark(self):
        return "woof!"


class Cat(object):
    def __init__(self):
        self._name = "Cat"

    def meow(self):
        return "meow!"


class Human(object):
    def __init__(self):
        self._name = "Human"

    def speak(self):
        return "hello!"


class Adapter(object):
    def __init__(self, obj, **kwargs):
        self._obj = obj
        self.__dict__.update(kwargs)

    def __getattr__(self, item):
        return getattr(self._obj, item)

    def original_dict(self):
        return self._obj.__dict__


if __name__ == "__main__":
    dog = Dog()
    objects = []
    objects.append(Adapter(dog, make_noise=dog.bark()))
    cat = Cat()
    objects.append(Adapter(cat, make_noise=cat.meow()))
    human = Human()
    objects.append(Adapter(human, make_noise=human.speak()))
    for obj in objects:
        print(f"A {obj._name} goes {obj.make_noise}")
