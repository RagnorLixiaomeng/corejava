# -*- coding: utf-8 -*-
# @Time : 2021/11/9 2:04 PM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : singleton.py

"""这种模式将类的实例化限制为一个对象。它是一种创建模式，只涉及一个类来创建方法和指定的对象。

毛哥说过：比如数据库的连接池最大允许200个实例，那你调用数据库对象用单例模式就是最合适的
"""


class Singleton(object):
    __instance = None  # 类的私有属性

    @staticmethod
    def getInstance():
        """Static access method"""
        if Singleton.__instance == None:
            Singleton()
        return Singleton.__instance

    def __init__(self):
        """Virtually private constructor"""
        if Singleton.__instance != None:
            raise Exception("This class is a singleton!")
        else:
            Singleton.__instance = self


obj1 = Singleton()
print(obj1)

# obj2 = Singleton()  # 这里触发Exception("This class is a singleton!") 也就是不允许你再使用这种方法进行实例化，只能是调用类的getInstance方法
# print(obj2)

obj2 = Singleton.getInstance()
print(obj2)

obj3 = Singleton.getInstance()
print(obj3)


# <__main__.Singleton object at 0x7fc559aa0208>
# <__main__.Singleton object at 0x7fc559aa0208>
# <__main__.Singleton object at 0x7fc559aa0208>

