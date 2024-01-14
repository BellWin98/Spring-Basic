package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller // HTTP 통신을 쉽고 편하게 해주는 어노테이션
// @RestController // 모든 요청에 @ResponseBody를 붙일 때 사용 (CSR 방식의 프로젝트는 거의 다 이 방식이다.)
// 클래스 차원에서 url 경로를 지정하고 싶으면 @RequestMapping을 클래스 위에 선언하면서 경로 지정
@RequestMapping("/hello")
public class HelloController {

    // @ResponseBody가 없고, return타입이 String이면 templates 아래에 있는 html 파일 (화면) return
    // 데이터만 return할 때는 @ResponseBody를 붙인다. (RestAPI 방식)
    // return 값이 String이면 문자열, 객체면 JSON 형식으로 자동 변환되어 값을 반환한다.
    //    @RequestMapping(value = "string", method = RequestMethod.GET) // @GetMapping과 동일
    @GetMapping("/string") // 사용자의 요청을 분기 처리
    @ResponseBody
    public String helloString() {
        return "hello_string";
    }

    @GetMapping("/screen")
    public String helloScreen() {
        return "screen";
    }

    @GetMapping("/screen-model-param")
    // ?name=bellwin의 방식으로 호출
    public String helloScreenModelParam(@RequestParam(value = "name") String inputName, Model model) {
        // 화면에 data 넘기고 싶을 때 model 객체 사용
        // model에 key:value 형식으로 전달
        model.addAttribute("myData", inputName);

        // Spring이 model 객체를 알아서 screen.html로 전달
        // 이 때, 템플릿 엔진(JSP, Thymeleaf)을 사용하여 html에서도 데이터 활용 가능.
        return "screen";
    }

    // PathVariable 방식은 URL을 통해 자원의 구조를 명확하게 표현할 수 있어, RestFul API 방식에 적합
    // REST API: 현대적인 아키텍처 (더 직관적)
    @GetMapping("/screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }

    @GetMapping("/json")
    @ResponseBody
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setEmail("bellwin98@gmail.com");
        hello.setPassword("hjs0324@");
        hello.setName("bellwin");
        System.out.println(hello);
        return hello;
    }

    // form 태그로 x-www 데이터 처리 (input - submit 통해서 html만으로 처리 가능)
    @GetMapping("/form-screen")
    public String formScreen() {
        return "hello-form-screen";
    }

    @PostMapping("/form-post-handle")
    @ResponseBody
    // form 태그를 통한 body의 형태가 key1=value1&key2=value2 (파라미터 방식과 동일)
    public String formPostHandle(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password) {
        System.out.println("이름: " + name);
        System.out.println("email: " + email);
        System.out.println("password: " + password);

        return "정상 처리";
    }

    @PostMapping("/form-post-handle2")
    @ResponseBody
//     Spring에서 Hello 클래스의 인스턴스를 자동 매핑하여 생성
//     form 데이터 형식 즉, x-www-url 인코딩 형식의 경우에 사용
//     이를 데이터 바인딩이라 한다. (Hello 클래스에 Setter 필수)
    public String formPostHandle2(Hello hello) {
        System.out.println(hello);
        return "정상 처리";
    }

    // json 데이터 처리
    // html만으로는 처리 불가. javascript 통한 axios 필요
    @GetMapping("/json-screen")
    public String jsonScreen() {
        return "hello-json-screen";
    }

    @PostMapping("/json-post-handle1")
    @ResponseBody
    // @RequestBody: JSON으로 Post 요청이 들어오면 Body에서 데이터를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println("이름: " + body.get("name"));
        System.out.println("email: " + body.get("email"));
        System.out.println("password: " + body.get("password"));

        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setName(body.get("email"));
        hello.setName(body.get("password"));

        return "ok";
    }

    @PostMapping("/json-post-handle2")
    @ResponseBody
    // JsonNode 사용하는 경우: 데이터를 유연하게 처리하고 싶을 때 즉, 다양한 데이터 타입이 들어올 때 사용
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        // 사용자로부터 JsonNode를 통해 받아온 데이터를 DB에 저장하는 프로세스
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());

        return "ok";
    }

    @PostMapping("/json-post-handle3")
    @ResponseBody
    public Hello jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return hello;
    }
}
