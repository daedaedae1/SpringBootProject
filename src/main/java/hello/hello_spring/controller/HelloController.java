package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping ("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // 템플릿에 있는 hello.html을 실행시켜라.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http에 헤더 부와 바디 부가 있다. 이 바디 부에 return데이터를 직접 넣어주겠다.
    // >> 페이지 소스 보기 하면, html이 아닌 무식하게 문자열이 그냥 있다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody   // 객체를 반환하고 ResponseBody를 해놓으면, json으로 반환하는 게 기본이다.
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }   // json방식이다. key, value로 이루어진 구조이다.

    static class Hello {
        private String name;

        // 게터, 세터 => 자바 빈 규약
        // 자바 빈 표준 규약
        // 프로퍼티 접근 방식이라고도 함.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

// 컨트롤러에서 리턴값으로 문자를 반환하면, 뷰 리졸버가 화면을 찾아서 처리.
// 스프링부트 템플릿엔진 기본 viewName 매핑.