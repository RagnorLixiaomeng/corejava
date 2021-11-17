package com.ragnor.corejava.chapter05_Inherit;

import java.time.LocalDate;
import java.util.Objects;

public class FocusPoint_02_JavaSuperClass {
    public static void main(String[] args) {
        // Topic-01: Java中Object类是所有类的始祖 ==》可以使用Object类型的变量引用任何类型的对象
        //                                      : Object obj = new Employeed("ragnor", 18);
        // Topic-02: 但是Object类型的变量只能用于各种值的通用持有者 ==》如果想对其中的内容进行具体的操作，还需要清楚对象的原始类型，并进行响应的类型转换
        //                                      : Employee e = (Employee) obj;
        EqualMethod();
        EqualAndInheritance();
        HashCodeMethod();
        ToStringMethod();
    }

    // FocusSubPoint-01：What is equals method ？
    public static void EqualMethod(){
        // Topic-01: Object类中的equals方法用于检测一个对象是否等于另外一个对象 ==》判断两个对象是否具有相同的引用
        Equal eq_obj = new Equal();
        Equal eq_obj2 = new Equal();
        Object eq_obj3 = new Object();
        eq_obj3 = eq_obj;
        if (eq_obj.equals(eq_obj2)){
            System.out.println("eq_obj equals eq_obj2");
        } else {
            System.out.println("eq_obj not equals eq_obj2");
        }

        if(eq_obj.equals(eq_obj3) && eq_obj.getClass() == eq_obj2.getClass()){
            System.out.println("eq_obj equals eq_obj3");
        }

    }

    protected static class Equal{
        private int secretId;

        public int getSecretId() {
            return secretId;
        }

        public void setSecretId(int secretId) {
            this.secretId = secretId;
        }
    }

    // FocusSubPoint-02：什么是相等测试与继承 What is equal and inheritance？
    public static void EqualAndInheritance(){
        // Topic-01: 思考一个问题，如果隐式与显式的参数不属于同一个类，equals方法将如何处理？
        //           上面的例子可以看出来==》发现类不匹配直接返回false

        // Topic-02: Java语言规范要求equals方法具有下面的特征：
        //  1) 自反性： 对于任何非空引用x ==》x.equals(x) 应该返回true。
        //  2) 对称性： 对于任何引用x和y，当且仅当y.equals(x)返回true ==》x.equals(y)也应该返回true
        //  3) 传递性： 对于任何引用x、y和z，如何x.equals(y)返回true，y.equals(z)返回true ==》x.equals(z)也应该返回true
        //  4) 一致性： 如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回同样的结果
        //  5) 对于任意非空引用x，x.equals(null)应该返回false

        // Todo: 我并不明白这里的章节设定想讲什么？==》equals的设计思路吗？someday
        System.out.println("corejava的作者给出的完美的equals的方案");
        // Step-01: 显式参数命名为otherObject， 稍后需要将它转换成另一个叫做other的变量。
        // Step-02: 检测this与otherObject是否引用同一个对象：
        //          if （this == otherObject） return true;
        //          这条语句只是一个优化。实际上，这是一种经常采用的形式 ==》因为计算这个等式要比一个一个的比较类中的域所付出的代价小的多
        // Step-03: 检测otherObject是否为null，如果为null，返回false ==》这项检测是很有必要的。
        //          if (otherObject == null) return false;
        // Step-04: 比较this与otherObject是否属于同一个类。如果equals的语义在每个子类中有所改变，就使用getClass 检测：
        //          if (getClass() != otherObject.getClass()) return false;
        //          如果所有的子类都拥有统一的语义，就使用instanceof检测：
        //          if (!(otherObject instanceof ClassName)) return false;
        // Step-05: 将otherObject转换为响应的类类型变量：
        //          ClassName other = (ClassName) otherObject;
        // Step-06: 现在开始对所有需要比较的域进行比较了。==》使用 == 比较基本类型域，使用equals比较对象域。
        //          return fields1 == other.fields1 && Objects.equals(fields2, othre.fields2) && ...;
        //          如果在子类中重新定义equals，就要在其中包含调用super.equals(other)。

        System.out.println("confused");

    }

    // FocusSubPoint-03：What is the hashCode（散列码） method？
    public static void HashCodeMethod(){
        // Topic-01: hash code是由对象导出的一个整型值==》hashcode是没有规律的
        HashGen hg = new HashGen("ragnor", 18, 1800, 2021, 12, 15);
        // Topic-02: hash code方法应该返回一个整型数值（可以为负），并合理的组合实例域的散列码 ==》以便于能够让各个不同的对象产生的散列码更加的均匀
        System.out.println(hg.getObjHashCode1());
        System.out.println(hg.getObjHashCode2());
        System.out.println(hg.getObjHashCode3());
    }

    public static class HashGen{
        private final String name;
        private final int age;
        private final double salary;
        private final LocalDate hireday;

        public HashGen(String name, int age, double salary, int year, int month, int day){
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.hireday = LocalDate.of(year, month, day);
        }

        public int getObjHashCode1(){
            return 7 * name.hashCode()
                    + 10 * new Integer(age).hashCode()
                    + 11 * new Double(salary).hashCode()
                    + 13 * hireday.hashCode();
        }

        public int getObjHashCode2(){
            // 优化：使用null安全的方法Objects.hashCode ,如果其参数为null，这个方法会返回0，否则返回对应参数调用hashCode的结果。
            //      另外使用静态方法Double.hashCode来避免创建Double对象
            return 7 * Objects.hashCode(name)
                    + 10 * Integer.hashCode(age)
                    + 11 * Double.hashCode(salary)
                    + 13 * Objects.hashCode(hireday);
        }

        public int getObjHashCode3(){
            // 更好的做法，组合多个散列值，可以调用Object.hash并提供多个参数==》这个方法会对各个参数调用Object.hashCode，并组合这些散列值
            return Objects.hash(name, age, salary, hireday);
        }
    }

    // FocusSubPoint-04：What is the toString method？
    public static void ToStringMethod(){
        // Topic-01: 在Object类中还有一个比较重要的方法，就是toString方法 ==》 用于返回表示对象值的字符串
        // Topic-02: 绝大多数，但不是全部的toString方法都遵循这样的格式： 类的名字 + 一对方括号括起来的域值
        FocusPoint_01_JavaDefineClass_SuperClass_SubClass.Student s_1 = new FocusPoint_01_JavaDefineClass_SuperClass_SubClass.Student();
        System.out.println(Objects.toString(s_1));
    }

}
