#!/usr/bin/env python
# coding:utf8

class Prototype(object):
  value = 'default'
  def clone(self, **kwargs):
    obj = self.__class__()
    obj.__dict__.update(kwargs)
    return obj

class PrototypeDispatcher(object):
  def __init__(self):
    self._objs={}
  def get_objects(self):
    return self._objs
  def register_obj(self, name, obj):
    self._objs[name]=obj
  def unregister_obj(self, name):
    del self._objs[name]

if __name__ == '__main__':
  prototypeDispatcher = PrototypeDispatcher()
  prototype = Prototype()
  a = prototype.clone(value='a')
  b = prototype.clone(value='b')
  prototypeDispatcher.register_obj('objDefault', prototype)
  prototypeDispatcher.register_obj('objA', a)
  prototypeDispatcher.register_obj('objB', b)
  print([{n:p.value} for n, p in prototypeDispatcher.get_objects().items()])