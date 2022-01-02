package onlineroomrent.controller;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("property-owner")
@RestController
public class OnlineRoomRentController {
    @Autowired
    FrontendService frontendService;
    @GetMapping("/post-property")
    public ModelAndView registerOwner(){
        return new ModelAndView("owner_register");
    }

    @GetMapping("/contact-us")
    public ModelAndView contactUs(){
        return new ModelAndView("contact-us");
    }

    @GetMapping("/register")
    public ModelAndView register(HttpServletResponse response){
        frontendService.findAllCategories();
        return new ModelAndView("online-room-rent-template/owner_register");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("online-room-rent-template/owner_login");
    }

    @GetMapping("/add-property")
    public ModelAndView addProperty(){
        List<PropertyCategoryEntity> list=frontendService.findAllCategories();
        ModelAndView mv=new ModelAndView("online-room-rent-template/add-property");
        mv.addObject("categoryList",list);
        return mv;
    }

    @GetMapping("/check-connection")
    public ResponseEntity<?> checkConnection(){
        return new ResponseEntity<>(HttpStatus.OK,HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("online-room-rent-template/index");
        return mv;
    }
}
