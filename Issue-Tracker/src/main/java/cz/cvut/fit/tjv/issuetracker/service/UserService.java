package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.exception.IllegalDataException;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CrudService<Integer, User, UserDTO, UserCreateDTO>{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO create (UserCreateDTO userCreateDTO)
    {
        UserCreateDTO userWithHashedPassword = new UserCreateDTO(userCreateDTO.getUsername(),
                passwordEncoder.encode(userCreateDTO.getPassword()));
        try{
            return super.create(userWithHashedPassword);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new IllegalDataException("Username is not unique");
        }
    }

    @Override
    protected User updateEntity(User existingEntity, UserCreateDTO e) {
        existingEntity.setUsername(e.getUsername());
        existingEntity.setPassword(e.getPassword());
        return existingEntity;
    }

    @Override
    protected UserDTO toDTO(User user)
    {
        return new UserDTO(user.getId(), user.getUsername());
    }

    @Override
    protected User toEntity(UserCreateDTO userCreateDTO) {
        return new User(userCreateDTO.getUsername(), userCreateDTO.getPassword());
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
