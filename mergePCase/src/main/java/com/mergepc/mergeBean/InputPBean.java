package com.mergepc.mergeBean;

import java.io.ByteArrayInputStream;

public class InputPBean {


	private String objectname;
	private int fno;
	private String mf_flag_number;
	private String r_object_type ;
	private String by_name ;

	private String DestID;
	private String subject;
	private String by;
	private ByteArrayInputStream inputstream;
	private String contenttype;
	private String file_name;
	private String file_status;
	private String display_file_name;
	private String sau;
	private String cau;
	private String blockNo;
	private String subSection;
	private String fileSub;
	private String dfile_name;
	
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
	public String getBy_name() {
		return by_name;
	}
	public void setBy_name(String by_name) {
		this.by_name = by_name;
	}

	public String getObjectname() {
		return objectname;
	}
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getMf_flag_number() {
		return mf_flag_number;
	}
	public void setMf_flag_number(String mf_flag_number) {
		this.mf_flag_number = mf_flag_number;
	}
	public String getR_object_type() {
		return r_object_type;
	}
	public void setR_object_type(String r_object_type) {
		this.r_object_type = r_object_type;
	}
	public String getDestID() {
		return DestID;
	}
	public void setDestID(String destID) {
		DestID = destID;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public ByteArrayInputStream getInputstream() {
		return inputstream;
	}
	public void setInputstream(ByteArrayInputStream inputstream) {
		this.inputstream = inputstream;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_status() {
		return file_status;
	}
	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}
	public String getDisplay_file_name() {
		return display_file_name;
	}
	public void setDisplay_file_name(String display_file_name) {
		this.display_file_name = display_file_name;
	}
	@Override
	public String toString() {
		return "InputPBean [objectname=" + objectname + ", fno=" + fno + ", mf_flag_number=" + mf_flag_number
				+ ", r_object_type=" + r_object_type + ", by_name=" + by_name + ", DestID=" + DestID + ", subject="
				+ subject + ", by=" + by + ", inputstream=" + inputstream + ", contenttype=" + contenttype
				+ ", file_name=" + file_name + ", file_status=" + file_status + ", display_file_name="
				+ display_file_name + ", sau=" + sau + ", cau=" + cau + ", blockNo=" + blockNo + ", subSection="
				+ subSection + ", fileSub=" + fileSub + ", dfile_name=" + dfile_name + "]";
	}


}
