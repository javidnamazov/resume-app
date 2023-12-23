/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.service.impl;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dell
 */
@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    @Qualifier("userDao1")
    private UserRepositoryCustom userDao;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname, nationalityId);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) { //Native SQL
        return userDao.findByEmail(email);
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public boolean add(User u) {
        return userDao.add(u);
    }

    @Override
    public boolean update(User u) {
        return userDao.update(u);
    }

    @Override
    public boolean remove(int id) {
        return userDao.remove(id);
    }
}
