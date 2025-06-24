package com.example.aoppoc.aspect;

import com.example.aoppoc.annotation.TrackTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Around("execution(* com.example.aoppoc.service..*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();

    Object result = joinPoint.proceed(); // Execute the method

    long duration = System.currentTimeMillis() - start;
    System.out.println("[@Around] Method " + joinPoint.getSignature() +
        " executed in " + duration + " ms");

    return result;
  }

  @Before("execution(* com.example.aoppoc.service..*(..))")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println("[@Before] Method called: " + joinPoint.getSignature());
  }

  @After("execution(* com.example.aoppoc.service..*(..))")
  public void logAfter(JoinPoint joinPoint) {
    System.out.println("[@After] Method finished: " + joinPoint.getSignature());
  }

  @AfterReturning(
      pointcut = "execution(* com.example.aoppoc.service..*(..))",
      returning = "result"
  )
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("[@AfterReturning] Method returned: " + joinPoint.getSignature() + ", Result: " + result);
  }

  @AfterThrowing(
      pointcut = "execution(* com.example.aoppoc.service..*(..))",
      throwing = "ex"
  )
  public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    System.out.println("[@AfterThrowing] Exception in method: " + joinPoint.getSignature() + ", Message: " + ex.getMessage());
  }

  @Around("@annotation(trackTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
    long start = System.currentTimeMillis();

    Object result = joinPoint.proceed();

    long duration = System.currentTimeMillis() - start;
    System.out.println("[@TrackTime] " + joinPoint.getSignature() + " executed in " + duration + " ms");

    return result;
  }
}
