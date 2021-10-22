package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.PapelPermissaoDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.PapelPermissao;
import com.business.team.businessSaleProject.service.PapelPermissaoService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("papeis-permissoes")
@Authorization("papel-permissao")
public class PapelPermissaoController {

    private final PapelPermissaoService service;

    public PapelPermissaoController(PapelPermissaoService service) {
        this.service = service;
    }

    @InsertPermission
    @PostMapping(value = "multiple")
    public ResponseEntity<List<PapelPermissao>> insertMultiple(@RequestBody List<PapelPermissaoDTO> modelsDTO)
            throws BusinessException {
        List<PapelPermissao> insertedModels = service.insertMultiple(MappingUtil.mapToList(modelsDTO, PapelPermissao.class));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MappingUtil.mapToList(insertedModels, PapelPermissaoDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<PapelPermissaoDTO> insert(@RequestBody PapelPermissaoDTO papelPermissaoDTO) throws BusinessException {
        PapelPermissao model = service.insert(MappingUtil.mapTo(papelPermissaoDTO, PapelPermissao.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, PapelPermissaoDTO.class));
    }

    @UpdatePermission
    @PutMapping(path = "/{id}")
    public ResponseEntity<PapelPermissaoDTO> update(@PathVariable Long id, @RequestBody PapelPermissaoDTO newPapelPermissaoDTO) throws BusinessException {
        PapelPermissao newPapelPermissao = MappingUtil.mapTo(newPapelPermissaoDTO, PapelPermissao.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newPapelPermissao, id), PapelPermissaoDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<PapelPermissaoDTO> patch(@PathVariable Long id, @RequestBody PapelPermissaoDTO newPapelPermissaoDTO) throws BusinessException {
        PapelPermissao newPapelPermissao = MappingUtil.mapTo(newPapelPermissaoDTO, PapelPermissao.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newPapelPermissao, id), PapelPermissaoDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<PapelPermissaoDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), PapelPermissaoDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), PapelPermissao.class);
        return ResponseEntity.ok(page);
    }

    @DeletePermission
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws BusinessException {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
