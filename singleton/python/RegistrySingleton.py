#!/usr/bin/env python
# coding:utf8


class RegistrySingleton(object):
    registry = {}

    @classmethod
    def getSingleton(cls):
        if cls not in cls.registry:
            RegistrySingleton.registry[cls] = object.__new__(cls)
        return cls.registry[cls]

    def check(self):
        print("the instance has been created")

    def __init__(self):
        raise "createError"


if __name__ == "__main__":
    RegistrySingleton.getSingleton().check()

# 不能通过 m n o 测试的原因是，这里没有限制实例化，所以实例化和注册表是两种不同的实例化方式。
