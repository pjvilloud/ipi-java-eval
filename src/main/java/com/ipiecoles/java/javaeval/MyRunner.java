package com.ipiecoles.java.javaeval;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.javaeval.model.Technicien;
import com.ipiecoles.java.javaeval.repository.ManagerRepository;
import com.ipiecoles.java.javaeval.repository.TechnicienRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@Component
public class MyRunner implements CommandLineRunner {

    
    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise2";
        String user = "root";
        String pwd = "root";

        java.sql.Connection connexion = null;

        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
        } catch ( java.sql.SQLException e ) {
            //Problème de connexion à la base !
            print(e.getMessage());
        }
        return connexion;
    }

    public static void print(Object t) {
        System.out.println(t);
    }
    
    @Override
    public void run(String... strings) throws Exception {
        Connection connexion = initConnection();
        Statement statement = connexion.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employe LIMIT 10");
        while ( resultSet.next() ) {
            print(resultSet.getString("nom"));
            print(resultSet.getDate("dateEmbauche"));
        }

            
    }
    
}
