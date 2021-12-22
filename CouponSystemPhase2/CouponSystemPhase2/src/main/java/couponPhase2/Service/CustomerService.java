package couponPhase2.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import couponPhase2.Entity.Coupon;
import couponPhase2.Entity.Coupon.Category;
import couponPhase2.Entity.Customer;
import couponPhase2.Exception.CouponSystemException;
import lombok.Getter;
import lombok.Setter;

@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {

	@Setter
	@Getter
	private int customerID;

	@Override
	public boolean login(String email, String password) {
		if (customerRepo.existsByEmailAndPassword(email, password)) {
			customerID = customerRepo.findByemail(email).getId();
			return true;
		} else {
			System.out.println("one or more of the details are incorrect");
			return false;
		}
	}

	public String addPurchaseCoupon(int couponID) throws CouponSystemException {
		Coupon couponToPurchase = couponRepo.findById(couponID).orElse(null);
		if (couponToPurchase == null) {
			throw new CouponSystemException("this coupon doesn't exist");
		} else if (couponToPurchase.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponSystemException("this coupon has expired.");
		} else if (couponRepo.existsByCustomersIdAndId(customerID, couponID)) {
			throw new CouponSystemException("you have already purchased this coupon.");
		} else if (couponToPurchase.getAmount() == 0) {
			throw new CouponSystemException("this coupon is out of stock.");
		} else {
			Optional<Customer> opt = customerRepo.findById(customerID);
			Customer thisCustomer = opt.get();
			couponToPurchase.addCustomer(thisCustomer);
			couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
			return "you have purchased this coupon successfully";
		}
	}

	public List<Coupon> findAllCustomerCoupons() {
		return couponRepo.findAllByCustomersId(customerID);
	}

	public List<Coupon> findAllCustomerCouponsByCategory(Category category) {
		return couponRepo.findAllByCustomersIdAndCategory(customerID, category);

	}

	public List<Coupon> findCouponsByMAX_PRICE(double MAX_PRICE) {
		return couponRepo.findByCustomersIdAndPriceLessThan(customerID, MAX_PRICE);

	}

	public Customer CustomerDetails() {
		return customerRepo.findById(customerID).orElse(null);
	}

}
