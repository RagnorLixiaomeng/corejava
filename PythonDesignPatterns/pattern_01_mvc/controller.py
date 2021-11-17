# -*- coding: utf-8 -*-
# @Time : 2021/11/9 11:37 AM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : controller.py
"""

Controller： （控制器Controller）- 负责转发请求，对请求进行处理。
"""

from PythonDesignPatterns.pattern_01_mvc.model import Person
from PythonDesignPatterns.pattern_01_mvc import view


def showAll():
    # gets list of all Person objects
    people_in_db = Person.getAll()
    # calls view
    return view.showAllView(people_in_db)


def start():
    view.startView()
    option = input()
    if option == 'y':
        return showAll()
    else:
        return view.endView()


if __name__ == "__main__":
    # running controller function
    start()




# MVC - the simplest example
# Do you want to see everyone in my db?[y/n]
# y
# In our db we have 2 users. Here they are:
# ragnor li
# qingning ye