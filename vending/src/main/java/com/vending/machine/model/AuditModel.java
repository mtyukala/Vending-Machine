package com.vending.machine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createOn", "updatedOn" }, allowGetters = true)
/**
 * Abstract class that will help children classes with annotations that will
 * automatically populate the <code>createOn</code> and <code>updateOn
 * </code> fields when the children are persisted.
 * 
 * @author Mkhululi Tyukala
 *
 */
public abstract class AuditModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "create_on", nullable = false, updatable = false)
	private Date createOn;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_on", nullable = false)
	@LastModifiedDate
	private Date updatedOn;

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
