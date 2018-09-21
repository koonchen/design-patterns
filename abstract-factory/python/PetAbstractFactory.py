#!/usr/bin/env python
# coding:utf8


class PetShop(object):
    def __init__(self, animal_factory=None):
        self.pet_factory = animal_factory

    def show_pet(self):
        pet = self.pet_factory()
        pet.show()


class Dog(object):
    def show(self):
        print("Dog instance has been created")


class Cat(object):
    def show(self):
        print("Cat instance has been created")


if __name__ == "__main__":
    cat_shop = PetShop(Cat)
    cat_shop.show_pet()
    dog_shop = PetShop(Dog)
    dog_shop.show_pet()
