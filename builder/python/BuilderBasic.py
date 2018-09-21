#!/usr/bin/env python
# coding:utf8


class Product(object):
    def withNameDes(self, **kwargs):
        self._name = kwargs.get("name")
        self._des = kwargs.get("describe")

    def __repr__(self, *args, **kwargs):
        return "name: " + self._name + "\ndescribe: " + self._des


class Builder(object):
    def buildePart(self):
        pass

    def getResult(self):
        pass


class ConcreteBuilder(Builder):
    def buildePart(self):
        self._product = Product()
        self._product.withNameDes(
            name="product", describe="Product instance has been created"
        )

    def getResult(self):
        print(self._product)
        return self._product


if __name__ == "__main__":
    builder = ConcreteBuilder()
    builder.buildePart()
    builder.getResult()
