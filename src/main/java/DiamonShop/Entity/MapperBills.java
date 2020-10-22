package DiamonShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBills  implements RowMapper<Bills> {

public Bills mapRow(ResultSet rs, int rowNum) throws SQLException {

	Bills bill = new Bills();
	
	bill.setId(rs.getLong("id"));
	bill.setUser(rs.getString("user"));
	bill.setPhone(rs.getString("phone"));
	bill.setDisplay_name(rs.getString("display_name"));
	bill.setAddress(rs.getString("address"));
	bill.setQuanty(rs.getInt("quanty"));
	bill.setTotal(rs.getDouble("total"));

	
		return bill;
	}

}
