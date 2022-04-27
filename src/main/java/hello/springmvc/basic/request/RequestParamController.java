package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 요청 파라미터 받아오기
 */
@Slf4j  //Logger 관련 애노테이션
@Controller
public class RequestParamController {


    //요청 파라미터 조회
    @RequestMapping("/request-param-v1")
    public void requestParam1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     *
     * @RequestParam 을 없애도 파라미터변수명이랑 같다면 @RequestParam 을 없애도 된다.
     * 너무 과하다는 의견도 있다.
     * 따라서 requestParamV3 처럼 사용하는게 좋긴하다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required=true 이면 파라미터값이 꼭 들어와야한다는 뜻이고 false 이면 안들어와도 된다는 뜻
     * 대신 빈값에는 null 값이들어간다. 따라서 int 형같은경우 null 이 들어갈 수 없으므로 오류가 뜨기도 한다.
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamV5(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age)
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * default 값을 설정해줄수 있다. 빈문자인 경우에도 처리를 해준다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamV6(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age)
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 요청파라미터를 Map 으로 조회하기
     * 97번째 줄에서 Map<String, String> 으로 받지 않는 이유는 Object 는 다양한 타입으로 파라미터가 들어올 수 있음을 명시하는 것이다.
     * 따라서 Map<String, String> 으로 받아도 문제가 되지 않는다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamV7(@RequestParam Map<String, Object> paramMap)
    {
        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 스프링MVC는 @ModelAttribute 가 있으면 다음을 실행한다.
     * HelloData 객체를 생성한다.
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를
     * 호출해서 파라미터의 값을 입력(바인딩) 한다.
     * 예) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
     */
    @ResponseBody
    @RequestMapping("model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /** 이부분이 @ModelAttribute 가 해결해준 부분이다.
         * HelloData data = new HelloData();
         * data.setUsername(username);
         * data.setAge(age);
         */
        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        /** 이부분이 @ModelAttribute 가 해결해준 부분이다.
         *
         * V1 과 다른점은 @ModelAttribute를 생략을 해주었다는 것이다.
         *
         * HelloData data = new HelloData();
         * data.setUsername(username);
         * data.setAge(age);
         */
        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }


} //class 끝
