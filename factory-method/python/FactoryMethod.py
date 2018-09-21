#!/usr/bin/env python
# coding:utf8

from abc import abstractmethod, ABCMeta


class Product(metaclass=ABCMeta):
    @abstractmethod
    def productOperation(self):
        pass


class Creator(metaclass=ABCMeta):
    @abstractmethod
    def creatorOperaton(self):
        pass

    @abstractmethod
    def factoryMethod(self):
        pass


class ConcreteProduct(Product):
    def productOperation(self):
        print("Product instance has been created")


class ConcreteCreator(Creator):
    def creatorOperaton(self):
        print("Creator instance has been created")

    def factoryMethod(self):
        return ConcreteProduct()


if __name__ == "__main__":
    creator = ConcreteCreator()
    product = creator.factoryMethod()
    creator.creatorOperaton()
    product.productOperation()
