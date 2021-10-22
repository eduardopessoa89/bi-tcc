package com.business.team.businessSaleProject.core.controller;

import com.business.team.businessSaleProject.core.dto.TokenDTO;
import com.business.team.businessSaleProject.core.dto.UserAuthenticateDTO;
import com.business.team.businessSaleProject.core.security.AccountCredentials;
import com.business.team.businessSaleProject.core.service.AuthenticateService;
import com.business.team.businessSaleProject.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest Controller for Authentication
 */
@RestController
@Api(value = "authentication", tags = "authentication-controller")
public class AuthenticationController {

    /**
     * Service de autenticação
     */
    private final AuthenticateService authService;

    /**
     * Autowired constructor
     * @param authService Service de autenticação
     */
    public AuthenticationController(AuthenticateService authService) {
        this.authService = authService;
    }


    @PostMapping(value = "/login")
    @ApiOperation(value = "Login user")
    public ResponseEntity<UserAuthenticateDTO> login(@RequestBody AccountCredentials credentials) throws BusinessException {
        return ResponseEntity.ok(authService.authenticate(credentials));
    }

    @PostMapping(value = "/refresh")
    @ApiOperation(value = "Refresh token")
    public ResponseEntity<UserAuthenticateDTO> refresh(@RequestBody TokenDTO body) throws BusinessException {
        return ResponseEntity.ok(authService.refresh(body));
    }

    @GetMapping(value = "/verify")
    @ApiOperation(value = "Verify token")
    public ResponseEntity<?> verify(HttpServletRequest request) throws BusinessException {
        if (authService.verifyToken(request)) {
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
    }

}
