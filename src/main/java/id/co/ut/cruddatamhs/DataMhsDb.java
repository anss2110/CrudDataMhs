/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.co.ut.cruddatamhs;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author anand
 */
public class DataMhsDb {    
    Connection con;
    Dotenv dotenv = Dotenv.load();
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public DataMhsDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dotenv.get("DB_CONN"), dotenv.get("DB_UNAME"), dotenv.get("DB_PASS"));
            System.out.println("Koneksi Berhasil...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    public void insertDB(String nama, String nim, String jurusan, String alamat, String phone) {
        try {
            String sql = "insert into data_pribadi (`nama`, `nim`, `jurusan`, `alamat`, `phone`) values (?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1,nama);
            pst.setString(2,nim);
            pst.setString(3,jurusan);
            pst.setString(4,alamat);
            pst.setString(5,phone);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet selectDB() {
        try {
            String sql = "select * from data_pribadi";
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void deleteDB(String nim) {
        try {
            String sql = "delete from data_pribadi where nim =?";
            pst = con.prepareStatement(sql);
            pst.setString(1, nim);
            pst.executeUpdate();
            System.out.println("(DEBUG) deleteDB oke");
        } catch (SQLException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("(DEBUG) deleteDB failed");
        }
    }
    
    public void updateDB(String nama, String nim, String jurusan, String alamat, String phone) {
        try {
            String sql = "update data_pribadi set nama =?, nim =?, jurusan =?, alamat =?, phone =? where nim =?";
            pst = con.prepareStatement(sql);
            pst.setString(1,nama);
            pst.setString(2,nim);
            pst.setString(3,jurusan);
            pst.setString(4,alamat);
            pst.setString(5,phone);
            pst.setString(6,nim);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
