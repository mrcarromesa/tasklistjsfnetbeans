/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.task.bean;

import br.com.task.dao.TaskDao;
import br.com.task.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlosrodolfosantos
 */
@ManagedBean
@SessionScoped
public class TaskBean {
    
    private Task task = new Task();
    private Task taskUpdate = new Task();
    private List<Task> tasks = new ArrayList<>();
    
    private final TaskDao taskDao = new TaskDao();
    
    public TaskBean() {
        this.listar();
    }
                
    public final void listar(){
        this.tasks = taskDao.lista();
    }
    
    public void add(){
        
        //Update
        if (tasks.indexOf(taskUpdate) > -1) {
            taskDao.alterar(task, this.taskUpdate.getId());
            tasks.set(tasks.indexOf(taskUpdate), task);
        } else {
            //Create or add.
            int id = taskDao.salvar(task);
            task.setId(id);
            tasks.add(task);
            
        }
        
        taskUpdate = new Task();
        task = new Task();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void delete(Task task) {
        taskDao.deletar(task.getId());
        this.tasks.remove(task);
    }
    
    public void edit(Task task){
        this.taskUpdate = task;
        this.task = task;
    }
}
