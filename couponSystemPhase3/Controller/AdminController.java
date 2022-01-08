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
import couponSystemPhase3.Entity.Customer;
import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController extends ClientController {

	@Autowired
	private AdminService adminServ;

	public AdminController(AdminService adminServ) {
		super();
		this.adminServ = adminServ;
	}

	@PostMapping("/login")
	@Override
	public boolean login(@RequestParam String email, String password) throws CouponSystemException {
		return adminServ.login(email, password);
	}

	@PostMapping("/add-company")
	public Company addCompany(@RequestBody Company company) throws CouponSystemException {
		return adminServ.addCompany(company);
	}

	@PutMapping("/update-company")
	public Company updateCompany(@RequestBody Company company) throws CouponSystemException {
		return adminServ.updateCompany(company);
	}

	@DeleteMapping("/delete-company")
	public boolean deleteCompany(@RequestParam int companyID) throws CouponSystemException {
		return adminServ.deleteCompany(companyID);
	}

	@GetMapping("/get-one-company")
	public Company getOneCompany(@RequestParam int companyID) {
		return adminServ.getOneCompany(companyID);
	}

	@GetMapping("/get-all-companies")
	List<Company> getAllCompanies() {
		return adminServ.findAllCompanies();
	}

	@PostMapping("/add-customer")
	public Customer addCustomer(@RequestBody Customer customer) throws CouponSystemException {
		return adminServ.addCustomer(customer);
	}

	@PutMapping("/update-customer")
	public Customer updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
		return adminServ.updateCustomer(customer);
	}

	@DeleteMapping("/delete-customer")
	public void deleteCustomer(@RequestParam int customerID) throws CouponSystemException {
		adminServ.deleteCustomer(customerID);
	}

	@GetMapping("/get-one-customer")
	public Customer getOneCustomer(@RequestParam int customerID) {
		return adminServ.getOneCustomer(customerID);
	}

	@GetMapping("/get-all-customers")
	List<Customer> getAllCustomers() {
		return adminServ.findAllCustomers();
	}

}
