package onlineroomrent.controller;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dao.entity.country.CityEntity;
import onlineroomrent.dao.entity.country.CountryEntity;
import onlineroomrent.dao.entity.country.StateEntity;
import onlineroomrent.dao.repository.CityRepository;
import onlineroomrent.dao.repository.CountryRepository;
import onlineroomrent.dao.repository.StateRepository;
import onlineroomrent.dto.PropertyAdsDto;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
@RequestMapping("")
@RestController
public class OnlineRoomRentController {
    @Autowired
    FrontendService frontendService;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;
    @GetMapping("/post-property")
    public ModelAndView registerOwner(){
        return new ModelAndView("owner_register");
    }

    @GetMapping("/contact-us")
    public ModelAndView contactUs(){
        return new ModelAndView("contact-us");
    }

    @GetMapping("/property-owner/register")
    public ModelAndView register(HttpServletResponse response){
        ModelAndView mv=new ModelAndView("online-room-rent-template/owner_register");
        List<Role>roles= frontendService.findAllRole();
        mv.addObject("userRoles",roles);
        return mv;
    }

    @GetMapping("/property-owner/login")
    public ModelAndView login(){
        return new ModelAndView("online-room-rent-template/owner_login");
    }

    @GetMapping("/property-owner/add-property")
    public ModelAndView addProperty(){
        List<PropertyCategoryEntity> list=frontendService.findAllCategories();
        List<CountryEntity>countries=countryRepository.findAll();
        List<StateEntity>stateEntities=stateRepository.findAll();
        List<CityEntity>cityEntities=cityRepository.findAll();
        List<String> propertyTypes= Arrays.asList("Rent","Resale","PG/Hotel","FlatMates");
        ModelAndView mv=new ModelAndView("online-room-rent-template/add-property");
        mv.addObject("countries",countries);
        mv.addObject("states",stateEntities);
        mv.addObject("cities",cityEntities);
        mv.addObject("propertyTypes",propertyTypes);
        mv.addObject("propertyCategory",list);
        return mv;
    }

    @GetMapping("/property-owner/property-detail")
    public ModelAndView propertyDetail(){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("online-room-rent-template/property_detail");
        return mv;
    }

    @GetMapping("/check-connection")
    public ResponseEntity<?> checkConnection(){
        return new ResponseEntity<>(HttpStatus.OK,HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView index(){
     List<PropertyAdsDto> dtos = frontendService.findAllPropertyAds();
        ModelAndView mv= new ModelAndView();
        mv.addObject("dtos",dtos);
        mv.setViewName("online-room-rent-template/index");
        return mv;
    }
}
