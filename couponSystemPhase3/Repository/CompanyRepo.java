package couponSystemPhase3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import couponSystemPhase3.Entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

	boolean existsByEmailAndPassword(String email, String password);

	boolean existsByEmailOrName(String email, String name);

	Company findByEmail(String email);

}
