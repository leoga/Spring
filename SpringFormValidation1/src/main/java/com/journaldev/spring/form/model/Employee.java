package com.journaldev.spring.form.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Employee's entity
 * <p>
 */
@Entity
@Table( name = "employee")
public class Employee implements Serializable{
	
	
	/**
	 * Employee's id
	 * <p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * Employee's name
	 * <p>
	 */
	@Column(name = "name")
	@NotEmpty
    private String name;
	
	/**
	 * Employee's role
	 * <p>
	 */
	@Column(name = "role")
	@NotEmpty
    private String role;
	
	/**
	 * Employee's user
	 * <p>
	 */
	@Column(name = "user")
	@NotEmpty
	private String user;
	
	/**
	 * Employee's password
	 * <p>
	 */
	@Column(name = "password")
	private String password;
	/**
	 * Constant
	 * <p>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Employee's empty constructor
	 * <p>
	 */
	public Employee()
	{	
		/**
		 * Employee's empty constructor
		 * <p>
		 */
	}
	
	/**
	 * Employee's constructor
	 * <p>
	 */
	public Employee(final int id,final String name,final String role,final String user,final String password){
		this.id=id;
		this.name=name;
		this.role=role;
		this.user=user;
		this.password=password;
	}
	
	/**
	 * Getter for id
	 * <p>
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Setter for id
	 * <p>
	 */
    public void setId(final int id) {
        this.id = id;
    }
    
	/**
	 * Getter for name
	 * <p>
	 */
    public String getName() {
        return name;
    }
    
	/**
	 * Setter for name
	 * <p>
	 */
    public void setName(final String name) {
        this.name = name;
    }
    
	/**
	 * Getter for role
	 * <p>
	 */
    public String getRole() {
        return role;
    }
    
	/**
	 * Setter for role
	 * <p>
	 */
    public void setRole(final String role) {
        this.role = role;
    }
    
	/**
	 * Getter for user
	 * <p>
	 */
    public String getUser() {
		return user;
	}
    
	/**
	 * Setter for user
	 * <p>
	 */
    public void setUser(final String user) {
		this.user = user;
	}
    
	/**
	 * Getter for password
	 * <p>
	 */
    public String getPassword() {
		return password;
	}
    
	/**
	 * Setter for password
	 * <p>
	 */
    public void setPassword(final String password) {
		this.password = password;
	}
     
}