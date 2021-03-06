# -*- coding: utf-8 -*-
# @Time : 2021/11/9 5:10 PM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : prototype.py

"""

原型模式：本质上是拷贝模式也就是copy模式，简称副本模式

"""

import copy


class Prototype:
    _type = None
    _value = None

    def clone(self):
        pass

    def getType(self):
        return self._type

    def getValue(self):
        return self._value


class Type1(Prototype):

    def __init__(self, number):
        self._type = "Type1"
        self._value = number

    def clone(self):
        return copy.copy(self)


class Type2(Prototype):
    """ Concrete prototype. """

    def __init__(self, number):
        self._type = "Type2"
        self._value = number

    def clone(self):
        return copy.copy(self)


class ObjectFactory:
    """ Manages prototypes.
    Static factory, that encapsulates prototype
    initialization and then allows instatiation
    of the classes from these prototypes.
    """
    __type1Value1 = None
    __type1Value2 = None
    __type2Value1 = None
    __type2Value2 = None

    @staticmethod
    def initialize():
        ObjectFactory.__type1Value1 = Type1(1)
        ObjectFactory.__type1Value2 = Type1(2)
        ObjectFactory.__type2Value1 = Type2(1)
        ObjectFactory.__type2Value2 = Type2(2)

    @staticmethod
    def getType1Value1():
        return ObjectFactory.__type1Value1.clone()

    @staticmethod
    def getType1Value2():
        return ObjectFactory.__type1Value2.clone()

    @staticmethod
    def getType2Value1():
        return ObjectFactory.__type2Value1.clone()

    @staticmethod
    def getType2Value2():
        return ObjectFactory.__type2Value2.clone()


def main():
    ObjectFactory.initialize()

    instance = ObjectFactory.getType1Value1()
    print("%s: %s" % (instance.getType(), instance.getValue()))
    print(instance)

    instance = ObjectFactory.getType1Value2()
    print("%s: %s" % (instance.getType(), instance.getValue()))
    print(instance)

    instance = ObjectFactory.getType2Value1()
    print("%s: %s" % (instance.getType(), instance.getValue()))
    print(instance)

    instance = ObjectFactory.getType2Value2()
    print("%s: %s" % (instance.getType(), instance.getValue()))
    print(instance)


if __name__ == "__main__":
    main()



