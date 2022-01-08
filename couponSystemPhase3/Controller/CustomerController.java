package couponSystemPhase3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import couponSystemPhase3.Entity.Coupon;
import couponSystemPhase3.Entity.Coupon.Category;
import couponSystemPhase3.Entity.Customer;
import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController extends ClientController {

	@Autowired
	private CustomerService customerServ;

	public CustomerController(CustomerService customerServ) {
		super();
		this.customerServ = customerServ;
	}

	@PostMapping("/login")
	@Override
	public boolean login(@RequestParam String email, String password) throws CouponSystemException {
		return customerServ.login(email, password);
	}

	@PostMapping("/purchase-coupon")
	public String addPurchase(@RequestBody Coupon coupon) throws CouponSystemException {
		return customerServ.addPurchaseCoupon(coupon);
	}

	@GetMapping("/your-coupons")
	List<Coupon> yourCoupons() {
		return customerServ.findAllCustomerCoupons();
	}

	@GetMapping("/your-coupons-by-category")
	List<Coupon> yourCouponsByCategory(@RequestParam Category category) {
		return customerServ.findAllCustomerCouponsByCategory(category);
	}

	@GetMapping("/your-coupons-by-price")
	List<Coupon> yourCouponsByPrice(@RequestParam double maxPrice) {
		return customerServ.findCouponsByMAX_PRICE(maxPrice);
	}

	@GetMapping("/your-info")
	public Customer getInfo() {
		return customerServ.CustomerDetails();
	}

}