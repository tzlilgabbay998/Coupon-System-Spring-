package couponPhase2;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import couponPhase2.Exception.CouponSystemException;
import couponPhase2.TestAll.StartTest;

@SpringBootApplication
public class CouponSystemPhase2Application {

	public static void main(String[] args) throws CouponSystemException, SQLException {
		ApplicationContext ctx = SpringApplication.run(CouponSystemPhase2Application.class, args);
		StartTest startTest = ctx.getBean(StartTest.class);
		startTest.testAll();
	}

}
