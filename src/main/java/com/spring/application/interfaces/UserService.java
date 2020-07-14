package com.spring.application.interfaces;

import com.spring.application.entity.User;

/**
 * Интерфейс описывающий поведение пользователей
 */
public interface UserService {
    /**
     * Метод сохраняющий user в БД
     * @param user сущность описывающая пользователя
     * @return user сущность описывающая пользователя
     */
    User save(User user);

    /**
     * Метод удаляющий пользователя по id
     * @param id пользователя
     */
    void deleteById(Long id);

    /**
     * Метод удаляющий всех пользователей из БД
     */
    void deleteAll();

    /**
     * Метод получающий пользователя по id
     * @param id пользователя
     * @return user пользователь
     */
    User getUserById(Long id);
}
