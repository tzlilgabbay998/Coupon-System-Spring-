package couponSystemPhase3.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString.Exclude;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Check(constraints = "amount>=0")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Enumerated(EnumType.STRING)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JoinColumn(name = "category_id")
	private Category category;
	@NonNull
	private String title;
	@NonNull
	private String description;
	@NonNull
	private LocalDate startDate;
	@NonNull
	private LocalDate endDate;
	private int amount;
	private double price;
	@NonNull
	private String image;
	@Exclude
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;
	@Exclude
	@ManyToMany
	@JoinTable(name = "coupon_vs_customer", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@JsonIgnore
	private List<Customer> customers;

	public enum Category {
		RESTAURANTSandFOOD, EXPERIENCES, BEUATY, HOME_CLOTHING, ELECTRICITY;

	}

	public void addCustomer(Customer customer) {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		customers.add(customer);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
