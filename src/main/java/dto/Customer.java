package dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullname;
//	@Column(unique=true)
	private String email;
//	@Column (unique=true)
	private long mobile;
	private String password;
	private String gender;
	private LocalDate dob;
	@Lob
//	@Column(columnDefinition = "LONGBLOB")
	private byte[] picture;
//	private int age;
	private String country;	
	
}
