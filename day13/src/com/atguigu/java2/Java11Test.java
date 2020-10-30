package com.atguigu.java2;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author 田沛勋
 * @create 2020-08-24 21:08
 */
public class Java11Test {

    //java 11新特性一：String中新增的方法
    @Test
    public void test1(){
//        判断字符串是否为空白	" ".isBlank(); // true
        System.out.println(" \t  \t \n".isBlank());

//        去除首尾空白	" Javastack ".strip(); // "Javastack"
        System.out.println("-------" + " \t abc \t \n".strip() + "------------");
        System.out.println("-------" + " \t abc \t \n".trim() + "------------");

//        去除尾部空格	" Javastack ".stripTrailing(); // " Javastack"
        System.out.println("-------" + " \t abc \t \n".stripTrailing() + "------------");

//        去除首部空格	" Javastack ".stripLeading(); // "Javastack "
        System.out.println("-------" + " \t abc \t \n".stripLeading() + "------------");

//        复制字符串	"Java".repeat(3);// "JavaJavaJava"
        String str1 = "abc";
        String str2 = str1.repeat(5);
        System.out.println(str2);

//        行数统计	"A\nB\nC".lines().count(); // 3
        String str3 = "abc\ndef\ng";
        System.out.println(str3.lines().count());
    }


    //java11 新特性二：Optional新增的方法
    @Test
    public void test2(){
        var op = Optional.empty();
        System.out.println(!op.isPresent());//判断Optional内部属性value 是否存在
        System.out.println(op.isEmpty());//判断Optional内部属性value是否为空

        op = Optional.of("abc");
        //orElseThrow():value 非空，返回value ；否则抛异常NoSuchElementException
        var obj = op.orElseThrow();
        System.out.println(obj);
        op = Optional.empty();

        //or():value 非空，返回对应的 Optional; value 为空，返回形参封装的 Optional
        var op1 = Optional.of("hello");
        Optional<Object> op2 = op.or(() -> op1);
        System.out.println(op2);
    }

    //java11 新特性三：局部变量类型推断的升级(注解修饰变量，变量需要使用var进行类型推断)

    @Test
    public void test3(){
//        在var上添加注解的语法格式，在jdk10中是不能实现的。在JDK11中加入了这样的语法。

        //错误的形式: 注解修饰变量，必须要有类型, 可以加上var
//         Consumer<String> con1 = (@Deprecated  t) -> System.out.println(t.toUpperCase());

        // 正确的形式://使用var的好处是在使用lambda表达式时给参数加上注解。
        Consumer<String> con2 = (@Deprecated var t) -> System.out.println(t.toUpperCase());
    }


    //java11 新特性四：HttpClient替换原有的HttpURLConnection
    @Test
    public void test4() throws IOException {
        try {
            HttpClient client= HttpClient.newHttpClient();
            HttpRequest request= HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
            HttpResponse.BodyHandler<String> responseBodyHandler= HttpResponse.BodyHandlers.ofString();
            HttpResponse<String> response= client.send(request, responseBodyHandler);
            String body= response.body();
            System.out.println(body);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test5(){

        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler= HttpResponse.BodyHandlers.ofString();
        CompletableFuture<HttpResponse<String>> sendAsync= client.sendAsync(request, responseBodyHandler);
        sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
        //HttpResponse<String> response = sendAsync.get();
        //String body = response.body();
        //System.out.println(body);

    }


}
