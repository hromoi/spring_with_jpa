package com.spring.application.service;

import com.spring.application.entity.User;
import com.spring.application.interfaces.UserService;
import com.spring.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс реалезующий бизнес логику для пользователей
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод сохраняющий user в БД
     * @param user сущность описывающая пользователя
     * @return user сущность описывающая пользователя
     */
    @Transactional
    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * Метод удаляющий пользователя по id
     * @param id пользователя
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Метод удаляющий всех пользователей из БД
     */
    @Transactional
    @Override
    public void deleteAll(){
        userRepository.deleteAllInBatch();
    }

    /**
     * Метод получающий пользователя по id
     * @param id пользователя
     * @return user пользователь
     */
    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id){
        return userRepository.getOne(id);
    }
}
