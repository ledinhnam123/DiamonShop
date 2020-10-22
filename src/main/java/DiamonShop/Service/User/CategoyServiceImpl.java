package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.ProductsDao;
import DiamonShop.Dto.ProductsDto;
@Service
public class CategoyServiceImpl implements ICategoyService{

	@Autowired
	private ProductsDao productsDao;
	
	public List<ProductsDto> GetDataProductsPaginate( int id,int start, int totalPage) {
		
		return productsDao.GetDataProductsByPaginate(id,start, totalPage);
	}

	public List<ProductsDto> GetALLProductsByID(int id) {
	
		return productsDao.GetALLProductsByID(id);
	}

}
