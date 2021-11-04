package com.ragnor.corejava.chapter04_ClassAndObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

/** 这个类就是为了练习文档注释
 * @author xiaomengli
 * @version 0.0.1
 *
 * */
public class FocusPoint_09_JavaDocumentAnnotationTheCode {

    public static void main(String[] args) {
        // Topic-01:  JDK中包含一个很有用的工具，叫做javadoc=》javadoc 工具将你 Java 程序的源代码作为输入，输出一些包含你程序注释的HTML文件=》java_doc中的文件就是通过对标准java类库的源代码运行javadoc生成的

        // Topic-02:  java中插入文档注释的流程
        //            step-01： 通过 /** */在类、方法、域、包等需要的地方添加文档注释
        //            step-02:  在注释中根据需求添加@xxx标签
        //            step-03:  使用javadoc 抽取信息生成html文档
        //                      javadoc -d /Users/xiaomengli/Desktop/java全栈/ragnor_java/corejava/DocDirectory/ ./src/com/ragnor/corejava/chapter04_ClassAndObject/FocusPoint_09_JavaDocumentAnnotationTheCode.java

        // Topic-03:  javadoc 工具软件识别以下标签：
        //
        //            标签	描述	示例
        //            @author	    标识一个类的作者	        @author description
        //            @deprecated	指名一个过期的类或成员	    @deprecated description
        //            {@docRoot}	指明当前文档根目录的路径	    Directory Path
        //            @exception	标志一个类抛出的异常	    @exception exception-name explanation
        //            {@inheritDoc}	从直接父类继承的注释	    Inherits a comment from the immediate surperclass.
        //            {@link}	    插入一个到另一个主题的链接	{@link name text}
        //            {@linkplain}	插入一个到另一个主题的链接，但是该链接显示纯文本字体	Inserts an in-line link to another topic.
        //            @param	    说明一个方法的参数	        @param parameter-name explanation
        //            @return	    说明返回值类型	            @return explanation
        //            @see	        指定一个到另一个主题的链接	@see anchor
        //            @serial	    说明一个序列化属性	        @serial description
        //            @serialData	说明通过writeObject( ) 和 writeExternal( )方法写的数据	@serialData description
        //            @serialField	说明一个ObjectStreamField组件	@serialField name type description
        //            @since	    标记当引入一个特定的变化时	@since release
        //            @throws	    和 @exception标签一样.	The @throws tag has the same meaning as the @exception tag.
        //            {@value}	    显示常量的值，该常量必须是static属性。	Displays the value of a constant, which must be a static field.
        //            @version	    指定类的版本	            @version info

    }

}

/** 【类注释】这个类是为了实现 四则运算的
 * @author xiaomengli
 * @version 0.0.0
 * */
class CalculateNums{

    /** 【域注释】只需要对公有域-{通常是静态常量}建立文档
     * this product calculate unique-secret-id generate by com.ragnor.corejava
     *
     * */
    public static final String SecretId = "afsdffadsfhhahdufdaf";


    /** 【方法注释】这个方法是为了实现去平方的
     * @param num : num 就是要进行取平方运算的原值
     * @return num squared : 返回num * num 的值
     * */
    public double square(double num){
        return num * num;
    }

    /** 【方法注释】这个方法接收用控制台输入的num
     * @return : 输入的值作为double类型输出
     * @exception IOException on input error
     * @see IOException 指定到另一个主题的链接
     *
     * */
    public double getNum() throws IOException {
        Scanner ragnor_scanner_comment = new Scanner(System.in);
        System.out.println("please input your num:");
        return ragnor_scanner_comment.nextDouble();
    }


}
