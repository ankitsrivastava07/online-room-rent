package onlineroomrent.controller;
import onlineroomrent.convertor.DtoToEntityConvertor;
import onlineroomrent.dao.entity.PropertyAdsEntity;
import onlineroomrent.dao.entity.PropertyCategoryEntity;
import onlineroomrent.dao.entity.Role;
import onlineroomrent.dao.entity.country.CityEntity;
import onlineroomrent.dao.entity.country.CountryEntity;
import onlineroomrent.dao.entity.country.StateEntity;
import onlineroomrent.dao.repository.*;
import onlineroomrent.dto.FilterBean;
import onlineroomrent.dto.PropertyAdsDto;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Autowired
    PropertyCategoryRepository propertyCategoryRepository;
    @Autowired
    PropertyAdsRepository propertyAdsRepository;
    @Autowired DtoToEntityConvertor dtoToEntityConvertor;

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
        List<StateEntity>state=stateRepository.findAll();
        List<CityEntity>city=cityRepository.findAll();
        List<String> propertyTypes= Arrays.asList("Rent","Resale","PG/Hotel","FlatMates");
        ModelAndView mv=new ModelAndView("online-room-rent-template/add-property");
        mv.addObject("countries",countries);
        mv.addObject("states",state);
        mv.addObject("cities",city);
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
        mv.addObject("propertyCategories",propertyCategoryRepository.findAll());
        mv.setViewName("online-room-rent-template/index");
        return mv;
    }

    @GetMapping("/propertyDetail/{propertyDetail}")
    public ModelAndView postDetail(@PathVariable String propertyDetail){
       String vars[]=propertyDetail.split("-For-");
       String state=vars[1].split(",")[1];
        List<PropertyAdsDto> dtos = frontendService.findAllPropertyAdsBySlugAndAdrress(vars[0],vars[1],state);
        ModelAndView mv= new ModelAndView();
        mv.addObject("dtos",dtos);
        mv.setViewName("online-room-rent-template/property_detail");
        return mv;
    }

    @GetMapping("/search-by-filter")
    public ModelAndView search(@RequestParam(name = "state") String  state, @RequestParam(name = "city") String city , @RequestParam(name = "locality") String locality, @RequestParam(name = "createdAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String createdAt, @RequestParam(name = "roomSet") String roomSet){
       ModelAndView mv= new ModelAndView();
       FilterBean filterBean= new FilterBean();
       filterBean.setCity(city);
       filterBean.setState(state);
       filterBean.setLocality(locality);
       filterBean.setRoomSet(roomSet);
       List<PropertyAdsEntity>list=propertyAdsRepository.findAll(filterBean.search());
       mv.addObject("dtos",dtoToEntityConvertor.convertToDto(list,PropertyAdsDto.class));
       mv.setViewName("online-room-rent-template/property_detail");
       return mv;
    }
}
