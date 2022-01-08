package couponSystemPhase3;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.TestAll.StartTest;
import couponSystemPhase3.TokensManager.LoginFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CouponSystemPhase3Application {

	public static void main(String[] args) throws CouponSystemException, SQLException {
		ApplicationContext ctx = SpringApplication.run(CouponSystemPhase3Application.class, args);
		StartTest startTest = ctx.getBean(StartTest.class);
		startTest.testAll();
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistrationBean(LoginFilter loginFilter) {
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}

}
