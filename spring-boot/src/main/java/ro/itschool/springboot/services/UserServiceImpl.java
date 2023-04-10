
package ro.itschool.springboot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.itschool.springboot.models.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    List<UserDTO> userDTOList = new ArrayList<>();

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getAge() < 18 || userDTO.getAge() > 120) {
            log.error("Invalid age.");
        } else {
            userDTOList.add(userDTO);
            log.info("User created: " + userDTO.getName());
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("Available users: " + userDTOList);
        return userDTOList;
    }

    @Override
    public UserDTO deleteUserByName(String name) {
        for (UserDTO user : userDTOList) {
            if (user.getName().equalsIgnoreCase(name)) {
                userDTOList.remove(user);
                log.info("User removed: " + user.getName());
                return user;
            }
        }
        return null;
    }
}