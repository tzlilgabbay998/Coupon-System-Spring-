package couponPhase2.TestAll;

import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import couponPhase2.Entity.Company;
import couponPhase2.Entity.Coupon;
import couponPhase2.Entity.Coupon.Category;
import couponPhase2.Entity.Customer;
import couponPhase2.Exception.CouponSystemException;
import couponPhase2.Job.CouponExpirationDailyJob;
import couponPhase2.LoginManager.LoginManager;
import couponPhase2.LoginManager.LoginManager.ClientType;
import couponPhase2.Service.AdminService;
import couponPhase2.Service.CompanyService;
import couponPhase2.Service.CustomerService;

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

	static Coupon newCoupon = new Coupon(1, Category.DRINKS, "Kola", "tasty soda drink with an addiction effect",
			LocalDate.parse("2022-12-03"), LocalDate.parse("2022-12-12"), 200, 5.3, "each can is heaven", newCompany,
			null);
	static Coupon newCoupon1 = new Coupon(2, Category.FROZEN, "Hamburger", "tasty fast food with an addiction effect",
			LocalDate.parse("2022-12-03"), LocalDate.parse("2022-12-12"), 100, 11, "each can is heaven", new4Company,
			null);
	static Coupon newCoupon2 = new Coupon(1, Category.DRINKS, "Kola Zero", "tasty soda drink with an addiction effect",
			LocalDate.parse("2022-02-03"), LocalDate.parse("2022-05-12"), 150, 5.3, "each can is heaven", newCompany,
			null);
	static Coupon newCoupon3 = new Coupon(3, Category.DRINKS, "Fanta", "tasty soda drink with the orange effect",
			LocalDate.parse("2022-07-03"), LocalDate.parse("2022-12-12"), 60, 5.3, "each can is FANTA", newCompany,
			null);
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
			System.out.println(admin.UpdateCompany(new2Company));
			System.out.print(admin.findAllCompanies());
			admin.deleteCompany(new3Company.getId());
			admin.getOneCompany(newCompany.getId());

			admin.addCustomer(newCustomer);
			admin.addCustomer(new2Customer);
			admin.addCustomer(new3Customer);
			System.out.println(admin.findAllCustomers());
			admin.getOneCustomer(newCustomer.getId());
			System.out.println(admin.UpdateCustomer(new4Customer));
			admin.deleteCustomer(newCustomer.getId());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Admin issue: someting got wrong " + e.getCause());
		}

		try {
			CompanyService company = (CompanyService) lm.login(ClientType.COMPANY, "tarG@gmail", "sisma128");
			company.addCoupon(newCoupon);
			company.addCoupon(newCoupon1);
			company.addCoupon(newCoupon3);
			company.addCoupon(newCoupon4);
			company.UpdateCoupon(newCoupon2);
			System.out.println(company.findAllCompanyCoupons());
			System.out.println(company.findAllCompanyCouponsByCategory(Category.DRINKS));
			System.out.println(company.findCouponsByMAX_PRICE(7));
			company.deleteCoupon(newCoupon1.getId());
			System.out.println(company.CompanyDetails());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Company issue: someting got wrong " + e.getCause());
		}

		try {
			CustomerService customer = (CustomerService) lm.login(ClientType.CUSTOMER, "shum@gmail", "BaSushi");
			customer.addPurchaseCoupon(3);
			customer.addPurchaseCoupon(4);
			System.out.println(customer.findAllCustomerCoupons());
			System.out.println(customer.findAllCustomerCouponsByCategory(Category.DRINKS));
			System.out.println(customer.findCouponsByMAX_PRICE(6.5));
			System.out.println(customer.CustomerDetails());
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Customer issue: someting got wrong " + e.getCause());
		}

		try {
			startJob.stopJob();
		} catch (IllegalThreadStateException e) {
			throw new CouponSystemException("Thread issue. could'nt stop the daily job");
		}

	}

}
