package org.springaop.chapter.two.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class ComposableBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {

        System.out.println("ComposableBeforeAdvice");
    }
}
