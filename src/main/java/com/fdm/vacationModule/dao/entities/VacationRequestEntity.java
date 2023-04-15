package com.fdm.vacationModule.dao.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vacationRequest")
public class VacationRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	@Column(name = "start_date")
	private String startDate ;
	@Column(name = "end_date")
	private String endDate;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;
	
	public VacationRequestEntity() {
		super();
	}
	public VacationRequestEntity(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public VacationRequestEntity(String startDate, String endDate, UserAccountEntity userAccountEntity) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.userAccountEntity = userAccountEntity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public UserAccountEntity getUserAccountEntity() {
		return userAccountEntity;
	}
	public void setUserAccountEntity(UserAccountEntity userAccountEntity) {
		this.userAccountEntity = userAccountEntity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(endDate, id, startDate, userAccountEntity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VacationRequestEntity other = (VacationRequestEntity) obj;
		return Objects.equals(endDate, other.endDate) && id == other.id && Objects.equals(startDate, other.startDate)
				&& Objects.equals(userAccountEntity, other.userAccountEntity);
	}
	@Override
	public String toString() {
		return "VacationRequestEntity [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", userAccountEntity=" + userAccountEntity + "]";
	}

	
}
