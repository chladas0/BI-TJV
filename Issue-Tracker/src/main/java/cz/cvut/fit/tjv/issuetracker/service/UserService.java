package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Finds -----------------------------------------------------------------------------------------------------------
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<UserDTO> findByIDasDTO(int id)
    {
        return toDTO(userRepository.findById(id));
    }


    public List<UserDTO> findAll()
    {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }


    public List<User> findByIDs(List<Integer> ids)
    {
        return userRepository.findAllById(ids);
    }
    // -----------------------------------------------------------------------------------------------------------------

    public UserDTO create(UserCreateDTO user)
    {
        return toDTO(userRepository.save(new User(user.getUsername(), user.getPassword())));
    }

    // method is public and called from outside, transaction, commit, saved when its managed
    @Transactional
    public UserDTO update(int id, UserCreateDTO userCreateDto) throws Exception {
        Optional<User> optUser = userRepository.findById(id);

        // todo exception class catch in controllers

        if(optUser.isEmpty())
            throw new Exception("No such user {fix me :)}");

        User user = optUser.get();
        user.setUsername(userCreateDto.getUsername());

        return toDTO(user);
    }


    // Helpers ---------------------------------------------------------------------------------------------------------
    private UserDTO toDTO(User user)
    {
        return new UserDTO(user.getId(), user.getUsername());
    }

    private Optional<UserDTO> toDTO(Optional<User> user)
    {
        return user.map(this::toDTO);
    }
    // -----------------------------------------------------------------------------------------------------------------

}
