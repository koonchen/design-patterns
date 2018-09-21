class Component(object):
    def operation(self):
        print("Component instance has been created")


class ConcreteComponent(Component):
    def operation(self):
        print("ConcreteComponent instance has been created")


class Decorator(Component):
    def __init__(self, component):
        self._component = component

    def operation(self):
        print("Decorator instance has been created")


class ConcreteDecoratorA(Decorator):
    def addedState(self):
        print("Added state")


class ConcreteDecoratorB(Decorator):
    def addedBehavior(self):
        print("Added behavior")


if __name__ == "__main__":
    component = ConcreteComponent()
    componenta = ConcreteDecoratorA(component)
    componentb = ConcreteDecoratorB(component)
    component.operation()
    componenta.addedState()
    componentb.addedBehavior()
