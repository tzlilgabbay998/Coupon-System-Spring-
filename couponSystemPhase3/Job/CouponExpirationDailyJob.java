package couponSystemPhase3.Job;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import couponSystemPhase3.Repository.CouponRepo;

@Service
@Scope("singleton")
public class CouponExpirationDailyJob extends Thread {

	@Autowired
	private CouponRepo couponRepo;
	private boolean quit;

	@Override
	public void run() {
		try {
			while (true) {
				CouponExpirationDailyJob.sleep(1000 * 60 * 60 * 24);
				if (quit == false) {
					this.couponRepo.deleteByEndDateBefore(LocalDate.now());
				} else {
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopJob() {
		this.quit = true;
	}

}
