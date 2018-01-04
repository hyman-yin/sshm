package hyman.study.ssh.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "YEARS" database table.
 * 
 */
@Entity
@Table(name="\"YEARS\"")
@NamedQuery(name="Year.findAll", query="SELECT y FROM Year y")
public class Year implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"TEMP\"")
	private BigDecimal temp;

	public Year() {
	}

	public BigDecimal getTemp() {
		return this.temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

}