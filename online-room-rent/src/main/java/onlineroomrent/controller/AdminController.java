package onlineroomrent.controller;
import onlineroomrent.dto.*;
import onlineroomrent.jwtUtil.JwtAccessTokenUtil;
import onlineroomrent.service.FrontendService;
import onlineroomrent.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@RequestMapping("admin")
@RestController
public class AdminController {

    @Autowired
    FrontendService frontendService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtAccessTokenUtil jwtAccessTokenUtil;

    @GetMapping("/dashboard")
    public ModelAndView index(){
        TokenStatus tokenStatus = TenantContext.getCurrentTenant();
        ModelAndView mv= new ModelAndView();
        mv.addObject("Name","");
        if(tokenStatus!=null && tokenStatus.getStatus())
        mv.addObject("Name",jwtAccessTokenUtil.getPrincipalFromToken(tokenStatus.getAccessToken(),"Name"));
        mv.setViewName("admin-dashboard/dashboard");
        return mv;
    }

    @GetMapping("/add-category")
    public ModelAndView tables(){
        TokenStatus tokenStatus=TenantContext.getCurrentTenant();
        ModelAndView mv= new ModelAndView();
        mv.addObject("Name",jwtAccessTokenUtil.getPrincipalFromToken(tokenStatus.getAccessToken(),"Name"));
        mv.setViewName("admin-dashboard/add-category");
        return mv;
    }

    @PostMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestBody @Valid RoleDto roleDto){
         ApiResponse apiResponse = frontendService.updateRole(roleDto);
         return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/save-category-or-update-category")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid CategoryDto categoryDto){
        ApiResponse apiResponse = frontendService.updateCategory(categoryDto);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/check-connection")
    public ResponseEntity<?> checkConnection() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ModelAndView logOut() {
        TokenStatus tokenStatus=TenantContext.getCurrentTenant();
        frontendService.invalidateToken(tokenStatus.getAccessToken());
        return new ModelAndView("redirect:"+"/admin");
    }

    @GetMapping({"","/","/login"})
    public ModelAndView adminLogin() {
        TokenStatus tokenStatus = TenantContext.getCurrentTenant();
        ModelAndView mv=new ModelAndView();
        if(tokenStatus!=null && tokenStatus.getStatus()){
            return new ModelAndView("redirect:/admin"+"/dashboard");
        }
        mv.setViewName("/login");
        return mv;
    }

}
