package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// エラー出てたので一旦コメントアウト

@Aspect
@Component
public class CommonLogAspect {
	//	private final Logger log = LoggerFactory.getLogger(CommonLogAspect.class);
	//
	//	@Around("execution(*com.exmple.demo..*(..))")
	//	public Object writeLog(ProceedingJoinPoint jp) {
	//		Object returnObj = null;
	//		log.info("start:" + jp.getSignature().toString());
	//
	//		try {
	//			returnObj = jp.proceed();
	//		} catch (Throwable t) {
	//			log.error(t.toString());
	//		}
	//
	//		log.info("end:" + jp.getSignature().toString());
	//
	//		return returnObj;
	//
	//	}
}
