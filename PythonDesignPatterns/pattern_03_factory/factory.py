# -*- coding: utf-8 -*-
# @Time : 2021/11/9 2:20 PM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : factory.py

"""
1、工厂模式是属于建造者模式的一种：它提供了了一种创建对象的方式
2、在工厂模式中创建对象时无需向客户端公开逻辑并使用公共接口引用新创建的对象

"""


class Button(object):
    html = ""

    def get_html(self):
        return self.html


class Image(Button):
    html = "<img></img>"
    cls_attr = "ragnor.li"


class Input(Button):
    html = "<input></input>"


class Flash(Button):
    html = "<obj></obj>"


class ButtonFactory():

    def create_button(self, btn_type):
        targetclass = btn_type.capitalize()  # 大写第一个字母 ==>因为那几Image\Input\Flash都是大写的
        print(globals()["Image"].cls_attr)  # 理解这个就理解了下面为什么了，==》🤩这样可以实现python把类给参数化了
        return globals()[targetclass]()  # [Image]().get_html()


button_obj = ButtonFactory()
button = ['image', 'input', 'flash']


for b in button:
    print(button_obj.create_button(b).get_html())






