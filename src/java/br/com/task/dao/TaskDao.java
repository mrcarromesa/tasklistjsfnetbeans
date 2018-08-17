/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.task.dao;

import br.com.task.db.ConexaoMysql;
import br.com.task.entity.Task;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosrodolfosantos
 */
public class TaskDao {
    
    public int salvar(Task task) {
        Connection conexao = ConexaoMysql.getConexao();
        try {
            String sql = "INSERT INTO tasks SET titulo = ?, status = ?";
            PreparedStatement ps = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTitulo());
            ps.setBoolean(2, task.isConcluido());
            ps.executeUpdate();
            
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
              //Obter valor da chave auto increment inserido
              int rid = rs.getInt(1);
              return rid;
            }
            
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public List<Task> lista() {
        Connection conexao = ConexaoMysql.getConexao();
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM tasks");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitulo(rs.getString("titulo"));
                task.setConcluido(rs.getBoolean("status"));
                
                tasks.add(task);
                //rs.next();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tasks;
    }
    
   public void alterar(Task task, int id){
       Connection conexao = ConexaoMysql.getConexao();
        try {
            PreparedStatement ps = conexao.prepareCall("UPDATE tasks SET titulo = ?, status = ? WHERE id = ?");
            ps.setString(1, task.getTitulo());
            ps.setBoolean(2, task.isConcluido());
            ps.setInt(3, id);
            ps.execute();
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void deletar(int id){
       Connection conexao = ConexaoMysql.getConexao();
        try {
            PreparedStatement ps = conexao.prepareCall("DELETE FROM tasks WHERE id= ?");
            ps.setInt(1, id);
            ps.execute();
            ConexaoMysql.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
