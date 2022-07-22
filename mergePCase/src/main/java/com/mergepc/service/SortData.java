package com.mergepc.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mergepc.mergeBean.JsonBean;
import com.mergepc.mergeBean.PartCaseBean;

@Service
public class SortData {
	Logger log=Logger.getLogger(SortData.class);

	public PartCaseBean getdataInPartCaseBean(JsonBean jb) {

		PartCaseBean pb=new PartCaseBean();
		//		pb.setEnclosureCount(jb.getEnclosureList().size());
		//		pb.setNotingCount(jb.getNotingList().size());
		pb.setObjId(jb.getObjId());
		pb.setFileCreatedBy(jb.getFromRole());		

		ArrayList<String> Eurls=new ArrayList<String>();
		ArrayList<String> Nurls=new ArrayList<String>();
		ArrayList<String> Esub=new ArrayList<String>();
		ArrayList<String> Nsub=new ArrayList<String>();
		ArrayList<String> Eext=new ArrayList<String>();
		ArrayList<String> Next=new ArrayList<String>();
		ArrayList<String> Eobj=new ArrayList<String>();
		ArrayList<String> Nobj=new ArrayList<String>();
		ArrayList<Boolean> Esig=new ArrayList<Boolean>();
		ArrayList<Boolean> Nsig=new ArrayList<Boolean>();
		if(!jb.getNotingList().isEmpty()) {
			String[] notingstr=jb.getNotingList().split(",");
			log.info("length of notinglength "+notingstr);
			for(int i=0;i<notingstr.length;i++ ) {
				log.info("notingstr["+i+"]"+notingstr[i]);
				if(notingstr[i].contains("fileName")) {
					String filename=notingstr[i].substring(notingstr[i].indexOf("=")+1, notingstr[i].length());
					log.info("filename "+filename);
					Nobj.add(filename);      			
					StringBuffer sb=new StringBuffer(filename);
					StringBuffer rev=sb.reverse();
					String sunStr=rev.substring(0, rev.indexOf("."));
					String sunStr2=rev.substring(rev.indexOf(".")+1,rev.length());
					StringBuffer sb1=new StringBuffer(sunStr);
					StringBuffer sb2=new StringBuffer(sunStr2);
					String ext=sb1.reverse().toString();
					String file_sub=sb2.reverse().toString();
					Nsub.add(file_sub);
					Next.add(ext);
				}
				if(notingstr[i].contains("isSigned")) {
					String signed=notingstr[i].substring(notingstr[i].indexOf("=")+1, notingstr[i].length());
					log.info("signed "+signed);
					Nsig.add(Boolean.parseBoolean(signed));
				}
			}
		}

		if(!jb.getEnclosureList().isEmpty()) {
			String[] enclosurestr=jb.getEnclosureList().split(",");
			log.info("length of enclosurestr "+enclosurestr);
			for(int i=0;i<enclosurestr.length;i++ ) {
				log.info("notingstr["+i+"]"+enclosurestr[i]);
				if(enclosurestr[i].contains("fileName")) {
					String filename=enclosurestr[i].substring(enclosurestr[i].indexOf("=")+1, enclosurestr[i].length());
					log.info("filename "+filename);
					Eobj.add(filename);      			
					StringBuffer sb=new StringBuffer(filename);
					StringBuffer rev=sb.reverse();
					String sunStr=rev.substring(0, rev.indexOf("."));
					String sunStr2=rev.substring(rev.indexOf(".")+1,rev.length());
					StringBuffer sb1=new StringBuffer(sunStr);
					StringBuffer sb2=new StringBuffer(sunStr2);
					String ext=sb1.reverse().toString();
					String file_sub=sb2.reverse().toString();
					Esub.add(file_sub);
					Eext.add(ext);
				}
				if(enclosurestr[i].contains("isSigned")) {
					String signed=enclosurestr[i].substring(enclosurestr[i].indexOf("=")+1, enclosurestr[i].length());
					log.info("signed "+signed);
					Esig.add(Boolean.parseBoolean(signed));
				}
			}
		}


		//		if(jb.getEnclosureList().size()>0) {
		//			for(int i=0;i<jb.getEnclosureList().size();i++) {
		//		
		//				EnclosureList el=jb.getEnclosureList().get(i);
		//				String filename=el.getFileName();
		//				String url=el.getFileUrl();
		//				Boolean isSigned=el.getisSigned();
		//				StringBuffer sb=new StringBuffer(filename);
		//				StringBuffer rev=sb.reverse();
		//				String sunStr=rev.substring(0, rev.indexOf("."));
		//				String sunStr2=rev.substring(rev.indexOf(".")+1,rev.length());
		//				StringBuffer sb1=new StringBuffer(sunStr);
		//				StringBuffer sb2=new StringBuffer(sunStr2);
		//				String ext=sb1.reverse().toString();
		//				String file_sub=sb2.reverse().toString();
		//				Eurls.add(url);
		//				Esig.add(isSigned);
		//				Esub.add(file_sub);
		//				Eext.add(ext);
		//				Eobj.add(filename);
		//
		//
		//			}
		//		}
		//		if(jb.getNotingList().size()>0) {
		//			for(int j=0;j<jb.getNotingList().size();j++) {
		//				NotingList nl=jb.getNotingList().get(j);
		//				String filename=nl.getFileName();
		//				String url=nl.getFileUrl();
		//				Boolean isSigned=nl.getisSigned();
		//				StringBuffer sb=new StringBuffer(filename);
		//				StringBuffer rev=sb.reverse();
		//				String sunStr=rev.substring(0, rev.indexOf("."));
		//				String sunStr2=rev.substring(rev.indexOf(".")+1,rev.length());
		//				StringBuffer sb1=new StringBuffer(sunStr);
		//				StringBuffer sb2=new StringBuffer(sunStr2);
		//				String ext=sb1.reverse().toString();
		//				String file_sub=sb2.reverse().toString();
		//				Nurls.add(url);
		//				Nsig.add(isSigned);
		//				Nsub.add(file_sub);
		//				Next.add(ext);
		//				Nobj.add(filename);
		//			}
		//		}
		//		pb.setNotingUrls(Nurls);
		pb.setNfileStatus(Nsig);
		pb.setNsubject(Nsub);
		pb.setNobjName(Nobj);
		pb.setNcontentType(Next);
		pb.setNotingCount(pb.getNobjName().size());
		//		pb.setEnclosureUrls(Eurls);
		pb.setEfileStatus(Esig);	
		pb.setEobjName(Eobj);
		pb.setEcontentType(Eext);
		pb.setEsubject(Esub);
		pb.setEnclosureCount(pb.getEobjName().size());
		return pb;
	}

}
