package couponSystemPhase3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Service.AdminService;
import couponSystemPhase3.Service.CompanyService;
import couponSystemPhase3.Service.CustomerService;

@Service
public abstract class ClientController {

	@Autowired
	protected CompanyService companyServ;
	@Autowired
	protected CustomerService customerServ;
	@Autowired
	protected AdminService adminServ;

	public boolean login(String email, String password) throws CouponSystemException {
		return false;
	}
}
