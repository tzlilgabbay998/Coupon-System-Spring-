package couponSystemPhase3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import couponSystemPhase3.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);

	Customer findByemail(String email);

}
