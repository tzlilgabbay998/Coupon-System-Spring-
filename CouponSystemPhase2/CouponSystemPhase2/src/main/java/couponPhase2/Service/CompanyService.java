package couponPhase2.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import couponPhase2.Entity.Company;
import couponPhase2.Entity.Coupon;
import couponPhase2.Entity.Coupon.Category;
import couponPhase2.Exception.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CompanyService extends ClientService {

	@Id
	private int companyID;

	@Override
	public boolean login(String email, String password) {
		if (companyRepo.existsByEmailAndPassword(email, password)) {
			companyID = companyRepo.findByEmail(email).getId();
			return true;
		} else {
			System.out.println("one or more of the details are incorrect");
			return false;
		}
	}

	public Coupon addCoupon(Coupon coupon) throws CouponSystemException {
		if (couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
			throw new CouponSystemException("this title is already exist in your company coupons");
		} else {
			return couponRepo.save(coupon);
		}
	}

	public Coupon UpdateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponCheck = couponRepo.findByIdAndCompanyId(coupon.getId(), coupon.getCompany().getId());
		if (couponCheck == null) {
			throw new CouponSystemException("could'nt find this coupon id / companyID");
		}
		return couponRepo.saveAndFlush(coupon);
	}

	public void deleteCoupon(int couponID) {
		couponRepo.deleteById(couponID);
	}

	public List<Coupon> findAllCompanyCoupons() {
		return couponRepo.findAllByCompanyId(companyID);
	}

	public List<Coupon> findAllCompanyCouponsByCategory(Category category) {
		return couponRepo.findAllByCompanyIdAndCategory(companyID, category);

	}

	public List<Coupon> findCouponsByMAX_PRICE(double MAX_PRICE) {
		return couponRepo.findByCompanyIdAndPriceLessThan(companyID, MAX_PRICE);
	}

	public Company CompanyDetails() {
		Optional<Company> thisCompany = companyRepo.findById(companyID);
		return thisCompany.isPresent() ? thisCompany.get() : null;

	}

}
