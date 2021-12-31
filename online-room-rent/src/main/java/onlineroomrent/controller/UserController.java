package onlineroomrent.controller;

import onlineroomrent.dto.LoginDto;
import onlineroomrent.dto.RegisterDto;
import onlineroomrent.dto.RegisterRequest;
import onlineroomrent.service.FrontendService;
import onlineroomrent.validation.ValidationFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    @Autowired
    FrontendService frontendService;

    @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto){
       return new ResponseEntity<>("", HttpStatus.OK);
   }

}
