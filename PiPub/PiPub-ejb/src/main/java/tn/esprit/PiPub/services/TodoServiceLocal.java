package tn.esprit.PiPub.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PiPub.persistence.Todo;

@Local
public interface TodoServiceLocal {
	
	void create(Todo todo);
	List<Todo> findAll();

}
