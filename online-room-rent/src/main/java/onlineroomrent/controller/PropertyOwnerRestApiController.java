package onlineroomrent.controller;
import onlineroomrent.dto.*;
import onlineroomrent.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RequestMapping("api/v1/property")
@RestController
public class PropertyOwnerRestApiController {
    @Autowired
    FrontendService frontendService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginDto loginDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@RequestBody @Valid RegisterRequest registerRequest, HttpServletRequest request){
        ApiResponse apiResponse= frontendService.registerOwner(registerRequest);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
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
