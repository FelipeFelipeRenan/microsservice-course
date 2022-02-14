package com.br.felipe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Cambio implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private long id;
	private String from;
	private String to;
	private BigDecimal convertionFactor;
	private BigDecimal convertedValue;
	private String enviroment;
	
	public Cambio(){
		
	}
	public Cambio(long id, String from, String to, BigDecimal convertionFactor, BigDecimal convertedValue,
			String enviroment) {
	
		this.id = id;
		this.from = from;
		this.to = to;
		this.convertionFactor = convertionFactor;
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
	public BigDecimal getConvertionFactor() {
		return convertionFactor;
	}
	public void setConvertionFactor(BigDecimal convertionFactor) {
		this.convertionFactor = convertionFactor;
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
		return Objects.hash(convertedValue, convertionFactor, enviroment, from, id, to);
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
				&& Objects.equals(convertionFactor, other.convertionFactor)
				&& Objects.equals(enviroment, other.enviroment) && Objects.equals(from, other.from) && id == other.id
				&& Objects.equals(to, other.to);
	}
	

	
	
}
