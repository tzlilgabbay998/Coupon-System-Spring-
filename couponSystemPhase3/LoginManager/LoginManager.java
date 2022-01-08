package couponSystemPhase3.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Service.AdminService;
import couponSystemPhase3.Service.ClientService;
import couponSystemPhase3.Service.CompanyService;
import couponSystemPhase3.Service.CustomerService;

@Service
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
