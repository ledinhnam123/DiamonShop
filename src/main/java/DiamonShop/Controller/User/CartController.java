package DiamonShop.Controller.User;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Dto.CartDto;
import DiamonShop.Entity.Bills;
import DiamonShop.Entity.Users;
import DiamonShop.Service.User.BillsServiceImpl;
import DiamonShop.Service.User.CartServiceImpl;

@Controller
public class CartController extends BaseController {
	@Autowired
	private CartServiceImpl cartServiceImpl = new CartServiceImpl();

	@Autowired
	private BillsServiceImpl billsServiceImpl = new BillsServiceImpl();

	@RequestMapping(value = "gio-hang")
	public ModelAndView Index() {

		_mvShare.addObject("slides", _homeService.GetDataSlide());
		_mvShare.addObject("categorys", _homeService.GetDataCategorys());
		_mvShare.addObject("products", _homeService.GetDataProdcucts());
		_mvShare.setViewName("user/cart/list_cart");

		return _mvShare;
	}

	@RequestMapping(value = "AddCart/{id}")
	public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {

		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = cartServiceImpl.AddCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("ToTalQuantyCart", cartServiceImpl.TotalQuanty(cart));
		session.setAttribute("ToTalPriceCart", cartServiceImpl.TotalPrice(cart));
		// return "redirect:/chi-tiet-san-pham/"+id;//redirect la cai thang truoc
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "EditCart/{id}/{quanty}")
	public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable long id,
			@PathVariable int quanty) {
		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		// cart = cartServiceImpl.EditCart(id);
		cart = cartServiceImpl.EditCart(id, quanty, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("ToTalQuantyCart", cartServiceImpl.TotalQuanty(cart));
		session.setAttribute("ToTalPriceCart", cartServiceImpl.TotalPrice(cart));

		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {

		HashMap<Long, CartDto> cart = (HashMap<Long, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Long, CartDto>();
		}
		cart = cartServiceImpl.DeleteCart(id, cart);

		session.setAttribute("Cart", cart);
		session.setAttribute("ToTalQuantyCart", cartServiceImpl.TotalQuanty(cart));
		session.setAttribute("ToTalPriceCart", cartServiceImpl.TotalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView CheckoutOut(HttpServletRequest request, HttpSession session) {

		_mvShare.setViewName("user/bills/checkout");
		Bills bills = new Bills();
		Users loginInfo = (Users) session.getAttribute("LoginInfo");

		if (loginInfo != null) {
			bills.setAddress(loginInfo.getAddress());
			bills.setDisplay_name(loginInfo.getDisplay_name());
			bills.setUser(loginInfo.getUser());
		}
		_mvShare.addObject("bills", bills);

		return _mvShare;
	}

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String CheckoutBills(HttpServletRequest request, HttpSession session,
			@ModelAttribute(value = "bills") Bills bill) {
		
		 bill.setQuanty(Integer.valueOf((String) session.getAttribute("ToTalQuantyCart")) );
		 bill.setTotal(Double.parseDouble((String) session.getAttribute("ToTalPriceCart")));
		 
		//bill.setQuanty(toInt((String) session.getAttribute("ToTalQuantyCart")));
		//bill.se
	
		if (billsServiceImpl.AddBills(bill) > 0) {
			
				
			HashMap<Long, CartDto> carts = (HashMap<Long, CartDto>) session.getAttribute("Cart");
			billsServiceImpl.AddBillsDetail(carts);
			
		}
		
		// _mvShare.setViewName("Cart");
		session.removeAttribute("Cart");
		session.removeAttribute("ToTalQuantyCart");
		session.removeAttribute("ToTalPriceCart");

		return "redirect:gio-hang";

	}

	public static int toInt(Object value) {
		return toInt(value, 0);
	}

	public static int toInt(Object value, int def) {
		if (value == null) {
			return def;
		}
		if ((value instanceof Number)) {
			return ((Number) value).intValue();
		}
		try {
			return Integer.valueOf(value.toString()).intValue();
		} catch (Exception e) {
		}
		return def;
	}

}
