package com.business.team.businessSaleProject.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.business.team.businessSaleProject.core.dto.TokenDTO;
import com.business.team.businessSaleProject.core.dto.UserAuthenticateDTO;
import com.business.team.businessSaleProject.core.util.RoleUtil;
    import com.business.team.businessSaleProject.service.PapelService;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER = "Authorization";

    protected static final String HEADER_REFRESH = "Refresh-Token";

    protected static final String CLAIM_LOGIN = "Login-User";

    private final     PapelService papelService ;

    @Autowired
    public TokenAuthenticationService(    PapelService papelService ) {
            this.papelService = papelService; 
    }

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * Token Expiration.
     */
    @Value("${jwt.expiration}")
    private long EXPIRATION_TOKEN;

    /**
     * Refresh Token Expiration.
     */
    @Value("${jwt.refresh.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    /**
     * Adds the authentication in the response.
     *
     * @param dto Login DTO.
     */
    public UserAuthenticateDTO addAuthentication(UserAuthenticateDTO dto) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_LOGIN,    dto.getEmail() );

        String jwt = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(   dto.getEmail() )
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        String refresh = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(   dto.getEmail() )
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        dto.setToken(String.format("%s %s", TOKEN_PREFIX, jwt));
        dto.setRefreshToken(String.format("%s %s", TOKEN_PREFIX, refresh));

        return dto;
    }

    /**
     * Gets the authentication.
     *
     * @param request Request.
     * @return Authentication.
     */
    public Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim()).getBody();

            if(claims != null) {
                UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO(Long.valueOf(claims.getId()), claims.getSubject());

                    userAuthenticateDTO.setPapeis(RoleUtil.toPapeis(papelService.getPapeis(userAuthenticateDTO.getId())));  
                return new UsernamePasswordAuthenticationToken(userAuthenticateDTO, null, emptyList());
            }
            return null;
        }

        return null;
    }

    /**
     * Generate Token.
     *
     * @param username Username.
     * @return Token.
     */
    public String generateToken(String id, String username) {

        return Jwts.builder().setId(id).setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * Gets the refresh authentication.
     *
     * @param request Request.
     * @return Refresh Authentication.
     */
    public Authentication getRefreshAuthetication(HttpServletRequest request) {
        try {
            String refreshToken = new ObjectMapper().readValue(request.getInputStream(), TokenDTO.class).getToken();
            return getRefreshAuthentication(refreshToken);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Gets the refresh authentication for a String token
     * @param refreshToken Refresh token
     * @return Authentication with token user as principal
     */
    public Authentication getRefreshAuthentication(String refreshToken) {
        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody();

            if (claims != null) {
                UserAuthenticateDTO userDTO = new UserAuthenticateDTO(Long.valueOf(claims.getId()), claims.getSubject());
                return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
            }
        }
        return null;
    }

}
