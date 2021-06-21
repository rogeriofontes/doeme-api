package br.com.doeme.grantee.model.service.impl;

import br.com.doeme.grantee.model.entity.Grantee;
import br.com.doeme.grantee.model.repositories.GranteeRepository;
import br.com.doeme.grantee.model.service.GranteeService;
import br.com.doeme.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GranteeServiceImpl implements GranteeService {

    @Autowired
    private GranteeRepository granteeRepository;

    @Override
    public Optional<Grantee> findByName(String name) {
        return granteeRepository.findByName(name);
    }

    @Override
    public Grantee save(Grantee grantee) {
        if (StringUtils.isEmpty(grantee.getCode())) {
            grantee.setCode(UUIDUtil.shortUUID());
        }
        return granteeRepository.save(grantee);
    }

    @Override
    public Grantee update(Long id, Grantee grantee) {
        Optional<Grantee> fornecedorById = findById(id);
        if (fornecedorById.isPresent()) {
            var pessoaUpdate = fornecedorById.get();
            pessoaUpdate.update(id, grantee);
            return granteeRepository.save(pessoaUpdate);
        }

        return new Grantee();
    }

    @Override
    public List<Grantee> list() {
        return granteeRepository.findAll();
    }

    @Override
    public Optional<Grantee> findById(Long id) {
        return granteeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        granteeRepository.deleteById(id);
    }
}
