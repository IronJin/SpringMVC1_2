package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


/**
 * 요청메세지 텍스트 형식으로 보내는 방식
 */
@Controller
@Slf4j
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messagebody={}", messagebody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messagebody={}", messagebody);
        responseWriter.write("ok");

    }

    /**
     * 대부분의 경우 이렇게 데이터를 직접 꺼내는 방식을 사용한다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messagebody = httpEntity.getBody();
        log.info("messagebody={}", messagebody);
        return new HttpEntity<>("ok"); //html 형식으로 반환해줌 response.getWriter().write("ok") 와 비슷한 형식이라고 생각하면됨
    }

    /**
     * @RequsetBody 를 사용하면 실제로 요청메세지 BODY 부분을 쉽게 조회할수 있다.
     * 실제로 실무에서도 엄청 많이쓴다.
     */
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messagebody) throws IOException {
        log.info("messagebody={}", messagebody);
        return "ok";
    }

    /**
     * 아래 두가지를 많이 쓴다.
     *
     * 요청 파라미터 vs HTTP 메시지 바디
     * 요청 파라미터를 조회하는 기능: @RequestParam , @ModelAttribute
     * HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
     */
}
