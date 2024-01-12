package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String helloString(){
        return "hello_string";
    }

    @GetMapping("/screen")
    public String helloScreen(){
        return "screen";
    }

    @GetMapping("/screen-model-param")
    // ?name=bellwin의 방식으로 호출
    public String helloScreenModelParam(@RequestParam(value = "name") String inputName, Model model){
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
    public String helloScreenModelPath(@PathVariable int id, Model model){
        model.addAttribute("myData", id);
        return "screen";
    }

    @GetMapping("/json")
    @ResponseBody
    public String helloJson(){
        return "hello_json";
    }
}
