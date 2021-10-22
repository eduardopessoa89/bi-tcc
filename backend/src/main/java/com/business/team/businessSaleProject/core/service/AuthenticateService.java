package com.business.team.businessSaleProject.core.service;

import com.business.team.businessSaleProject.core.dto.TokenDTO;
import com.business.team.businessSaleProject.core.dto.UserAuthenticateDTO;
import com.business.team.businessSaleProject.core.security.AccountCredentials;
import com.business.team.businessSaleProject.core.security.TokenAuthenticationService;
import com.business.team.businessSaleProject.core.util.RoleUtil;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.*;
import com.business.team.businessSaleProject.service.*;
import com.business.team.businessSaleProject.model.Usuario;
import com.business.team.businessSaleProject.service.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticateService {

    private final UsuarioService usuarioService; 

    private final      PermissaoService permissaoService;
    
    private final TokenAuthenticationService tokenService;


    public AuthenticateService(UsuarioService usuarioService,      PermissaoService permissaoService,
                               TokenAuthenticationService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
             this.permissaoService = permissaoService;
    }

    public Authentication tryAuthentication(AccountCredentials credenciais) throws BusinessException {
        Usuario usuario = usuarioService.login(credenciais.getEmail(), credenciais.getSenha());
        if(usuario == null) {
            throw new BusinessException("authentication.failure");
        }
        UserAuthenticateDTO userAuthenticateDTO = toDTO(usuario);
        tokenService.addAuthentication(userAuthenticateDTO);
        return new UsernamePasswordAuthenticationToken(userAuthenticateDTO, null, Collections.emptyList());
    }

    public UserAuthenticateDTO authenticate(AccountCredentials credenciais) throws BusinessException {
        Authentication authentication = tryAuthentication(credenciais);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (UserAuthenticateDTO) authentication.getPrincipal();
    }

    public Authentication tryRefresh(TokenDTO token) throws BusinessException {
        Authentication authentication = tokenService.getRefreshAuthentication(token.getToken());
        if(authentication == null) {
            throw new BusinessException("authentication.failure");
        }
        Usuario usuario = usuarioService.findByLogin(((UserAuthenticateDTO) authentication.getPrincipal()).getEmail());
        UserAuthenticateDTO userAuthenticateDTO = toDTO(usuario);
        tokenService.addAuthentication(userAuthenticateDTO);
        return new UsernamePasswordAuthenticationToken(userAuthenticateDTO, null, Collections.emptyList());
    }

    public UserAuthenticateDTO refresh(TokenDTO token) throws BusinessException {
        Authentication authentication = tryRefresh(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (UserAuthenticateDTO) authentication.getPrincipal();
    }

    public boolean verifyToken(HttpServletRequest request) throws BusinessException {
        Authentication authentication = tokenService.getAuthentication(request);
        if (authentication != null) {
            return true;
        }
        throw new BusinessException("authentication.failure");
    }

    private UserAuthenticateDTO toDTO(Usuario usuario) {
        UserAuthenticateDTO dto = new UserAuthenticateDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
dto.setSobrenome(usuario.getSobrenome());
 dto.setEmail(usuario.getEmail());
 
            Set<Papel> papeis = new HashSet<Papel>(usuario.getPapeis());
dto.setPapeis(RoleUtil.toPapeis(papeis));
        addPermissions(dto);
        return dto;
    }

   public void addPermissions(UserAuthenticateDTO dto) {
             dto.setPermissoes(new ArrayList<>(permissaoService.getPermissoes(  dto.getPapeis())));
    }

}
