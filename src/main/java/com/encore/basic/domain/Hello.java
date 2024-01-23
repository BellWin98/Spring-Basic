package com.encore.basic.domain;

import lombok.*;

//@Getter
//@Setter
@Data // Getter, Setter, toString, equals 등 사전 구현
@NoArgsConstructor
public class Hello {
    private String name;
    private String email;
    private String password;

    // Builder 패턴 직접 구현
    public Hello(MyBuilder myBuilder){
        this.name = myBuilder.name;
        this.email = myBuilder.email;
        this.password = myBuilder.password;
    }

    public static MyBuilder builder(){
        return new MyBuilder();
    }

    public static class MyBuilder{
        private String name;
        private String email;
        private String password;

        public MyBuilder name(String name){
            this.name = name;
            return this;
        }
        public MyBuilder email(String email){
            this.email = email;
            return this;
        }
        public MyBuilder password(String password){
            this.password = password;
            return this;
        }
        public Hello build(){
            return new Hello(this);
        }

    }
}
