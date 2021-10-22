package com.business.team.businessSaleProject.core.security;

import com.business.team.businessSaleProject.core.dto.UserAuthenticateDTO;
import com.business.team.businessSaleProject.core.security.authorization.Authorization;
import com.business.team.businessSaleProject.dto.simple.*;
import com.business.team.businessSaleProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SecurityService {

    private static final String OPERATION_PATTERN = "%s-%s";

         /**
  * Permissao service.
  */
@Autowired
private PermissaoService permissaoService;



    /**
     * Allows or not the user to perform the operation.
     *
     * @param instance
     * 		Component instace.
     * @param operation
     * 		Operation to be performed.
     * @return If the user can perform the operation.
     */
    public boolean allow(Object instance, String operation) {
        return allow(false, instance, operation);
    }

    /**
     * Allows or not the user to perform the operation.
     *
     * @param custom
     * 		If it is a custom operation.
     * @param instance
     * 		Component instace.
     * @param operation
     * 		Operation to be performed.
     * @return If the user can perform the operation.
     */
    public boolean allow(boolean custom, Object instance, String operation) {
        String wildcard = String.format(OPERATION_PATTERN, operation.toLowerCase(), "*");

        if (!custom) {
            Authorization auth = AnnotationUtils.findAnnotation(instance.getClass(), Authorization.class);

            if (auth != null) {
                operation = String.format(OPERATION_PATTERN, operation.toLowerCase(), auth.value().toLowerCase());
            }
        }

        Set<String> permissions =      permissaoService.getPermissoes(getUserAuthenticateRoles());
        return permissions.contains(wildcard) || permissions.contains(operation);
    }

        protected Set<PapelSimpleDTO> getUserAuthenticateRoles() {
    return getUserAuthenticate().getPapeis();
} 

    protected UserAuthenticateDTO getUserAuthenticate() {
        return (UserAuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
