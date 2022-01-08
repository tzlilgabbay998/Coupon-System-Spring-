package couponSystemPhase3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import couponSystemPhase3.Entity.Company;
import couponSystemPhase3.Entity.Coupon;
import couponSystemPhase3.Entity.Coupon.Category;
import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Service.CompanyService;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController extends ClientController {

	@Autowired
	private CompanyService companyServ;

	public CompanyController(CompanyService companyServ) {
		super();
		this.companyServ = companyServ;
	}

	@PostMapping("/login")
	@Override
	public boolean login(@RequestParam String email, String password) throws CouponSystemException {
		return companyServ.login(email, password);
	}

	@PostMapping("/add-coupon")
	public Coupon addCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
		return companyServ.addCoupon(coupon);
	}

	@PutMapping("/update-coupon")
	public Coupon updateCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
		return companyServ.updateCoupon(coupon);
	}

	@DeleteMapping("/delete-coupon")
	public void deleteCoupon(@RequestParam int couponID) throws CouponSystemException {
		companyServ.deleteCoupon(couponID);
	}

	@GetMapping("/get-all-coupons")
	List<Coupon> getAllCompanyCoupons() {
		return companyServ.findAllCompanyCoupons();
	}

	@GetMapping("/coupons-by-price")
	List<Coupon> getAllCompanyCouponsByPrice(@RequestParam double maxPrice) {
		return companyServ.findCouponsByMAX_PRICE(maxPrice);
	}

	@GetMapping("/coupons-by-category")
	List<Coupon> getAllCompanyCouponsByCategory(@RequestParam Category category) {
		return companyServ.findAllCompanyCouponsByCategory(category);
	}

	@GetMapping("/company-info")
	public Company getInfo() {
		return companyServ.CompanyDetails();
	}

}