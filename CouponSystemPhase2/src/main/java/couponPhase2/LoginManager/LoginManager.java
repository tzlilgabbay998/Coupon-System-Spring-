package couponPhase2.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import couponPhase2.Exception.CouponSystemException;
import couponPhase2.Service.AdminService;
import couponPhase2.Service.ClientService;
import couponPhase2.Service.CompanyService;
import couponPhase2.Service.CustomerService;

@Component
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	public enum ClientType {
		ADMINISTRATOR, COMPANY, CUSTOMER
	}

	public ClientService login(ClientType ct, String email, String password) throws CouponSystemException {
		switch (ct) {
		case ADMINISTRATOR:
			ClientService admin = ctx.getBean(AdminService.class);
			if (admin.login(email, password)) {
				return admin;
			}
			break;
		case COMPANY:
			ClientService company = ctx.getBean(CompanyService.class);
			if (company.login(email, password)) {
				return company;
			}
			break;
		case CUSTOMER:
			ClientService customer = ctx.getBean(CustomerService.class);
			if (customer.login(email, password)) {
				return customer;
			}
			break;
		}
		return null;
	}

}
