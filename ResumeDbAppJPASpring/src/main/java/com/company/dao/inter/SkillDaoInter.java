/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 *
 * @author Dell
 */
public interface SkillDaoInter {

    public List<Skill> getAll();
    
    public Skill getById(int id);
    
    public boolean insert(Skill s);
    
    public boolean update(Skill u);
}
