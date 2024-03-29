package org.springaop.chapter.one.annotation.around;

import org.springaop.target.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/one/annotation/around/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        Hello hello = (Hello) ctx.getBean("hello");
        hello.greeting();
    }
}
