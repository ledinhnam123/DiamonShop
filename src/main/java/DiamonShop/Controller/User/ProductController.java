package DiamonShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Service.User.ProductServiceImpl;

@Controller
public class ProductController extends BaseController {

	
	@Autowired
	private ProductServiceImpl _productServiveImpl;

	@RequestMapping(value = {"chi-tiet-san-pham/{id}"})
	public ModelAndView Index(@PathVariable long id) {
		
		
		_mvShare.setViewName("user/products/product");
		_mvShare.addObject("product",_productServiveImpl.GetProductByID(id));
		int idCategory = _productServiveImpl.GetProductByID(id).getId_category();
		_mvShare.addObject("productByIdCategory",_productServiveImpl.GetProductByIDCategory(idCategory));
		
		return _mvShare;
	}
}
