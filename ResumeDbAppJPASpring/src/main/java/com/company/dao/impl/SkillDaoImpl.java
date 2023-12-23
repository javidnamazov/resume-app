/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try ( Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill;");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getSkill(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Skill s) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?);");
            stmt.setString(1, s.getName());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Skill getById(int id) {
        Skill result = new Skill();
        try ( Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from skill where id="+ id + ";");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getSkill(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Skill u) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update skill set name=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
