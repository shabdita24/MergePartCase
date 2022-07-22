package com.mergepc.mergeBean;

import java.util.ArrayList;

public class PartCaseBean {
	
	private ArrayList<String> enclosureUrls;
	private ArrayList<String> notingUrls;

	private ArrayList<String> eobjName;
	private ArrayList<String> esubject;
	private ArrayList<Boolean> efileStatus;
	private ArrayList<String> econtentType;

	private ArrayList<String> nobjName;
	private ArrayList<String> nsubject;
	private ArrayList<Boolean> nfileStatus;
	private ArrayList<String> ncontentType;
	
	private int notingCount;
	private int enclosureCount;
	private String objId;
	public String getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}
	public String getSubSection() {
		return subSection;
	}
	public void setSubSection(String subSection) {
		this.subSection = subSection;
	}
	public String getFileSub() {
		return fileSub;
	}
	public void setFileSub(String fileSub) {
		this.fileSub = fileSub;
	}
	public String getDfile_name() {
		return dfile_name;
	}
	public void setDfile_name(String dfile_name) {
		this.dfile_name = dfile_name;
	}
	private String fileCreatedBy;
	
	private String minioUrl;
	private String minioUsername;
	private String minioPass;

	private String blockNo;
	private String subSection;
	private String fileSub;
	private String dfile_name;
	
	public String getMinioUrl() {
		return minioUrl;
	}
	public void setMinioUrl(String minioUrl) {
		this.minioUrl = minioUrl;
	}
	public String getMinioUsername() {
		return minioUsername;
	}
	public void setMinioUsername(String minioUsername) {
		this.minioUsername = minioUsername;
	}
	public String getMinioPass() {
		return minioPass;
	}
	public void setMinioPass(String minioPass) {
		this.minioPass = minioPass;
	}
	public String getFileCreatedBy() {
		return fileCreatedBy;
	}
	public void setFileCreatedBy(String fileCreatedBy) {
		this.fileCreatedBy = fileCreatedBy;
	}
	private String displayFileName;
	private String fileName;
	private String filePath;
	private String sau;
	private String cau;

	public String getCau() {
		return cau;
	}
	public void setCau(String cau) {
		this.cau = cau;
	}

	public String getSau() {
		return sau;
	}
	public void setSau(String sau) {
		this.sau = sau;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDisplayFileName() {
		return displayFileName;
	}
	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ArrayList<String> getEnclosureUrls() {
		return enclosureUrls;
	}
	public void setEnclosureUrls(ArrayList<String> enclosureUrls) {
		this.enclosureUrls = enclosureUrls;
	}
	public ArrayList<String> getNotingUrls() {
		return notingUrls;
	}
	public void setNotingUrls(ArrayList<String> notingUrls) {
		this.notingUrls = notingUrls;
	}
	public ArrayList<String> getEobjName() {
		return eobjName;
	}
	public void setEobjName(ArrayList<String> eobjName) {
		this.eobjName = eobjName;
	}
	public ArrayList<String> getEsubject() {
		return esubject;
	}
	public void setEsubject(ArrayList<String> esubject) {
		this.esubject = esubject;
	}

	
	
	public ArrayList<Boolean> getNfileStatus() {
		return nfileStatus;
	}
	public void setNfileStatus(ArrayList<Boolean> nfileStatus) {
		this.nfileStatus = nfileStatus;
	}
	public ArrayList<Boolean> getEfileStatus() {
		return efileStatus;
	}
	public void setEfileStatus(ArrayList<Boolean> efileStatus) {
		this.efileStatus = efileStatus;
	}

	public ArrayList<String> getEcontentType() {
		return econtentType;
	}
	public void setEcontentType(ArrayList<String> econtentType) {
		this.econtentType = econtentType;
	}

	public ArrayList<String> getNobjName() {
		return nobjName;
	}
	public void setNobjName(ArrayList<String> nobjName) {
		this.nobjName = nobjName;
	}
	public ArrayList<String> getNsubject() {
		return nsubject;
	}
	public void setNsubject(ArrayList<String> nsubject) {
		this.nsubject = nsubject;
	}

	public ArrayList<String> getNcontentType() {
		return ncontentType;
	}
	public void setNcontentType(ArrayList<String> ncontentType) {
		this.ncontentType = ncontentType;
	}

	public int getNotingCount() {
		return notingCount;
	}
	public void setNotingCount(int notingCount) {
		this.notingCount = notingCount;
	}
	public int getEnclosureCount() {
		return enclosureCount;
	}
	public void setEnclosureCount(int enclosureCount) {
		this.enclosureCount = enclosureCount;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	@Override
	public String toString() {
		return "PartCaseBean [enclosureUrls=" + enclosureUrls + ", notingUrls=" + notingUrls + ", eobjName=" + eobjName
				+ ", esubject=" + esubject + ", efileStatus=" + efileStatus + ", econtentType=" + econtentType
				+ ", nobjName=" + nobjName + ", nsubject=" + nsubject + ", nfileStatus=" + nfileStatus
				+ ", ncontentType=" + ncontentType + ", notingCount=" + notingCount + ", enclosureCount="
				+ enclosureCount + ", objId=" + objId + ", fileCreatedBy=" + fileCreatedBy + ", minioUrl=" + minioUrl
				+ ", minioUsername=" + minioUsername + ", minioPass=" + minioPass + ", blockNo=" + blockNo
				+ ", subSection=" + subSection + ", fileSub=" + fileSub + ", dfile_name=" + dfile_name
				+ ", displayFileName=" + displayFileName + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", sau=" + sau + ", cau=" + cau + "]";
	}




}
