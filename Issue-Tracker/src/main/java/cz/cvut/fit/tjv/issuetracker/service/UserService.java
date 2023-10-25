package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService extends CrudService<Integer, User, UserDTO, UserCreateDTO>{

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
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
}
