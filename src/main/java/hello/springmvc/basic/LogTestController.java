package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //private final Logger log = LoggerFactory.getLogger(LogTestController.class);
@RestController
public class LogTestController {


    //private final Logger log = LoggerFactory.getLogger(LogTestController.class); @Slf4j 가 사용되서 주석처리함함

   /**
     * RestController 대신 Controller를 쓴다면
     * 아래 메소드에서 return 값이 뷰 리졸버에 의해 뷰를 반환해주는데
     * RestController 를 사용함으로써 홈페이지에 반환된 String 이 사용된다.
     */
    @RequestMapping("log-test")
    public String logTest() {

        String name = "Spring";

        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);

        return "ok";
    }

}
