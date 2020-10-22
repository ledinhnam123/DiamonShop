package DiamonShop.Service.User;

import java.util.HashMap;

import DiamonShop.Dto.CartDto;
import DiamonShop.Entity.Bills;

public interface IBillsService {

	public int AddBills(Bills bill);
	public void AddBillsDetail(HashMap<Long,CartDto> carts);
}
