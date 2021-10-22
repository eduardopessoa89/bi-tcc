package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.VendaDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.Venda;
import com.business.team.businessSaleProject.service.VendaService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("vendas")
@Authorization("venda")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
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
    public ResponseEntity<VendaDTO> update(@PathVariable Long id, @RequestBody VendaDTO newModelDTO) throws BusinessException {
        Venda newModel = MappingUtil.mapTo(newModelDTO, Venda.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), VendaDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<VendaDTO> patch(@PathVariable Long id, @RequestBody VendaDTO newModelDTO) throws BusinessException {
        Venda newModel = MappingUtil.mapTo(newModelDTO, Venda.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), VendaDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<VendaDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), VendaDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<VendaDTO> insert(@RequestBody VendaDTO modelDTO) throws BusinessException {
        Venda model = service.insert(MappingUtil.mapTo(modelDTO, Venda.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, VendaDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), VendaDTO.class);
        return ResponseEntity.ok(page);
    }

}
