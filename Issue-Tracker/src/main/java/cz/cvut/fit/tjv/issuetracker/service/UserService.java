package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import cz.cvut.fit.tjv.issuetracker.exception.IllegalDataException;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<Integer, User, UserDTO, UserCreateDTO>{

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public UserDTO create (UserCreateDTO userCreateDTO)
    {
        try{
            return super.create(userCreateDTO);
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
}
