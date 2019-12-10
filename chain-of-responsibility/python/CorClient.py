#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/9/12
# @Author  : Koon
# @Link    : chenzeping.com
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

from __future__ import absolute_import
from __future__ import division
from __future__ import print_function


class Request(object):
    def __init__(self, _type):
        self._type = _type


class Handler(object):
    def __init__(self, _next):
        self._next = _next

    def handle_request(self, request):
        self._next.handle_request(request)


class ConcreteHandler1(Handler):
    def handle_request(self, request):
        if request._type == "ConcreteHandler1":
            print("ConcreteHandler1 instance has been created")
        else:
            self._next.handle_request(request)


class ConcreteHandler2(Handler):
    def handle_request(self, request):
        if request._type == "ConcreteHandler2":
            print("ConcreteHandler2 instance has been created")
        else:
            self._next.handle_request(request)


class DefaultHandler(Handler):
    def __init__(self):
        pass

    def handle_request(self, request):
        print("Without this request type")


if __name__ == "__main__":
    handler = ConcreteHandler1(ConcreteHandler2(DefaultHandler()))
    handler.handle_request(Request("ConcreteHandler1"))
    handler.handle_request(Request("ConcreteHandler2"))
    handler.handle_request(Request("ConcreteHandler3"))
