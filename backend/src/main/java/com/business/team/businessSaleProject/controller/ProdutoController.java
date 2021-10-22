package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.ProdutoDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.Produto;
import com.business.team.businessSaleProject.service.ProdutoService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("produtos")
@Authorization("produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
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
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO newModelDTO) throws BusinessException {
        Produto newModel = MappingUtil.mapTo(newModelDTO, Produto.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), ProdutoDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProdutoDTO> patch(@PathVariable Long id, @RequestBody ProdutoDTO newModelDTO) throws BusinessException {
        Produto newModel = MappingUtil.mapTo(newModelDTO, Produto.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), ProdutoDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProdutoDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), ProdutoDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO modelDTO) throws BusinessException {
        Produto model = service.insert(MappingUtil.mapTo(modelDTO, Produto.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, ProdutoDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), ProdutoDTO.class);
        return ResponseEntity.ok(page);
    }

}
