package com.edusystem.eduplatform.AOP;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class ControllersAspect {

    @Pointcut("within(com.edusystem.eduplatform.Controller..*)")
    public void allControllersExceptAuth() {}

    @Around("allControllersExceptAuth()")
    public Object logControllerActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (attributes != null) ? attributes.getRequest() : null;

        String methodName = joinPoint.getSignature().getName();
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : "Anonymous";

        String httpMethod = (request != null) ? request.getMethod() : "N/A";
        String uri = (request != null) ? request.getRequestURI() : "N/A";

        System.out.println("\n🌐 [REQUEST] " + httpMethod + " " + uri);
        System.out.println("🏷️ [CONTROLLER] " + controllerName);
        System.out.println("🔧 [METHOD] " + methodName);
        System.out.println("👤 [USER] " + username);
        System.out.println("📦 [PARAMS] " + Arrays.toString(joinPoint.getArgs()));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Exception in " + methodName + ": " + e.getMessage());
            throw e;
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("✅ [RESULT] " + result);
        System.out.println("⏱️ [TIME] " + duration + " ms");
        System.out.println("-----------------------------------------------------");
        return result;
    }
}

