package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.ItemVendaDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.ItemVenda;
import com.business.team.businessSaleProject.service.ItemVendaService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("itensvenda")
@Authorization("itemvenda")
public class ItemVendaController {

    private final ItemVendaService service;

    public ItemVendaController(ItemVendaService service) {
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
    public ResponseEntity<ItemVendaDTO> update(@PathVariable Long id, @RequestBody ItemVendaDTO newModelDTO) throws BusinessException {
        ItemVenda newModel = MappingUtil.mapTo(newModelDTO, ItemVenda.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), ItemVendaDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ItemVendaDTO> patch(@PathVariable Long id, @RequestBody ItemVendaDTO newModelDTO) throws BusinessException {
        ItemVenda newModel = MappingUtil.mapTo(newModelDTO, ItemVenda.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), ItemVendaDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<ItemVendaDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), ItemVendaDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<ItemVendaDTO> insert(@RequestBody ItemVendaDTO modelDTO) throws BusinessException {
        ItemVenda model = service.insert(MappingUtil.mapTo(modelDTO, ItemVenda.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, ItemVendaDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), ItemVendaDTO.class);
        return ResponseEntity.ok(page);
    }

}
