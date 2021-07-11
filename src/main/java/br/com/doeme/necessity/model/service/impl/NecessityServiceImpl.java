package br.com.doeme.necessity.model.service.impl;

import br.com.doeme.necessity.model.entity.Necessity;
import br.com.doeme.necessity.model.repositories.NecessityRepository;
import br.com.doeme.necessity.model.service.NecessityService;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.repositories.UserRepository;
import br.com.doeme.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NecessityServiceImpl implements NecessityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NecessityRepository necessityRepository;

    @Override
    public Necessity save(Necessity necessity) {
        if (StringUtils.isEmpty(necessity.getCode())) {
            necessity.setCode(UUIDUtil.shortUUID());
        }
        return necessityRepository.save(necessity);
    }

    private User getUserById(Necessity necessity) {


        return null;
    }

    @Override
    public Necessity update(Long id, Necessity necessity) {
        Optional<Necessity> fornecedorById = findById(id);
        if (fornecedorById.isPresent()) {
            var pessoaUpdate = fornecedorById.get();
            pessoaUpdate.update(id, necessity);
            return necessityRepository.save(pessoaUpdate);
        }

        return new Necessity();
    }

    @Override
    public List<Necessity> list() {
        return necessityRepository.findAll();
    }

    @Override
    public Optional<Necessity> findById(Long id) {
        return necessityRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        necessityRepository.deleteById(id);
    }

    @Override
    public Optional<Necessity> findByBeneficiaryId(Long granteeId) {
        return necessityRepository.findByBeneficiaryId(granteeId);
    }


}
