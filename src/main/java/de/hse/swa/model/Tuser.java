package de.hse.swa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TUSER database table.
 * 
 */
@Entity
@Table(name="TUSER")
@NamedQuery(name="Tuser.findAll", query="SELECT t FROM Tuser t")
public class Tuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "userSeq", table = "SEQUENCE",
	pkColumnName = "SEQ_NAME", pkColumnValue = "TUSER",
	valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="userSeq")
	@Column(name="IDUSER")
	private int iduser;

	@Column(name="ADMIN")
	private byte admin;

	@Column(name="EMAIL")
	private String email;

	@Column(name="FIRSTNAME")
	private String firstname;

	@Column(name="LASTNAME")
	private String lastname;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="USERNAME")
	private String username;

	//bi-directional many-to-one association to Tservicecontract
	@OneToMany(mappedBy="tuser")
	private List<Tservicecontract> tservicecontracts;

	//bi-directional many-to-one association to Tcompany
	@ManyToOne
	@JoinColumn(name="COMPANYID")
	private Tcompany tcompany;

	public Tuser() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public byte getAdmin() {
		return this.admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Tservicecontract> getTservicecontracts() {
		return this.tservicecontracts;
	}

	public void setTservicecontracts(List<Tservicecontract> tservicecontracts) {
		this.tservicecontracts = tservicecontracts;
	}

	public Tservicecontract addTservicecontract(Tservicecontract tservicecontract) {
		getTservicecontracts().add(tservicecontract);
		tservicecontract.setTuser(this);

		return tservicecontract;
	}

	public Tservicecontract removeTservicecontract(Tservicecontract tservicecontract) {
		getTservicecontracts().remove(tservicecontract);
		tservicecontract.setTuser(null);

		return tservicecontract;
	}

	public Tcompany getTcompany() {
		return this.tcompany;
	}

	public void setTcompany(Tcompany tcompany) {
		this.tcompany = tcompany;
	}

}