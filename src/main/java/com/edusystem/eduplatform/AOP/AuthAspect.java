package com.edusystem.eduplatform.AOP;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthAspect {


    @Pointcut("within(com.edusystem.eduplatform.secure.notes.Controllers.AuthController)")
    public void authControllerMethods() {}

    @Around("authControllerMethods()")
    public Object logAuthMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : "Anonymous";

        System.out.println("🔐 [AUTH] Executing method: " + methodName);
        System.out.println("👤 [USER]  Logged in as: " +Arrays.toString(args).substring(23,Arrays.toString(args).indexOf(',')));
        System.out.println("📦 [PARAMS] " + Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Exception in method: " + methodName + " -> " + e.getMessage());
            throw e;
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("✅ [RESULT] " + result);
        System.out.println("⏱️ [TIME] " + duration + " ms");
        System.out.println("-----------------------------------------------------");
        return result;
    }
}
