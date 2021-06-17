package boot.helloboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //스프링 빈 등록
public class TimeTraceAop {

    //공통함수 등록
    @Around("execution(* boot.helloboot..*(..))") //경로지정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        //joinPoint 함수를 통해 조작가능
        System.out.println("START: " + joinPoint.toString());
        try{
            //다음 메소드로 진행
            Object result = joinPoint.proceed();
            return result;
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }

    }
}
