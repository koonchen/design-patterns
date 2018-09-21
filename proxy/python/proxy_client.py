#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/8/27
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.


class Subject(object):
    def request(self):
        print("Subject instance has been created")


class RealSubject(Subject):
    def request(self):
        print("RealSubject instance has been created")


class Proxy(Subject):
    def __init__(self, subject):
        self._subject = subject

    def request(self):
        self._subject.request()


if __name__ == "__main__":
    proxy = Proxy(RealSubject())
    proxy.request()
