package com.business.team.businessSaleProject.service;

import com.business.team.businessSaleProject.core.dto.PageDTO;
import com.business.team.businessSaleProject.core.dto.SearchDTO;
import com.business.team.businessSaleProject.exception.BusinessException;
import com.business.team.businessSaleProject.model.PapelPermissao;
import com.business.team.businessSaleProject.repository.PapelPermissaoRepository;
import com.business.team.businessSaleProject.util.BeanUtils;
import com.business.team.businessSaleProject.util.TranslatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PapelPermissaoService {

    @Autowired
    private PapelPermissaoRepository repository;

    @Transactional(readOnly = true)
    public PapelPermissao getOne(Long id) throws BusinessException {
        return repository
                .findById(id)
                .orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<PapelPermissao> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageDTO findPaginated(SearchDTO searchDTO) {
        Pageable pageable = createPageRequest(searchDTO);
        Page<PapelPermissao> jpaPage = repository.findAll(searchDTO, pageable);
        return pageDTOFromJPAPage(jpaPage);
    }

    @Transactional
    public void delete(Long id) throws BusinessException {
        PapelPermissao model = repository
                .findById(id)
                .orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
        repository.delete(model);
    }

    @Transactional
    public void delete(Long[] ids) throws BusinessException {
        for (Long id : ids) {
            this.delete(id);
        }
    }

    private void validateInsert(PapelPermissao model) throws BusinessException {
        // SonarLint
    }

    @Transactional
    public void preInsert(PapelPermissao model) throws BusinessException {

    }

    @Transactional
    public PapelPermissao insert(PapelPermissao model) throws BusinessException {
        this.validateInsert(model);
        preInsert(model);
        return repository.save(model);
    }

    @Transactional
    public List<PapelPermissao> insertMultiple(List<PapelPermissao> models) throws BusinessException {
        List<PapelPermissao> insertedModels = new ArrayList<>();
        for (PapelPermissao model: models) {
            PapelPermissao papelPermissao = this.repository.findPapelPermissaoByPapelAndPermissao(model.getPapel().getId(), model.getPermissao().getId());
            if (papelPermissao == null) {
                insertedModels.add(this.insert(model));
            }
        }
        return insertedModels;
    }

    private void validateUpdate(PapelPermissao model, Long id) throws BusinessException {
        PapelPermissao dbModel =
                this.repository.findById(id)
                        .orElseThrow(
                                () -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND)
                        );
    }

    @Transactional
    public PapelPermissao update(PapelPermissao newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        return update(newModel, id, false);
    }

    @Transactional
    public PapelPermissao patch(PapelPermissao newModel, Long id) throws BusinessException {
        this.validateUpdate(newModel, id);
        return update(newModel, id, true);
    }

    private PapelPermissao update(PapelPermissao newModel, Long id, boolean isPatch)
            throws BusinessException {
        PapelPermissao dbModel = repository
                .findById(id)
                .orElseThrow(() -> new BusinessException(TranslatorUtil.ITEM_NOT_FOUND));
        if (!dbModel.getId().equals(newModel.getId())) {
            throw new BusinessException(TranslatorUtil.ITEM_UPDATE_INCONSISTENT);
        }
        if (isPatch) {
            BeanUtils.copyNonNullProperties(dbModel, newModel);
        } else {
            BeanUtils.copyAllProperties(newModel, dbModel);
        }
        return repository.save(dbModel);
    }

    private PageDTO pageDTOFromJPAPage(Page<PapelPermissao> jpaPage) {
        PageDTO page = new PageDTO();
        page.setItems(jpaPage.getContent());
        page.setCurrentPage(jpaPage.getPageable().getPageNumber());
        page.setPageSize(jpaPage.getPageable().getPageSize());
        page.setTotalPages(jpaPage.getTotalPages());
        page.setTotalRecords(jpaPage.getTotalElements());
        return page;
    }

    private Pageable createPageRequest(SearchDTO searchDTO) {
        if (searchDTO.getSort().getColumns() == null) {
            return PageRequest.of(
                    searchDTO.getCurrentPage(),
                    searchDTO.getPageSize()
            );
        }
        String[] columns = searchDTO
                .getSort()
                .getColumns()
                .replace(" ", "")
                .split(",");
        return PageRequest.of(
                searchDTO.getCurrentPage(),
                searchDTO.getPageSize(),
                Sort.Direction.fromString(searchDTO.getSort().getOrder()),
                columns
        );
    }

}
