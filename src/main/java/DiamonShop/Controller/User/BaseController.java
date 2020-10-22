package DiamonShop.Controller.User;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import DiamonShop.Service.User.HomeServiceImpl;


@Controller
public class BaseController {

		@Autowired
		public HomeServiceImpl _homeService;
		
		public ModelAndView _mvShare = new ModelAndView();
		
		
		@PostConstruct // vua vo chay Init truoc
		public ModelAndView Init() {
			 _mvShare.addObject("menus",_homeService.GetDataMenu());
			return _mvShare;
		}
		
		
		

}
