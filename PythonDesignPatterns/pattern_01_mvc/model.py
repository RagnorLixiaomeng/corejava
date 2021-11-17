# -*- coding: utf-8 -*-
# @Time : 2021/11/9 11:32 AM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : model.py

"""
（模型Model） - 程序员编写程序应有的功能（实现算法等等）、数据库专家进行数据管理和数据库设计(可以实现具体的功能)。
"""

import json


class Person(object):
    def __init__(self, first_name=None, last_name=None):
        self.first_name = first_name
        self.last_name = last_name

    # returns Person name, ex: John Doe
    def name(self):
        return ("%s %s" % (self.first_name, self.last_name))

    @classmethod
    # returns all people inside db.txt as list of Person objects
    def getAll(self):
        with open("db.txt", "r") as database:
            result = []
            json_list = json.load(database)

            for item in json_list["staff_info"]:
                person = Person(item['first_name'], item['last_name'])
                result.append(person)
            return result


def main():
    for obj in Person().getAll():
        print(obj.name())

    # import json
    #
    # # Opening JSON file
    # f = open('db.txt')
    #
    # # returns JSON object as
    # # a dictionary
    # data = json.load(f)
    #
    # # Iterating through the json
    # # list
    # for i in data['staff_info']:
    #     print(i)
    #
    # # Closing file
    # f.close()

if __name__ == "__main__":
    main()
