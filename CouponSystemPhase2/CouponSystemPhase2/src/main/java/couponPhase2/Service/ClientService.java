package couponPhase2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import couponPhase2.Exception.CouponSystemException;
import couponPhase2.Repository.CompanyRepo;
import couponPhase2.Repository.CouponRepo;
import couponPhase2.Repository.CustomerRepo;

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
