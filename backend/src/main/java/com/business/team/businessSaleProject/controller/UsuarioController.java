package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.UsuarioDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.Usuario;
import com.business.team.businessSaleProject.service.UsuarioService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("usuarios")
@Authorization("usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @DeletePermission
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws BusinessException {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @UpdatePermission
    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO newModelDTO) throws BusinessException {
        Usuario newModel = MappingUtil.mapTo(newModelDTO, Usuario.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), UsuarioDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> patch(@PathVariable Long id, @RequestBody UsuarioDTO newModelDTO) throws BusinessException {
        Usuario newModel = MappingUtil.mapTo(newModelDTO, Usuario.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), UsuarioDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), UsuarioDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO modelDTO) throws BusinessException {
        Usuario model = service.insert(MappingUtil.mapTo(modelDTO, Usuario.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, UsuarioDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), UsuarioDTO.class);
        return ResponseEntity.ok(page);
    }

}
