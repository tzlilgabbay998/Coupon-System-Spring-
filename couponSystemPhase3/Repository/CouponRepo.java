package couponSystemPhase3.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import couponSystemPhase3.Entity.Coupon;
import couponSystemPhase3.Entity.Coupon.Category;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	List<Coupon> findAllByCompanyId(int companyID);

	List<Coupon> findAllByCompanyIdAndCategory(int companyID, Category category);

	List<Coupon> findAllByCustomersIdAndCategory(int customerID, Category category);

	List<Coupon> findByCompanyIdAndPriceLessThan(int Id, double MAX_PRICE);

	List<Coupon> findByCustomersIdAndPriceLessThan(int Id, double MAX_PRICE);

	boolean existsByCustomersIdAndId(int customerID, int couponID);

	boolean existsByTitleAndCompanyId(String title, int companyID);

	Coupon findByIdAndCompanyId(int Id, int companyID);

	List<Coupon> findAllByCustomersId(int customerID);

	List<Coupon> deleteByEndDateBefore(LocalDate endDate);

}
