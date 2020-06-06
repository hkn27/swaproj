package de.hse.swa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TCOMPANY database table.
 * 
 */
@Entity
@Table(name="TCOMPANY")
@NamedQuery(name="Tcompany.findAll", query="SELECT t FROM Tcompany t")
public class Tcompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "companySeq", table = "SEQUENCE",
	pkColumnName = "SEQ_NAME", pkColumnValue = "TCOMPANY",
	valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "companySeq")
	@Column(name="IDCOMPANY")
	private int idcompany;

	@Column(name="ADDRESS")
	private String address;

	@Column(name="DEPARTMENT")
	private String department;

	@Column(name="NAME")
	private String name;

	//bi-directional many-to-one association to Tservicecontract
	@OneToMany(mappedBy="tcompany")
	private List<Tservicecontract> tservicecontracts;

	//bi-directional many-to-one association to Tuser
	@OneToMany(mappedBy="tcompany")
	private List<Tuser> tusers;

	public Tcompany() {
	}

	public int getIdcompany() {
		return this.idcompany;
	}

	public void setIdcompany(int idcompany) {
		this.idcompany = idcompany;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tservicecontract> getTservicecontracts() {
		return this.tservicecontracts;
	}

	public void setTservicecontracts(List<Tservicecontract> tservicecontracts) {
		this.tservicecontracts = tservicecontracts;
	}

	public Tservicecontract addTservicecontract(Tservicecontract tservicecontract) {
		getTservicecontracts().add(tservicecontract);
		tservicecontract.setTcompany(this);

		return tservicecontract;
	}

	public Tservicecontract removeTservicecontract(Tservicecontract tservicecontract) {
		getTservicecontracts().remove(tservicecontract);
		tservicecontract.setTcompany(null);

		return tservicecontract;
	}

	public List<Tuser> getTusers() {
		return this.tusers;
	}

	public void setTusers(List<Tuser> tusers) {
		this.tusers = tusers;
	}

	public Tuser addTuser(Tuser tuser) {
		getTusers().add(tuser);
		tuser.setTcompany(this);

		return tuser;
	}

	public Tuser removeTuser(Tuser tuser) {
		getTusers().remove(tuser);
		tuser.setTcompany(null);

		return tuser;
	}

}