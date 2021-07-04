package br.com.doeme.user.service;

import br.com.doeme.beneficiary.model.entity.Beneficiary;
import br.com.doeme.beneficiary.model.repositories.BeneficiaryRepository;
import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.donor.model.repositories.DonorRepository;
import br.com.doeme.exceptions.ResourceFoundException;
import br.com.doeme.exceptions.ResourceNotFoundException;
import br.com.doeme.filter.JWTUtil;
import br.com.doeme.grantee.model.repositories.GranteeRepository;
import br.com.doeme.ong.model.entity.Ong;
import br.com.doeme.ong.model.repositories.OngRepository;
import br.com.doeme.user.dto.RegisterResponse;
import br.com.doeme.user.dto.TokenResponse;
import br.com.doeme.user.entiry.Profile;
import br.com.doeme.user.entiry.User;
import br.com.doeme.user.entiry.UserType;
import br.com.doeme.user.repositories.PerfilRepository;
import br.com.doeme.user.repositories.UserRepository;
import br.com.doeme.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordCryptoService passwordCryptoService;

    @Autowired
    private GranteeRepository granteeRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Override
    public TokenResponse getLoginAndReturnToken(User user) throws ResourceNotFoundException {
        Optional<User> userOptional = getUserByEmail(user);
        if (userOptional.isPresent()) {
            return TokenResponse.builder()
                    .userId(userOptional.get().getId())
                    .token(JWTUtil.createToken(userOptional.get().getEmail()))
                    .roles(userOptional.get().getProfiles()).build();
        }

        return null;
    }

    @Override
    public RegisterResponse register(User user) throws ResourceFoundException, ResourceNotFoundException {
        Long beneficiaryId = null;
        Long donorId = null;
        Long ongId = null;

        if (checkIfUserExist(user.getEmail())) {
            throw new ResourceFoundException("User já existe!");
        } else {

            Set<Profile> basicProfile = getBasicProfile(user.getUserType());
            user.setProfiles(basicProfile);
            user.setPassword(getCryptoPassword(user.getPassword()));
            if (StringUtils.isEmpty(user.getCode())) {
                user.setCode(UUIDUtil.shortUUID());
            }
            User userCreated = userRepository.save(user);

            if (userCreated.getUserType().equals(UserType.NGO)) {
                Ong ong = Ong.builder().user(userCreated).build();
                Ong ongSaved = ongRepository.save(ong);
                ongId = ongSaved.getId();
            } else if (userCreated.getUserType().equals(UserType.DONOR)) {
                Donor donor = Donor.builder().user(userCreated).build();
                Donor donorSaved = donorRepository.save(donor);
                donorId = donorSaved.getId();
            } else if (userCreated.getUserType().equals(UserType.GRANTEE)) {
                Beneficiary beneficiary = Beneficiary.builder().user(userCreated).build();
                Beneficiary beneficiarySaved = beneficiaryRepository.save(beneficiary);
                beneficiaryId = beneficiarySaved.getId();
            }

            return RegisterResponse.builder().id(userCreated.getId()).beneficiaryId(beneficiaryId).donorId(donorId).ongId(ongId).user(userCreated).build();
        }
    }

    private Set<Profile> getBasicProfile(UserType userType) throws ResourceNotFoundException {
        String type = null;
        String userTypeByType = UserType.findUserTypeByType(userType);
        if (Objects.isNull(userTypeByType)) {
            type = "USER";
        } else {
            type = userTypeByType;
        }

        Set<Profile> profiles = new HashSet<>();

        Optional<Profile> profileOptional = perfilRepository.findByRole(type);

        if (profileOptional.isPresent()) {
            profiles.add(profileOptional.get());
        } else {
            throw new ResourceNotFoundException("Perfil não encontrado!");
        }

        return profiles;
    }

    private Optional<User> getUserByEmail(User user) throws ResourceNotFoundException {
        UserDetails userResult = getUserDetails(user);
        return getByEmail(userResult.getUsername());
    }

    private UserDetails getUserDetails(User user) throws ResourceNotFoundException {
        UserDetails userResult = userDetailsService.loadUserByUsername(user.getEmail());
        if (userResult == null) {
            throw new ResourceNotFoundException("Usuário não cadastrado");
        }
        return userResult;
    }

    private boolean checkIfUserExist(String email) {
        return getByEmail(email).isPresent() ? Boolean.TRUE : Boolean.FALSE;
    }

    private Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Optional<br.com.doeme.user.entiry.Profile> getByPerfilRole(String role) {
        return perfilRepository.findByRole(role);
    }

    private String getCryptoPassword(String password) {
        return passwordCryptoService.encrypt(password);
    }

}
