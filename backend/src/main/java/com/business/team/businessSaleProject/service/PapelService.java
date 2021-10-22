package com.business.team.businessSaleProject.service;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.*;
import com.business.team.businessSaleProject.dto.simple.*;
import com.business.team.businessSaleProject.repository.PapelRepository;
import com.business.team.businessSaleProject.util.BeanUtils;
import com.business.team.businessSaleProject.util.TranslatorUtil;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PapelService {

    @Autowired
    private PapelRepository repository;
      @Autowired
private PermissaoService permissaoService;
@Autowired
private UsuarioService usuarioService;


    @Transactional(readOnly = true)
    public Papel getOne(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Papel> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageDTO findPaginated(SearchDTO searchDTO) {
        Pageable pageable = createPageRequest(searchDTO);
        Page<Papel> jpaPage = repository.findAll(searchDTO, pageable);
        return pageDTOFromJPAPage(jpaPage);
    }
    
    @Transactional
    public void preDelete(Long id) throws BusinessException {
            
    }

    @Transactional
    public void delete(Long id) throws BusinessException {
        preDelete(id);
        Papel model = repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
        repository.delete(model);
    }

    @Transactional
    public void delete(Long[] ids) throws BusinessException {
        for (Long id: ids) {
            this.delete(id);
        }
    }

    private void validateInsert(Papel model) throws BusinessException {
        // SonarLint
    }

    @Transactional
    public void preInsert(Papel model) throws BusinessException {
            
          if (model.getPermissoes() != null) {
    for(Permissao permissao: model.getPermissoes()) {
        if (permissao.getId() == null) {
            permissaoService.insert(permissao);
        }
    }
}
if (model.getUsuarios() != null) {
    for(Usuario usuario: model.getUsuarios()) {
        if (usuario.getId() == null) {
            usuarioService.insert(usuario);
        }
    }
}

    }

    @Transactional
    public Papel insert(Papel model) throws BusinessException {
        this.validateInsert(model);
        preInsert(model);
        return repository.save(model);
    }

    private void validateUpdate(Papel model, Long id) throws BusinessException {
        Papel dbModel = this.repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
    }

    @Transactional
    public void preUpdate(Papel model) throws BusinessException {
            
    }

    @Transactional
    public Papel update(Papel newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        preUpdate(newModel);
        return update(newModel, id, false);
    }

    @Transactional
    public Papel patch(Papel newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        preUpdate(newModel);
        return update(newModel, id, true);
    }

    private Papel update(Papel newModel, Long id, boolean isPatch) throws BusinessException {
        Papel dbModel = repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
        if(!dbModel.getId().equals(newModel.getId())) {
            throw new BusinessException(TranslatorUtil.ITEM_UPDATE_INCONSISTENT);
        }
        if(isPatch) {
            BeanUtils.copyNonNullProperties(dbModel, newModel);
        } else {
            BeanUtils.copyAllProperties(newModel, dbModel);
        }
        return repository.save(dbModel);
    }

    private PageDTO pageDTOFromJPAPage(Page<Papel> jpaPage) {
        PageDTO page = new PageDTO();
        page.setItems(jpaPage.getContent());
        page.setCurrentPage(jpaPage.getPageable().getPageNumber());
        page.setPageSize(jpaPage.getPageable().getPageSize());
        page.setTotalPages(jpaPage.getTotalPages());
        page.setTotalRecords(jpaPage.getTotalElements());
        return page;
    }

    private Pageable createPageRequest(SearchDTO searchDTO) {
        if(searchDTO.getSort().getColumns() == null) {
            return PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize());
        }
        String[] columns = searchDTO.getSort().getColumns().replace(" ", "").split(",");
        return PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize(),
                Sort.Direction.fromString(searchDTO.getSort().getOrder()), columns);
    }
    
     
    
    public Set<Papel> getPapeis(Long authId) {
    return this.repository.getPapeis(authId);
}
    
     
    
}
