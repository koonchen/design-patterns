#!/usr/bin/env python
# coding:utf8
# @Date    : 2018/8/23
# @Author  : Koon
# @Link    : koon.cool
# When I wrote this, only God and I understood what I was doing. Now, God only knows.

import weakref

class FlyweightMeta(type):

    def __new__(mcs, name, parents, dct):
        """
        Set up object pool
        :param name: class name
        :param parents: class parents
        :param dct: dict: includes class attributes, class methods,
        static methods, etc
        :return: new class
        """
        dct['pool'] = weakref.WeakValueDictionary()
        return super(FlyweightMeta, mcs).__new__(mcs, name, parents, dct)

    @staticmethod
    def _serialize_params(cls, *args, **kwargs):
        """
        Serialize input parameters to a key.
        Simple implementation is just to serialize it as a string
        """
        args_list = list(map(str, args))
        args_list.extend([str(kwargs), cls.__name__])
        key = ''.join(args_list)
        return key

    def __call__(cls, *args, **kwargs):
        key = FlyweightMeta._serialize_params(cls, *args, **kwargs)
        pool = getattr(cls, 'pool', {})

        instance = pool.get(key)
        if instance is None:
            instance = super(FlyweightMeta, cls).__call__(*args, **kwargs)
            pool[key] = instance
        return instance

def with_metaclass(meta, *bases):
    """ Provide python cross-version metaclass compatibility. """
    return meta("NewBase", bases, {})

class Card2(with_metaclass(FlyweightMeta)):

    def __init__(self, *args, **kwargs):
        # print('Init {}: {}'.format(self.__class__, (args, kwargs)))
        pass

if __name__ == '__main__':
    # Tests with metaclass
    instances_pool = getattr(Card2, 'pool')
    cm1 = Card2('10', 'h', a=1)
    cm2 = Card2('10', 'h', a=1)
    cm3 = Card2('10', 'h', a=2)
    print(cm1)
    print(cm2)
    print(cm3)