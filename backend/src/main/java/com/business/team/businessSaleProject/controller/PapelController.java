package com.business.team.businessSaleProject.controller;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.core.security.authorization.*;
import com.business.team.businessSaleProject.dto.PapelDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.Papel;
import com.business.team.businessSaleProject.service.PapelService;
import com.business.team.businessSaleProject.util.JsonUtil;
import com.business.team.businessSaleProject.util.MappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("papeis")
@Authorization("papel")
public class PapelController {

    private final PapelService service;

    public PapelController(PapelService service) {
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
    public ResponseEntity<PapelDTO> update(@PathVariable Long id, @RequestBody PapelDTO newModelDTO) throws BusinessException {
        Papel newModel = MappingUtil.mapTo(newModelDTO, Papel.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.update(newModel, id), PapelDTO.class));
    }

    @UpdatePermission
    @PatchMapping(path = "/{id}")
    public ResponseEntity<PapelDTO> patch(@PathVariable Long id, @RequestBody PapelDTO newModelDTO) throws BusinessException {
        Papel newModel = MappingUtil.mapTo(newModelDTO, Papel.class);
        return ResponseEntity.ok(MappingUtil.mapTo(service.patch(newModel, id), PapelDTO.class));
    }

    @ReadPermission
    @GetMapping(path = "/{id}")
    public ResponseEntity<PapelDTO> getOne(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(MappingUtil.mapTo(service.getOne(id), PapelDTO.class));
    }

    @InsertPermission
    @PostMapping
    public ResponseEntity<PapelDTO> insert(@RequestBody PapelDTO modelDTO) throws BusinessException {
        Papel model = service.insert(MappingUtil.mapTo(modelDTO, Papel.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(MappingUtil.mapTo(model, PapelDTO.class));
    }

    @ReadPermission
    @GetMapping
    public ResponseEntity<PageDTO> listAll(HttpServletRequest request, @PathParam("filters") String filters) throws BusinessException {
        SearchDTO searchDTO = new SearchDTO();
        if (filters != null) {
            searchDTO = JsonUtil.fromJson(filters, SearchDTO.class);
        }
        PageDTO page = MappingUtil.mapPageItems(service.findPaginated(searchDTO), PapelDTO.class);
        return ResponseEntity.ok(page);
    }

}
