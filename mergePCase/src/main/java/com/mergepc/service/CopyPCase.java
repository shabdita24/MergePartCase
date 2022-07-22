package com.mergepc.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.mergepc.mergeBean.InputPBean;
import com.mergepc.mergeBean.PartCaseBean;

@Service
public class CopyPCase {
	Logger log = Logger.getLogger(CopyPCase.class);

	public String copyEnclosureToDepFileBm(IDfSession session, PartCaseBean partbean, String destFile, InputPBean inputBean,byte[] bs,String nofilename)
			throws DfException {

		IDfCollection colse=null;
		IDfCollection maxf=null;
		log.info("Copy PartCase Enclosure to Department File BM");
		List<InputPBean> beann = new ArrayList<>();
		String trim = destFile;
		String ss = trim.substring(0, trim.lastIndexOf("/"));
		log.info("After trimming destFile" + ss);
		String que = "select count(*) as total from iaf_file_content where folder('" + ss + "',descend) ";
		colse = getColl(session, que);
		int starttotal = 0;
		if (colse.next()) {
			starttotal = colse.getInt("total");
			log.warn("Total files count in destpath is" + starttotal);
		}
		String maxfnoQuery = "select fno from iaf_file_content  where folder('" + ss
				+ "',descend) order by fno desc enable (RETURN_TOP 1)";
		maxf = getColl(session, maxfnoQuery);
		int mfno = 0;
		if (maxf.next()) {
			mfno = maxf.getInt("fno");
			log.warn("Max fno in destpath is" + mfno);
		}

		int mfno1 = mfno + 1;
		log.warn("MF NO " + mfno1);
		IDfFolder destinationDirectory = session.getFolderByPath(destFile);
		int totalCount =partbean.getEnclosureCount()+partbean.getNotingCount();
		log.warn("total file count from src--" + totalCount);
		int totalCountN = partbean.getNotingCount();
		log.warn("total Noting count--" + totalCountN);
		try {
			ByteArrayInputStream s=new ByteArrayInputStream(bs);	
			log.info("outputStream "+s);
			ZipInputStream zis = new ZipInputStream(s);
			log.info("ZipInputStream "+zis);

			String alogic = null;int j=0;
			for (int i = totalCount-1; i>=totalCountN; i--) {
				InputPBean inputPartBean = new InputPBean();
				log.warn("in for loop " + i);
				alogic = ((mfno1) + str(i));
				log.warn("mf flag number will be" + alogic);		
				if (partbean.getEobjName().get(j).isEmpty() || partbean.getEobjName().get(j)==null) {
					log.warn("Enclosure folder empty");
					break;
				}else {
					String object =partbean.getEobjName().get(j);
					log.warn("ObjectName" + object);
//					String subj = partbean.getEsubject().get(j);
					String subj = nofilename;

					String dfile = partbean.getDisplayFileName();
					String fstat="";
					if(partbean.getEfileStatus().get(j))
						fstat = "S";
					else
						fstat = "D";
					String fnme = partbean.getFileName();
					String contenttype = partbean.getEcontentType().get(j);
					String byname = partbean.getFileCreatedBy();
					String sau=partbean.getSau();
					String cau=partbean.getCau();
					log.warn("content type" + contenttype);
					//					MinioTasks mm=new MinioTasks();
					//					ByteArrayOutputStream bao =  mm.copyfile(partbean.getEnclosureUrls().get(j),partbean.getMinioUrl(),partbean.getMinioUsername(),partbean.getMinioPass());
					ByteArrayOutputStream bao=getByteOfFile(zis,object,"enclosure");					
				//	log.info("ByteArrayOutputStream "+bao);
					byte[] data = bao.toByteArray();
					ByteArrayInputStream stream = new ByteArrayInputStream(data);
					
					String bNo=partbean.getBlockNo();
					inputPartBean.setSubSection(partbean.getSubSection());
					inputPartBean.setFileSub(partbean.getFileSub());
					inputPartBean.setDfile_name(partbean.getDfile_name());

					inputPartBean.setBlockNo(bNo);
					inputPartBean.setFno(mfno1);
					inputPartBean.setMf_flag_number(alogic);
					inputPartBean.setFile_name(fnme);
					inputPartBean.setFile_status(fstat);
					inputPartBean.setDisplay_file_name(dfile);
					inputPartBean.setObjectname(object);
					inputPartBean.setSubject(subj);
					inputPartBean.setContenttype(contenttype);
					inputPartBean.setInputstream(stream);
					inputPartBean.setBy_name(byname);
					inputPartBean.setSau(sau);
					inputPartBean.setCau(cau);
					inputPartBean.setBy("iaf_dak_content");
					bao.close();
					stream.close();
				}
				beann.add(inputPartBean);
				j++;
				MergeClass c=new MergeClass();
				c.partCopy(session, inputPartBean, destinationDirectory);
			}
			log.info("merged Enclosures ");
			s.close();
			zis.close();
			return "Enclosure Copied";
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception while copying enclosure--->" + e);
			return "Exception while copying enclosure";
		} finally {
			try {
				//if (maxf != null)
					maxf.close();
				//if (colse != null)
					colse.close();
			} catch (DfException e) {
				e.printStackTrace();
				log.warn("Exception e "+e.toString());
			}
		}

	}



	private ByteArrayOutputStream getByteOfFile(ZipInputStream zis, String object,String foldertype) {
	log.info(" In getByteOfFile method ");
		ByteArrayOutputStream boas=new ByteArrayOutputStream();
		try {
			//File destDir = new File("C:\\Users\\admin\\Desktop");
			byte[] buffer = new byte[1024];
			ZipEntry zipEntry = zis.getNextEntry();
			String folderpath="";
			while (zipEntry != null) {
				log.info("zipEntry name "+zipEntry.getName());
				if(foldertype.contains("enclosure"))
					folderpath="enclosures/"+object;
				else
					folderpath="notings/"+object;
				if (!zipEntry.isDirectory() && zipEntry.getName().contains(folderpath)) {
					log.info("It is present in folderpath");
					String objname=zipEntry.getName().substring(zipEntry.getName().lastIndexOf("/")+1, zipEntry.getName().length());
					log.info("objname "+objname);
					// write file content
					//FileOutputStream fos = new FileOutputStream(destDir+"\\"+objname);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						boas.write(buffer, 0, len);
					}
//					boas.writeTo(fos);
//					fos.close();
					return boas;
					//boas.close();

				}
				zipEntry = zis.getNextEntry();
			}
			}
		catch(Exception ex) {
			log.warn("Exception "+ex.toString());
			
		}
		return boas;

	}



	public String copyNotingToDepFileNoting(IDfSession session, PartCaseBean pb, String destFile, InputPBean bean1, byte[] bs,String nofilename)
			throws DfException {
		IDfCollection colse1 =null;
		IDfCollection maxf1=null;
		log.warn("Copy PartCase Noting to Department File BM");
		List<InputPBean> beanN = new ArrayList<>();
		String trim1 = destFile;
		String ss1 = trim1.substring(0, trim1.lastIndexOf("/"));
		log.warn("After trimming destfile " + ss1);
		String que = "select count(*) as total from iaf_file_content where folder('" + ss1 + "',descend) ";
		colse1 = getColl(session, que);
		int starttotal = 0;
		if (colse1.next()) {
			starttotal = colse1.getInt("total");
			log.warn("Total file count in destination " + starttotal);
		}
		String maxfnoQuery = "select fno from iaf_file_content  where folder('" + ss1
				+ "',descend) order by fno desc enable (RETURN_TOP 1)";
		maxf1 = getColl(session, maxfnoQuery);
		int mfno = 0;
		if (maxf1.next()) {
			mfno = maxf1.getInt("fno");
			log.warn("Max fno from desination path" + mfno);
		}
		int mfno1 = mfno + 1;
		log.warn("MF NO " + mfno1);
		IDfFolder destinationDirectory = session.getFolderByPath(destFile);	
		int totalCountN = pb.getNotingCount();
		log.warn("total Noting count is" + totalCountN);
		try {
			ByteArrayInputStream s=new ByteArrayInputStream(bs);
			ZipInputStream zis = new ZipInputStream(s);
			log.info("ByteArrayInputStream "+s);
			log.info("ZipInputStream "+zis);
			String alogic = null;int j=0;
			for (int i = totalCountN-1; i>=0; i--) {
				InputPBean inputPartBean1 = new InputPBean();
				log.warn("in for loop " + i);
				alogic = ((mfno1-1) + str(i));
				log.warn("logic" + alogic);
				if (pb.getNobjName().get(j).isEmpty() || pb.getNobjName().get(j)==null) {
					log.warn("Noting folder empty");
					break;
				}else {
					String object =pb.getNobjName().get(j);
					log.warn("ObjectName" + object);
				//	String subj = pb.getNsubject().get(j);
					String subj = nofilename;

					String dfile = pb.getDisplayFileName();
					String fstat = "";
					if(pb.getNfileStatus().get(j))
						fstat = "S";
					else
						fstat = "D";
					String sau = pb.getSau();
					String cau = pb.getCau();
					String fnme = pb.getFileName();
					String contenttype = pb.getNcontentType().get(j);
					String byname = pb.getFileCreatedBy();
					String bNo=pb.getBlockNo();
					log.warn("content type" + contenttype);
					ByteArrayOutputStream bao=getByteOfFile(zis,object,"noting");
					
					byte[] data = bao.toByteArray();
					log.info("data "+data);
					ByteArrayInputStream stream = new ByteArrayInputStream(data);
					inputPartBean1.setFileSub(pb.getFileSub());
					inputPartBean1.setDfile_name(pb.getDfile_name());
					inputPartBean1.setBlockNo(bNo);
					inputPartBean1.setSubSection(pb.getSubSection());
					
					inputPartBean1.setFno(mfno1-1);
					inputPartBean1.setMf_flag_number(alogic);
					inputPartBean1.setFile_name(fnme);
					inputPartBean1.setFile_status(fstat);
					inputPartBean1.setDisplay_file_name(dfile);
					inputPartBean1.setObjectname(object);
					inputPartBean1.setSubject(subj);
					inputPartBean1.setContenttype(contenttype);
					inputPartBean1.setInputstream(stream);
					inputPartBean1.setBy_name(byname);
					inputPartBean1.setSau(sau);	
					inputPartBean1.setCau(cau);
					inputPartBean1.setBy("iaf_dak_content");
					bao.close();
					stream.close();
				}
				beanN.add(inputPartBean1);
				j++;
				MergeClass c=new MergeClass();
				c.partCopy(session, inputPartBean1, destinationDirectory);
			}
			log.info(" Noting merged ");
			s.close();
			zis.close();
			return "Noting Copied";

		} catch (Exception e) {

			e.printStackTrace();
			log.error("Exception while copyinh Noting--->" + e);
			return "Exception while copying Noting";
		} finally {
			try {
				//if (colse1 != null)
					colse1.close();
				//if (maxf1 != null)
					maxf1.close();
			} catch (DfException e) {
				e.printStackTrace();
				log.warn("Exception e "+e.toString());
			}
		}

	}

	private String str(int i) {
		return i < 0 ? "" : str((i / 26) - 1) + (char) (65 + i % 26);
	}



	private IDfCollection getColl(IDfSession session, String query) {
		IDfQuery dfquery = new DfQuery();
		dfquery.setDQL(query);
		IDfCollection col = null;
		try {
			log.warn("DQL :- " + dfquery.getDQL());
			col = dfquery.execute(session, 1);
			if (col != null)
				return col;
		} catch (DfException e) {
			log.warn("Exception e "+e.toString());
			e.printStackTrace();
		}
		return null;
	}







}
