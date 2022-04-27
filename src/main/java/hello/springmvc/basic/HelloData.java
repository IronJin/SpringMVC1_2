package hello.springmvc.basic;

import lombok.Data;

@Data //Getter, @Setter @생성자 등을 자동으로 만들어준다.
public class HelloData {

    private String username;
    private int age;

}
