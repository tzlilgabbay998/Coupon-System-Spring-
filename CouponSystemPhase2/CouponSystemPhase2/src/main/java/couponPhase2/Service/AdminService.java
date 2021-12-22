package couponPhase2.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import couponPhase2.Entity.Company;
import couponPhase2.Entity.Customer;
import couponPhase2.Exception.CouponSystemException;

@Service
@Transactional
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		} else {
			System.out.println("one or more of the details are incorrect");
			return false;
		}
	}

	public Company addCompany(Company company) throws CouponSystemException {
		if (companyRepo.existsByEmailOrName(company.getEmail(), company.getName())) {
			throw new CouponSystemException("this name / email is already exist");
		} else {
			return companyRepo.save(company);
		}
	}

	public Company UpdateCompany(Company company) throws CouponSystemException {
		Company companyCheck = this.getOneCompany(company.getId());
		if (companyCheck == null) {
			throw new CouponSystemException("this id doesn't exist");
		} else if (companyCheck.getName().equals(company.getName())) {
			try {
				return companyRepo.saveAndFlush(company);
			} catch (Throwable e) {
				throw new CouponSystemException("this email is already taken");
			}
		} else {
			throw new CouponSystemException("this name isn't exist");
		}
	}

	public boolean deleteCompany(int companyID) {
		if (companyRepo.existsById(companyID)) {
			companyRepo.deleteById(companyID);
			return true;
		} else {
			return false;
		}
	}

	public List<Company> findAllCompanies() {
		return companyRepo.findAll();
	}

	public Company getOneCompany(int companyID) {
		Optional<Company> cp = companyRepo.findById(companyID);
		return cp.isPresent() ? cp.get() : null;
	}

	public Customer addCustomer(Customer customer) throws CouponSystemException {
		if (customerRepo.existsByEmail(customer.getEmail())) {
			throw new CouponSystemException("this email is already taken");
		}
		return customerRepo.save(customer);
	}

	public Customer UpdateCustomer(Customer customer) throws CouponSystemException {
		if (customerRepo.findById(customer.getId()) != null) {
			return this.customerRepo.saveAndFlush(customer);
		}
		throw new CouponSystemException("could'nt find that customer, id is'nt exist");
	}

	public void deleteCustomer(int customerID) {
		customerRepo.deleteById(customerID);
	}

	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getOneCustomer(int customerID) {
		Optional<Customer> oc = customerRepo.findById(customerID);
		return oc.isPresent() ? oc.get() : null;
	}

}
