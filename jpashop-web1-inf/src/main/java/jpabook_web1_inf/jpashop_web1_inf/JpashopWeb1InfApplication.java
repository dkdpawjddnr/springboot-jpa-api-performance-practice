package jpabook_web1_inf.jpashop_web1_inf;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// 이 어노테이션이 있으면 이 패키지랑 이 패키지 하위에 있는 것을
// 모드 컴포넌트 스캔을 하고 등록을 한다.
@SpringBootApplication
public class JpashopWeb1InfApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopWeb1InfApplication.class, args);
	}


	@Bean
	Hibernate5JakartaModule hibernate5Module() {
		return new Hibernate5JakartaModule();
	}

}

