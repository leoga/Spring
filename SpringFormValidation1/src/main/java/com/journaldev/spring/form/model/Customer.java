package com.journaldev.spring.form.model;
 
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.journaldev.spring.form.validator.Phone;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer's entity
 * <p>
 */
@Entity
@Table ( name= "customer")
public class Customer implements Serializable{
 
	/**
	 * Customer's id
	 * <p>
	 */
	@Id
	//@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * Customer's idemployee
	 * <p>
	 */
	@Column(name = "idemployee")
	private int idemployee;	
	
	/**
	 * Customer's name
	 * <p>
	 */
	@Column (name = "name")
    @Size(min=2, max=30) 
    private String name;
    
	/**
	 * Customer's email
	 * <p>
	 */
	@Column( name = "email")
    @NotEmpty @Email
    private String email;
    
	/**
	 * Customer's age
	 * <p>
	 */
	@Column (name = "age")
    @NotNull @Min(18) @Max(100)
    private Integer age;
    
	/**
	 * Customer's gender
	 * <p>
	 */
	@Column(name = "gender")
    @NotNull
    private Gender gender;
    
	/**
	 * Customer's birthday
	 * <p>
	 */
	@Column(name = "birthday")
    //@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull 
    @Past
    private Date birthday;
    
	/**
	 * Customer's phone
	 * <p>
	 */
	@Column( name = "phone")
    @Phone
    private String phone;
	
	/**
	 * Customer's user
	 * <p>
	 */
	@Column(name="user")
	@NotEmpty
	private String user;
	
	/**
	 * Customer's password
	 * <p>
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * Stores the customer's date of creation
	 * <p>
	 */
	private Timestamp currentdate;
	/**
	 * Constant
	 * <p>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Customer's empty constructor
	 * <p>
	 */
	public Customer()
    {
		/**
		 * Customer's empty constructor
		 * <p>
		 */
    }
	
	/**
	 * Customer's constructor
	 * <p>
	 */
    public Customer( final int	 id,
    				 final String name,
    				 final String email,
    				 final Integer age,
    				 final Gender gender,
    				 final Date birthday,
    				 final String phone,
    				 final String user,
    				 final String password)
    {
    	this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.user = user;
        this.password= password;
    }
	
	/**
	 * Customer's gender
	 * <p>
	 */
    public enum Gender {
        MALE, FEMALE
    }
    
	/**
	 * Setter for password
	 * <p>
	 */
	public void setPassword(final String password) {
		this.password = password;
		
	}
	
	/**
	 * Getter for password
	 * <p>
	 */
	public String getPassword() {
		return password;
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
    public void setId(final int id){
    	this.id=id;
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
	 * Getter for email
	 * <p>
	 */
    public String getEmail() {
        return email;
    }
    
	/**
	 * Setter for email
	 * <p>
	 */
    public void setEmail(final String email) {
        this.email = email;
    }
    
	/**
	 * Getter for age
	 * <p>
	 */
    public Integer getAge() {
        return age;
    }
    
	/**
	 * Setter for age
	 * <p>
	 */
    public void setAge(final Integer age) {
        this.age = age;
    }
    
	/**
	 * Getter for gender
	 * <p>
	 */
    public Gender getGender() {
        return gender;
    }
    
	/**
	 * Setter for gender
	 * <p>
	 */
    public void setGender(final Gender gender) {
        this.gender = gender;
    }
    
	/**
	 * Getter for birthday
	 * <p>
	 */
    public Date getBirthday() {
        return birthday;
    }
    
	/**
	 * Setter for birthday
	 * <p>
	 */
    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }
    
	/**
	 * Getter for phone
	 * <p>
	 */
    public String getPhone() {
        return phone;
    }
 
	/**
	 * Setter for phone
	 * <p>
	 */
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
	/**
	 * Getter for idemployee
	 * <p>
	 */
    public int getIdemployee() {
		return idemployee;
	}
    
	/**
	 * Setter for idemployee
	 * <p>
	 */
    public void setIdemployee(final int idemployee) {
		this.idemployee = idemployee;
	}
    
	/**
	 * Getter for currentdate
	 * <p>
	 */
    public Timestamp getCurrentdate() {
		return currentdate;
	}
    
	/**
	 * Setter for currentdate
	 * <p>
	 */
    public void setCurrentdate(final Timestamp currentdate) {
		this.currentdate = currentdate;
	}
}