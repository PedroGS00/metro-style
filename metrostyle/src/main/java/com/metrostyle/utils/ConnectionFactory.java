package com.metrostyle.utils;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class ConnectionFactory { 
    public static Connection getConnection() { 
        try { 
            //Opcional : Registro o driver 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            //Caminho do banco de dados 
            String url = "jdbc:mysql://localhost:3306/db_metroStyle"; 
            //Retorno o objeto de conexão 
            return DriverManager.getConnection(url,"root","");
        }
        catch(SQLException ex) { 
            //Registro o erro no log do tomcat 
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex); throw new RuntimeException("Erro ao abrir conexão", ex); 
        }
        catch (ClassNotFoundException ex) { 
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex); throw new RuntimeException("Erro ao registrar driver do JDBC", ex); 
        } 
    } 
}
