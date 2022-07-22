package com.mergepc.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.mergepc.mergeBean.PartCaseBean;

@Service
public class DctmTasks {
	Logger log=Logger.getLogger(DctmTasks.class);

	public PartCaseBean getDestPath(IDfSession session, PartCaseBean pb) {
		IDfQuery dfQuery = new DfQuery();
		IDfCollection coll=null;
		log.warn("****DctmTasks****");
		try {
			dfQuery.setDQL("select d_file_name,subject,file_path,object_name,pkl_directorate,display_file_name,block_number,cau,pkl_section from iaf_file_folder where r_object_id='"+pb.getObjId()+"'");
			log.info(dfQuery.getDQL());
			coll=dfQuery.execute(session, 1);
			if(coll.next()) {
				String path=coll.getString("file_path");
				String name=coll.getString("object_name");
				String dname=coll.getString("display_file_name");
				String sau=coll.getString("pkl_directorate");
				String block_no=coll.getString("block_number");
				String cau=coll.getString("cau");
				String pklsection=coll.getString("pkl_section");
				String path1=path+"/"+name+"/"+sau+"-"+name+"/Collation/Enclosure";
				pb.setFilePath(path1);
				pb.setFileName(name);
				pb.setDisplayFileName(dname);
				pb.setSau(sau);
				pb.setCau(cau);
				pb.setBlockNo(block_no);
				pb.setSubSection(pklsection);
				pb.setFileSub(coll.getString("subject"));
				pb.setDfile_name(coll.getString("d_file_name"));
				log.info("destination path "+path1);
				return pb;
			}
			else {
				log.warn("Coll is null ");
			}
		}
		catch(Exception e) {
			log.warn("Exception e "+e.toString());
			return pb;
		}
		finally {
			//if(coll!=null)
				try {
					coll.close();
				} catch (DfException e) {
					log.info("Error is closing collection "+e.toString());
					e.printStackTrace();
				}
		}
		return pb;

	}

}
