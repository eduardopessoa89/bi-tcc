package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.PermissaoDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.Permissao;
import com.business.team.businessSaleProject.service.PermissaoService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("permissoes")
@Authorization("permissao")
public class PermissaoController {

    private final PermissaoService service;

    public PermissaoController(PermissaoService service) {
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
    public ResponseEntity<PermissaoDTO> update(@PathVariable Long id, @RequestBody PermissaoDTO newModelDTO) throws BusinessException {
        Permissao newModel = MappingUtil.mapTo(newModelDTO, Permissao.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), PermissaoDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<PermissaoDTO> patch(@PathVariable Long id, @RequestBody PermissaoDTO newModelDTO) throws BusinessException {
        Permissao newModel = MappingUtil.mapTo(newModelDTO, Permissao.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), PermissaoDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<PermissaoDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), PermissaoDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<PermissaoDTO> insert(@RequestBody PermissaoDTO modelDTO) throws BusinessException {
        Permissao model = service.insert(MappingUtil.mapTo(modelDTO, Permissao.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, PermissaoDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), PermissaoDTO.class);
        return ResponseEntity.ok(page);
    }

}
