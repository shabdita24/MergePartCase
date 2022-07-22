package com.mergepc.mergeBean;


public class EnclosureList {
//	"fileName": "PersonalTemplate.docx",
//    "fileUrl": "http://11.0.0.118:9000/7wgaco/Files/PC-TEST UNCLAS  FILE BU AKAKAKAKAKAKAKAK 2 BMRL-1/Noting/PersonalTemplate.docx",
//    "flagNumber": 6,
//    "signedOn": {
//      "$date": "2022-01-05T06:09:44.177Z"
//    },
//    "isSigned": true

    
	private String fileName;
	private String fileUrl;
	private int flagNumber;
	private String signedOn;
	private boolean isSigned;
	private String prevVersionId;
	private String annotationId="";
private String comment;
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getFlagNumber() {
		return flagNumber;
	}
	public void setFlagNumber(int flagNumber) {
		this.flagNumber = flagNumber;
	}
	public String getSignedOn() {
		return signedOn;
	}

	public void setSignedOn(String signedOn) {
		this.signedOn = signedOn;
	}
	public boolean getisSigned() {
		return isSigned;
	}
	public void setisSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}
	public String getAnnotationId() {
		return annotationId;
	}
	public void setAnnotationId(String annotationId) {
		this.annotationId = annotationId;
	}
	
	
	public boolean isSigned() {
		return isSigned;
	}
	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}
	
	public String getPrevVersionId() {
		return prevVersionId;
	}
	public void setPrevVersionId(String prevVersionId) {
		this.prevVersionId = prevVersionId;
	}
	@Override
	public String toString() {
		return "EnclosureList [fileName=" + fileName + ", fileUrl=" + fileUrl + ", flagNumber=" + flagNumber
				+ ", signedOn=" + signedOn + ", isSigned=" + isSigned + ", annotationId=" + annotationId + "]";
	}
	
	
	

}
