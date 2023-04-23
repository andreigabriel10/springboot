package ro.itschool.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.itschool.springboot.models.dtos.OrderDTO;
import ro.itschool.springboot.models.dtos.UserDTO;
import ro.itschool.springboot.models.entities.Order;
import ro.itschool.springboot.models.entities.User;
import ro.itschool.springboot.repositories.OrderRepository;
import ro.itschool.springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository,
                           OrderRepository orderRepository,
                           ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User userToSave = objectMapper.convertValue(userDTO, User.class);
        //save user to the database test
        User userSaved = userRepository.save(userToSave);
        UserDTO userSavedDTO = objectMapper.convertValue(userSaved, UserDTO.class);

        return userSavedDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> savedUsers = userRepository.findAll();
        List<UserDTO> savedUsersDTO = new ArrayList<>();
        savedUsers.forEach(user -> {
            savedUsersDTO.add(objectMapper.convertValue(user, UserDTO.class));
        });

        return savedUsersDTO;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public OrderDTO createOrder(Long userId, OrderDTO orderDTO) {
        //check if user by userId exists
        User userFound = userRepository.findById(userId).orElseThrow();
        Order order = objectMapper.convertValue(orderDTO, Order.class);
        //set user on order
        order.setUser(userFound);
        //save order to the database - orders table
        Order orderSaved = orderRepository.save(order);

        return objectMapper.convertValue(orderSaved, OrderDTO.class);
    }
}