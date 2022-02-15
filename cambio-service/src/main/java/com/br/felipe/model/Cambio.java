package com.br.felipe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "cambio")
public class Cambio implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "from_currency ", nullable = false, length = 3)
	private String from;
	
	@Column(name = "to_currency ", nullable = false, length = 3)
	private String to;
	
	@Column(nullable = false)
	private BigDecimal conversionFactor;
	
	@Transient
	private BigDecimal convertedValue;
	
	@Transient
	private String enviroment;
	
	public Cambio(){
		
	}
	public Cambio(long id, String from, String to, BigDecimal convertionFactor, BigDecimal convertedValue,
			String enviroment) {
	
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = convertionFactor;
		this.convertedValue = convertedValue;
		this.enviroment = enviroment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}
	public void setConversionFactor(BigDecimal convertionFactor) {
		this.conversionFactor = convertionFactor;
	}
	public BigDecimal getConvertedValue() {
		return convertedValue;
	}
	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}
	public String getEnviroment() {
		return enviroment;
	}
	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
	@Override
	public int hashCode() {
		return Objects.hash(convertedValue, conversionFactor, enviroment, from, id, to);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cambio other = (Cambio) obj;
		return Objects.equals(convertedValue, other.convertedValue)
				&& Objects.equals(conversionFactor, other.conversionFactor)
				&& Objects.equals(enviroment, other.enviroment) && Objects.equals(from, other.from) && id == other.id
				&& Objects.equals(to, other.to);
	}
	

	
	
}
