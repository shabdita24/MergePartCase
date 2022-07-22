package com.mergepc.service;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.mergepc.mergeBean.InputPBean;

public class MergeClass {
	Logger log=Logger.getLogger(MergeClass.class);



	public void partCopy(IDfSession session, InputPBean inputPartBean, IDfFolder destinationDirectory)  {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			log.warn("Exception in thread "+ie.toString());
		}
		log.warn("Thread Name in start " + Thread.currentThread().getName());
		log.info("Creating doc");
		try {
			IDfDocument doc = (IDfDocument) session.newObject(inputPartBean.getBy());
			doc.setObjectName(inputPartBean.getObjectname());
			if(inputPartBean.getContenttype().equals("docx"))
				doc.setContentType("msw12");
			else if(inputPartBean.getContenttype().equals("xlsx") )
				doc.setContentType("excel12book");
			else if(inputPartBean.getContenttype().equals("txt") )
				doc.setContentType("crtext");
			else if(inputPartBean.getContenttype().equals("xls") )
				doc.setContentType("excel8book");
			else if(inputPartBean.getContenttype().equals("ppt") )
				doc.setContentType("ppt8");
			else if(inputPartBean.getContenttype().equals("jpg") )
				doc.setContentType("jpg");
			else if(inputPartBean.getContenttype().equals("htm") )
				doc.setContentType("html");
			else if(inputPartBean.getContenttype().equals("pptx") )
				doc.setContentType("ppt12_slide");		
			else
				doc.setContentType(inputPartBean.getContenttype());
			doc.setString("file_created_by", inputPartBean.getBy_name());
			doc.setSubject(inputPartBean.getSubject());
			doc.setString("mf_flag_number", inputPartBean.getMf_flag_number());
			doc.setString("subject_of_dak", inputPartBean.getSubject());
			doc.setString("block_no", inputPartBean.getBlockNo());
			doc.setString("section", inputPartBean.getSubSection());
			doc.setString("file_or_dak_type", "FILE");
			doc.setString("display_file_name", inputPartBean.getDisplay_file_name());
			doc.setString("flag_number", inputPartBean.getMf_flag_number());
			doc.setString("file_subject", inputPartBean.getFileSub());
			doc.setString("file_status", inputPartBean.getFile_status());
			doc.setString("file_name", inputPartBean.getFile_name());
			doc.setString("index_from", inputPartBean.getSau());
			doc.setString("from_dir", inputPartBean.getSau());
			doc.setString("dak_no", inputPartBean.getFile_name());
			doc.setString("scanned_range", inputPartBean.getMf_flag_number());
			doc.setString("creator_role",  inputPartBean.getBy_name());
			doc.setString("creation_dir", inputPartBean.getSau());
			doc.setString("acl_name",inputPartBean.getSau().toLowerCase());
			doc.setString("directorate", inputPartBean.getSau());		
			doc.setString("cau", inputPartBean.getCau());
			doc.setString("dak_type", "Enclosure");		
			doc.setString("doc_type", "Enclosure");	
			doc.setInt("fno", inputPartBean.getFno());
			log.warn("Reading File");
			ByteArrayOutputStream st = new ByteArrayOutputStream();
			try {
				byte[] buffer = new byte[10240];
				int len;
				while ((len = inputPartBean.getInputstream().read(buffer, 0, buffer.length)) != -1) {
					st.write(buffer, 0, len);
				}
			} catch (Exception e) {
				log.warn("File Reading Error" + e);
			}
			doc.setContent(st);
			doc.link(destinationDirectory.getFolderPath(0));
			doc.save();
			log.info("Created doc at destination path");
			st.close();
			st.flush();
		}catch(DfException d){
			log.warn("Exception e in dfc "+d.toString());

		}
		catch(Exception d){
			log.warn("Exception e "+d.toString());

		}

	}


}
