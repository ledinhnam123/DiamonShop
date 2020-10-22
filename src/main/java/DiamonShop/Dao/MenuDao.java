package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.MapperMenu;
import DiamonShop.Entity.Menus;


@Repository
public class MenuDao extends BaseDao{

	
	public List<Menus>  GetDataMenu(){
		List<Menus> list = new ArrayList<Menus>();
		String sql="SELECT * FROM menus";
		list =_jdbcTemplate.query(sql, new MapperMenu());
		return list; 
	}
}
