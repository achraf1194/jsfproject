
package tn.esprit.PiPub.persistence;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name = "\"user\"")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "Email")
	private String email;

	@Column(name = "PasswordHash")
	private String passwordHash;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "Role")
	private String role;

    @OneToMany(targetEntity=LikeAdv.class,mappedBy="user",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<LikeAdv> user;
	
	

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	


}