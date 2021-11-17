# -*- coding: utf-8 -*-
# @Time : 2021/11/9 11:33 AM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : view.py

"""
（视图View） - 界面设计人员进行图形界面设计。
"""

from PythonDesignPatterns.pattern_01_mvc.model import Person


def showAllView(staff_list):
    print('In our db we have %i users. Here they are:' % len(staff_list))
    for item in staff_list:
        print(item.name())


def startView():
    print('MVC - the simplest example')
    print('Do you want to see everyone in my db?[y/n]')


def endView():
    print('Goodbye!')
