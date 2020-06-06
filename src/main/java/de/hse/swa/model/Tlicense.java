package de.hse.swa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TLICENSE database table.
 * 
 */
@Entity
@Table(name="TLICENSE")
@NamedQuery(name="Tlicense.findAll", query="SELECT t FROM Tlicense t")
public class Tlicense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "licenseSeq", table = "SEQUENCE",
	pkColumnName = "SEQ_NAME", pkColumnValue = "TLICENSE",
	valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator = "licenseSeq")
	@Column(name="IDLICENSE")
	private int idlicense;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPIRATIONDATE")
	private Date expirationdate;

	@Column(name="IP1")
	private String ip1;

	@Column(name="IP2")
	private String ip2;

	@Column(name="IP3")
	private String ip3;

	@Column(name="IP4")
	private String ip4;

	@Column(name="LICENSECOUNT")
	private int licensecount;

	@Column(name="LICENSEKEY")
	private String licensekey;

	//bi-directional many-to-one association to Tservicecontract
	@ManyToOne
	@JoinColumn(name="TSCID")
	private Tservicecontract tservicecontract;

	public Tlicense() {
	}

	public int getIdlicense() {
		return this.idlicense;
	}

	public void setIdlicense(int idlicense) {
		this.idlicense = idlicense;
	}

	public Date getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getIp1() {
		return this.ip1;
	}

	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}

	public String getIp2() {
		return this.ip2;
	}

	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}

	public String getIp3() {
		return this.ip3;
	}

	public void setIp3(String ip3) {
		this.ip3 = ip3;
	}

	public String getIp4() {
		return this.ip4;
	}

	public void setIp4(String ip4) {
		this.ip4 = ip4;
	}

	public int getLicensecount() {
		return this.licensecount;
	}

	public void setLicensecount(int licensecount) {
		this.licensecount = licensecount;
	}

	public String getLicensekey() {
		return this.licensekey;
	}

	public void setLicensekey(String licensekey) {
		this.licensekey = licensekey;
	}

	public Tservicecontract getTservicecontract() {
		return this.tservicecontract;
	}

	public void setTservicecontract(Tservicecontract tservicecontract) {
		this.tservicecontract = tservicecontract;
	}

}