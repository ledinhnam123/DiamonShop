package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.CategorysDao;
import DiamonShop.Dao.MenuDao;
import DiamonShop.Dao.ProductsDao;
import DiamonShop.Dao.SlidesDao;
import DiamonShop.Dto.ProductsDto;
import DiamonShop.Entity.Categorys;
import DiamonShop.Entity.Menus;
import DiamonShop.Entity.Slides;

@Service
public class HomeServiceImpl implements IHomeService{
	
	@Autowired
	private SlidesDao slidesDao;
	@Autowired
	private CategorysDao categorysDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ProductsDao productsDao;
	public List<Slides> GetDataSlide() {
		
		return slidesDao.GetDataSlide();
	}
	public List<Categorys> GetDataCategorys() {
		
		return categorysDao.GetDataCategorys();
	}
	public List<Menus> GetDataMenu() {
		
		return menuDao.GetDataMenu();
	}
	public List<ProductsDto> GetDataProdcucts() {
		List<ProductsDto> listProducts = productsDao.GetDataProducts();
		return listProducts;
	}

	
}
