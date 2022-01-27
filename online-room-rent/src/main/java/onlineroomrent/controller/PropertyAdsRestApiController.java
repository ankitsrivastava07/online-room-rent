package onlineroomrent.controller;
import onlineroomrent.dto.*;
import onlineroomrent.service.FrontendService;
import onlineroomrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@RequestMapping("api/v1/property")
@RestController
public class PropertyAdsRestApiController {
    @Autowired
    FrontendService frontendService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginDto loginDto) {
        ApiResponse apiResponse=userService.findByEmailAndPassword(loginDto.getEmailOrMobile(),loginDto.getPassword());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/owner/register")
    public ResponseEntity<?> registerOwner(@RequestBody @Valid RegisterRequest registerRequest, HttpServletRequest request){
         ApiResponse apiResponse= frontendService.registerOwner(registerRequest);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/save-property")
    public ResponseEntity<?> saveProperty(@Valid PostProperty postProperty, HttpServletRequest request){
        ApiResponse apiResponse= frontendService.saveProperty(postProperty);
        return new ResponseEntity<>(apiResponse,apiResponse.getIsValidFile()?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/post-property")
    public ResponseEntity<?> saveProperty(@RequestBody @Valid PostProperty postProperty){
        return null;
    }

    @PostMapping("/sell-property")
    public ResponseEntity<?> sellProperty(@RequestBody @Valid SellProperty sellProperty){
        return null;
    }

  /*  @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        return new ResponseEntity<>(frontendService.registerUser(registerRequest), HttpStatus.OK);
    }*/

}
