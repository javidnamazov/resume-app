/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public abstract class AbstractDAO {

    public Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }

//    private static EntityManagerFactory emf = null;
//
//    public EntityManager em() {
//        if (emf == null) {
//            emf = Persistence.createEntityManagerFactory("resumeappPU");
//        }
//        EntityManager em = emf.createEntityManager();
//        return em;
//    }
//
//    public void closeEmf() {
//        emf.close();
//    }
}
