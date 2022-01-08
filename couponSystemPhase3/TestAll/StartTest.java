package couponSystemPhase3.TestAll;

import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import couponSystemPhase3.Entity.Company;
import couponSystemPhase3.Entity.Coupon;
import couponSystemPhase3.Entity.Coupon.Category;
import couponSystemPhase3.Entity.Customer;
import couponSystemPhase3.Exception.CouponSystemException;
import couponSystemPhase3.Job.CouponExpirationDailyJob;
import couponSystemPhase3.LoginManager.LoginManager;
import couponSystemPhase3.LoginManager.LoginManager.ClientType;
import couponSystemPhase3.Service.AdminService;
import couponSystemPhase3.Service.CompanyService;
import couponSystemPhase3.Service.CustomerService;

@Service
public class StartTest {

	@Autowired
	LoginManager lm;

	static Company newCompany = new Company(1, "TAR-G", "tar@gmail", "128", null);
	static Company new2Company = new Company(1, "TAR-G", "tarG@gmail", "sisma128", null);
	static Company new3Company = new Company(2, "Gili&Tzili", "G&T@gmail", "lll1", null);
	static Company new4Company = new Company(3, "Gilda", "Gilda@gmail", "1209", null);
	static Company new5Company = new Company(4, "Stemi", "kvar@gmail", "Metumtemet", null);

	static Customer newCustomer = new Customer("Sara", "Shara", "shir@gmail", "Sameach");
	static Customer new2Customer = new Customer("Sasha", "Sama", "shum@gmail", "BaSushi");
	static Customer new3Customer = new Customer("Ein", "Lee", "Odd@gmail", "Raayonot");
	static Customer new4Customer = new Customer("Adayeen", "Ein", "Lee@gmail", "Raayonot");

	static Coupon newCoupon = new Coupon(1, Category.RESTAURANTSandFOOD, "Kola",
			"tasty soda drink with an addiction effect", LocalDate.parse("2022-12-03"), LocalDate.parse("2022-12-12"),
			200, 5.3, "each can is heaven", newCompany, null);
	static Coupon newCoupon1 = new Coupon(2, Category.RESTAURANTSandFOOD, "Hamburger",
			"tasty fast food with an addiction effect", LocalDate.parse("2022-12-03"), LocalDate.parse("2022-12-12"),
			100, 11, "each can is heaven", new4Company, null);
	static Coupon newCoupon2 = new Coupon(1, Category.RESTAURANTSandFOOD, "Kola Zero",
			"tasty soda drink with an addiction effect", LocalDate.parse("2022-02-03"), LocalDate.parse("2022-05-12"),
			150, 5.3, "each can is heaven", newCompany, null);
	static Coupon newCoupon3 = new Coupon(3, Category.RESTAURANTSandFOOD, "Fanta",
			"tasty soda drink with the orange effect", LocalDate.parse("2022-07-03"), LocalDate.parse("2022-12-12"), 60,
			5.3, "each can is FANTA", newCompany, null);
	static Coupon newCoupon4 = new Coupon(4, Category.ELECTRICITY, "Lamp", "lightning product",
			LocalDate.parse("2022-07-03"), LocalDate.parse("2022-12-12"), 30, 7, "hit the light", new5Company, null);

	public void testAll() throws CouponSystemException, SQLException {
		CouponExpirationDailyJob startJob = new CouponExpirationDailyJob();

		try {
			startJob.start();
		} catch (IllegalThreadStateException e) {
			throw new CouponSystemException("Thread issue. could'nt start the daily job", e);
		}

		try {
			AdminService admin = (AdminService) lm.login(ClientType.ADMINISTRATOR, "admin@admin.com", "admin");
			admin.addCompany(newCompany);
			admin.addCompany(new3Company);
			admin.addCompany(new4Company);
			admin.addCompany(new5Company);
			System.out.println(admin.updateCompany(new2Company));
			System.out.print(admin.findAllCompanies());
			admin.deleteCompany(new3Company.getId());
			admin.getOneCompany(newCompany.getId());

			admin.addCustomer(newCustomer);
			admin.addCustomer(new2Customer);
			admin.addCustomer(new3Customer);
			System.out.println(admin.findAllCustomers());
			admin.getOneCustomer(newCustomer.getId());
			System.out.println(admin.updateCustomer(new4Customer));
			admin.deleteCustomer(newCustomer.getId());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Admin issue: something went wrong " + e.getCause());
		}

		try {
			CompanyService company = (CompanyService) lm.login(ClientType.COMPANY, "tarG@gmail", "sisma128");
			company.addCoupon(newCoupon);
			company.addCoupon(newCoupon1);
			company.addCoupon(newCoupon3);
			company.addCoupon(newCoupon4);
			company.updateCoupon(newCoupon2);
			System.out.println(company.findAllCompanyCoupons());
			System.out.println(company.findAllCompanyCouponsByCategory(Category.RESTAURANTSandFOOD));
			System.out.println(company.findCouponsByMAX_PRICE(7));
			company.deleteCoupon(newCoupon1.getId());
			System.out.println(company.CompanyDetails());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Company issue: something went wrong " + e.getCause());
		}

		try {
			CustomerService customer = (CustomerService) lm.login(ClientType.CUSTOMER, "shum@gmail", "BaSushi");
			customer.addPurchaseCoupon(newCoupon3);
			customer.addPurchaseCoupon(newCoupon4);
			System.out.println(customer.findAllCustomerCoupons());
			System.out.println(customer.findAllCustomerCouponsByCategory(Category.RESTAURANTSandFOOD));
			System.out.println(customer.findCouponsByMAX_PRICE(6.5));
			System.out.println(customer.CustomerDetails());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Customer issue: something went wrong " + e.getCause());
		}

		try {
			startJob.stopJob();
		} catch (IllegalThreadStateException e) {
			throw new CouponSystemException("Thread issue. could'nt stop the daily job");
		}

	}

}
