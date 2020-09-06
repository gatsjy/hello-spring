package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 스프링을 왜 쓰는가?
    // 바로 이러한 사항 때문이다. 객체지향적인 개발
    // 다형성을 활용한다. -> 스프링은 컨테이너 차원에서 이것을 지원해준다.
    // 기존의 코드는 하나도 손대지 않고 애플리케이션을 조립하는 이 코드만 세팅되면 나머지 애플리케이션에 관련된 코드를 하나도 손댈 필요가 없다
    // 개발 폐쇄 원칙? -> 확장에는 열려있고, 수정에는 닫혀있다
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
