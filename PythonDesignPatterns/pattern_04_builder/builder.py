# -*- coding: utf-8 -*-
# @Time : 2021/11/9 2:47 PM
# @Author: lixiaomeng_someday
# @Email : 131xxxx119@163.com
# @File : builder.py

"""
1、定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。也可以说，每个产品的建造会遵循同样的流程，不过流程内的每一个步骤都不尽相同
2、应用场景：
    1、 要求一个对象有不同的表现，并且希望将对象的构造与表现解耦
    2、要求在某个时间点创建对象，但在稍后的时间点再访问

3、建造者模式的角色：

    Builder：抽象建造者(Builder)（引入抽象建造者的目的，是为了将建造的具体过程交与它的子类来实现。这样更容易扩展。一般至少会有两个抽象方法，一个用来建造产品，一个是用来返回产品。）
    ConcreteBuilder：具体建造者(CommonBuilder、SuperBuilder)（实现抽象类的所有未实现的方法，具体来说一般是两项任务：组建产品；返回组建好的产品。）
    Director：指挥者(Director)（负责调用适当的建造者来组建产品，指挥者类一般不与产品类发生依赖关系，与指挥者类直接交互的是建造者类。一般来说，指挥者类被用来封装程序中易变的部分。）
    Product：产品角色(Role)（被建造的对象）

4、既然都是构建对象，建造者模式又称为生成器模式 VS 工厂模式有什么区别

    建造者模式和工厂模式的区别
    看上边这个例子，你可能会疑惑，为什么明明可以使用工厂方法模式可以解决的问题，要换成建造者模式呢？

    通过代码可以看出，建造者模式和工厂方法模式最大的区别是，建造者模式多了一个指挥者的角色。建造者负责创建复杂对象的各个组成部分。而指挥者使用一个建造者实例控制建造的过程。

    与工厂模式相比，建造者模式一般用来创建更为复杂的对象，因为对象的创建过程更为复杂，因此将对象的创建过程独立出来组成一个新的类——指挥者类。

    建造者模式通常用于补充工厂模式的不足，尤其是在如下场景中：

    要求一个对象有不同的表现，并且希望将对象的构造与表现解耦
    要求在某个时间点创建对象，但在稍后的时间点再访问


5、建造者模式的优点：
    1、It provides clear separation and a unique layer between construction and representation of a specified object created by class.

    2、It provides better control over construction process of the pattern created.

    3、It gives the perfect scenario to change the internal representation of objects

"""


class Director:
    """
    指挥者(Director)
    （负责调用适当的建造者来组建产品，指挥者类一般不与产品类发生依赖关系，与指挥者类直接交互的是建造者类。
    一般来说，指挥者类被用来封装程序中易变的部分。）
    """
    __builder = None

    def setBuilder(self, builder):
        self.__builder = builder

    def getCar(self):

        car = Car()

        # First goes the body
        body = self.__builder.getBody()
        car.setBody(body)

        # Then engine
        engine = self.__builder.getEngine()
        car.setEngine(engine)

        # And four wheels
        i = 0
        while i < 4:
            wheel = self.__builder.getWheel()
            car.attachWheel(wheel)
            i += 1  # 因为这个控制条件缩进错误，直接导致我运行死循环，但是我找不到原因==》通过程序的执行流程不断的print("this location")判断死循环很有用

        return car


# The whole product
class Car:
    """
    Product：产品角色(Role)这是我们要通过建造者模式生产出来的产品对象
    """
    def __init__(self):
        self.__wheels = list()
        self.__engine = None
        self.__body = None

    def setBody(self, body):
        self.__body = body

    def attachWheel(self, wheel):
        self.__wheels.append(wheel)

    def setEngine(self, engine):
        self.__engine = engine

    def specification(self):
        print("body: %s" % self.__body.shape)
        print("engine horsepower: %d" % self.__engine.horsepower)
        print("tire size: %d\'" % self.__wheels[0].size)

    def __str__(self):
        """
        取代上面的specification
        :return:
        """
        info = ("body: %s" % self.__body.shape,
                "engine horsepower: %d" % self.__engine.horsepower,
                "tire size: %d\'" % self.__wheels[0].size)
        return "\n".join(info)


class Builder:
    """
    抽象建造者(Builder)（引入抽象建造者的目的，是为了将建造的具体过程交与它的子类来实现。
    这样更容易扩展。
    一般至少会有两个抽象方法，
        一个用来建造产品
        一个是用来返回产品。）
    """

    def getWheel(self):
        raise NotImplementedError

    def getEngine(self):
        raise NotImplementedError

    def getBody(self):
        raise NotImplementedError


class JeepBuilder(Builder):
    """
    具体建造者(CommonBuilder、SuperBuilder)（实现抽象类的所有未实现的方法，具体来说一般是两项任务：
        1、组建产品；
        2、返回组建好的产品。）
    """

    def getWheel(self):
        wheel = Wheel()
        wheel.size = 22
        return wheel

    def getEngine(self):
        engine = Engine()
        engine.horsepower = 400
        return engine

    def getBody(self):
        body = Body()
        body.shape = "SUV"
        return body


# Car parts
class Wheel:
    size = None


class Engine:
    horsepower = None


class Body:
    shape = None


def main():
    jeepBuilder = JeepBuilder()  # initializing the class

    director = Director()

    # Build Jeep
    print("Jeep")
    director.setBuilder(jeepBuilder)

    jeep = director.getCar()
    print("this location")
    jeep.specification()
    print(jeep)  # 这就是__str__的作用
    print("")


if __name__ == "__main__":
    main()




