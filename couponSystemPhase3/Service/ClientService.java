package couponSystemPhase3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Repository.CompanyRepo;
import couponSystemPhase3.Repository.CouponRepo;
import couponSystemPhase3.Repository.CustomerRepo;

@Component
public abstract class ClientService {

	@Autowired
	protected CompanyRepo companyRepo;
	@Autowired
	protected CustomerRepo customerRepo;
	@Autowired
	protected CouponRepo couponRepo;

	public boolean login(String email, String password) throws CouponSystemException {
		return false;
	}
}
