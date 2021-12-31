package onlineroomrent.controller;

import onlineroomrent.dto.*;
import onlineroomrent.service.FrontendService;
import onlineroomrent.service.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequestMapping("/api/v1/admin")
@RestController
public class AdminControllerRestApi {
    @Autowired FrontendService frontendService;
    @Autowired AuthenticationManager authenticationManager;
   // @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AdminLogin adminLogin){
        UserAuthentication userAuthentication=(UserAuthentication) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminLogin.getEmailOrMobile(),adminLogin.getPassword()));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(userAuthentication.getStatus());
        apiResponse.setMessage(userAuthentication.getMessage());
        apiResponse.setAccessToken(userAuthentication.getAccessToken());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

  //  @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryDto categoryDto){
        ApiResponse apiResponse= frontendService.updateCategory(categoryDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid Admin adminDto){
        ApiResponse apiResponse= frontendService.saveAdmin(adminDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
