package com.business.team.businessSaleProject.service;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.*;
import com.business.team.businessSaleProject.dto.simple.*;
import com.business.team.businessSaleProject.repository.PermissaoRepository;
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
public class PermissaoService {

    @Autowired
    private PermissaoRepository repository;
      @Autowired
private PapelService papelService;


    @Transactional(readOnly = true)
    public Permissao getOne(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Permissao> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageDTO findPaginated(SearchDTO searchDTO) {
        Pageable pageable = createPageRequest(searchDTO);
        Page<Permissao> jpaPage = repository.findAll(searchDTO, pageable);
        return pageDTOFromJPAPage(jpaPage);
    }
    
    @Transactional
    public void preDelete(Long id) throws BusinessException {
           
    }

    @Transactional
    public void delete(Long id) throws BusinessException {
        preDelete(id);
        Permissao model = repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
        repository.delete(model);
    }

    @Transactional
    public void delete(Long[] ids) throws BusinessException {
        for (Long id: ids) {
            this.delete(id);
        }
    }

    private void validateInsert(Permissao model) throws BusinessException {
        // SonarLint
    }

    @Transactional
    public void preInsert(Permissao model) throws BusinessException {
           
          if (model.getPapeis() != null) {
    for(Papel papel: model.getPapeis()) {
        if (papel.getId() == null) {
            papelService.insert(papel);
        }
    }
}

    }

    @Transactional
    public Permissao insert(Permissao model) throws BusinessException {
        this.validateInsert(model);
        preInsert(model);
        return repository.save(model);
    }

    private void validateUpdate(Permissao model, Long id) throws BusinessException {
        Permissao dbModel = this.repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
    }

    @Transactional
    public void preUpdate(Permissao model) throws BusinessException {
           
    }

    @Transactional
    public Permissao update(Permissao newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        preUpdate(newModel);
        return update(newModel, id, false);
    }

    @Transactional
    public Permissao patch(Permissao newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        preUpdate(newModel);
        return update(newModel, id, true);
    }

    private Permissao update(Permissao newModel, Long id, boolean isPatch) throws BusinessException {
        Permissao dbModel = repository.findById(id).orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
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

    private PageDTO pageDTOFromJPAPage(Page<Permissao> jpaPage) {
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
    
     
    
     
    
      @Cacheable("permissoes-papel")
public Set<String> getPermissoes(Long papelId) {
    return new HashSet<>(this.repository.getPermissoesByPapel(papelId));
}

@Cacheable("permissoes-papel")
public Set<String> getPermissoes(Set<PapelSimpleDTO> papeis) {
    Set<String> permissoes = new HashSet<>();
    for(PapelSimpleDTO papel : papeis) {
        permissoes.addAll(this.getPermissoes(papel.getId()));
    }
    return permissoes;
}
    
}
