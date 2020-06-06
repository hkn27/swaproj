package de.hse.swa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TSERVICECONTRACT database table.
 * 
 */
@Entity
@Table(name="TSERVICECONTRACT")
@NamedQuery(name="Tservicecontract.findAll", query="SELECT t FROM Tservicecontract t")
public class Tservicecontract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "servicecontractSeq", table = "SEQUENCE",
	pkColumnName = "SEQ_NAME", pkColumnValue = "TSERVICECONTRACT",
	valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="servicecontractSeq")
	@Column(name="IDSERVICECONTRACT")
	private int idservicecontract;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENDDATE")
	private Date enddate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="STARTDATE")
	private Date startdate;

	//bi-directional many-to-one association to Tlicense
	@OneToMany(mappedBy="tservicecontract")
	private List<Tlicense> tlicenses;

	//bi-directional many-to-one association to Tcompany
	@ManyToOne
	@JoinColumn(name="COMPANYID")
	private Tcompany tcompany;

	//bi-directional many-to-one association to Tuser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Tuser tuser;

	public Tservicecontract() {
	}

	public int getIdservicecontract() {
		return this.idservicecontract;
	}

	public void setIdservicecontract(int idservicecontract) {
		this.idservicecontract = idservicecontract;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public List<Tlicense> getTlicenses() {
		return this.tlicenses;
	}

	public void setTlicenses(List<Tlicense> tlicenses) {
		this.tlicenses = tlicenses;
	}

	public Tlicense addTlicens(Tlicense tlicens) {
		getTlicenses().add(tlicens);
		tlicens.setTservicecontract(this);

		return tlicens;
	}

	public Tlicense removeTlicens(Tlicense tlicens) {
		getTlicenses().remove(tlicens);
		tlicens.setTservicecontract(null);

		return tlicens;
	}

	public Tcompany getTcompany() {
		return this.tcompany;
	}

	public void setTcompany(Tcompany tcompany) {
		this.tcompany = tcompany;
	}

	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}