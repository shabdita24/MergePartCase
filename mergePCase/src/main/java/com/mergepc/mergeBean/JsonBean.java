package com.mergepc.mergeBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JsonBean {

	private String id;
	
	private String pcFileNumber;

	private String nofFileName;

	private String objId;

	private String fromRole;

	private String toRole;
	
	private byte[] file;

//	private ArrayList<NotingList> notingList;
	private String notingList;
//	private ArrayList<EnclosureList> enclosureList;
	private String enclosureList;

//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private String createdOn;

	private String hrmRole;

	private boolean enableAddNoting;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPcFileNumber() {
		return pcFileNumber;
	}

	public void setPcFileNumber(String pcFileNumber) {
		this.pcFileNumber = pcFileNumber;
	}

	public String getNofFileName() {
		return nofFileName;
	}

	public void setNofFileName(String nofFileName) {
		this.nofFileName = nofFileName;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getFromRole() {
		return fromRole;
	}

	public void setFromRole(String fromRole) {
		this.fromRole = fromRole;
	}

	public String getToRole() {
		return toRole;
	}

	public void setToRole(String toRole) {
		this.toRole = toRole;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getNotingList() {
		return notingList;
	}

	public void setNotingList(String notingList) {
		this.notingList = notingList;
	}

	public String getEnclosureList() {
		return enclosureList;
	}

	public void setEnclosureList(String enclosureList) {
		this.enclosureList = enclosureList;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getHrmRole() {
		return hrmRole;
	}

	public void setHrmRole(String hrmRole) {
		this.hrmRole = hrmRole;
	}

	public boolean isEnableAddNoting() {
		return enableAddNoting;
	}

	public void setEnableAddNoting(boolean enableAddNoting) {
		this.enableAddNoting = enableAddNoting;
	}

	@Override
	public String toString() {
		return "PartCaseInventory [id=" + id + ", pcFileNumber=" + pcFileNumber + ", nofFileName=" + nofFileName
				+ ", objId=" + objId + ", fromRole=" + fromRole + ", toRole=" + toRole + ", file="
				+ Arrays.toString(file) + ", notingList=" + notingList + ", enclosureList=" + enclosureList
				+ ", createdOn=" + createdOn + ", hrmRole=" + hrmRole + ", enableAddNoting=" + enableAddNoting + "]";
	}
	
	

}
