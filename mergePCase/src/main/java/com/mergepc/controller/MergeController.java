package com.mergepc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.mergepc.mergeBean.InputPBean;
import com.mergepc.mergeBean.JsonBean;
import com.mergepc.mergeBean.PartCaseBean;
import com.mergepc.service.CopyPCase;
import com.mergepc.service.DctmTasks;
import com.mergepc.service.SortData;
import com.mergepc.utility.ConfigUtility;
import com.mergepc.utility.SessionFactory;
import com.mergepc.utility.UtilSessionFactory;


@RestController
public class MergeController {
	Logger log = Logger.getLogger(MergeController.class);
	@Value("${dctm.username}")
	private String user1;
	@Value("${dctm.password}")
	private String pass1;
	@Value("${dctm.repository}")
	private String repo1;

	@Autowired
	UtilSessionFactory  utilSessionFactory;

	@Autowired
	DctmTasks dt;

	@Autowired
	SortData sd;

	@Autowired
	ConfigUtility configUtility;

	@GetMapping(path = "/hello")
	@ResponseBody
	public ResponseEntity<String> sayHello() throws DfException {

		log.warn("Session fetching");


		IDfSession session = null;
		String userRepo = configUtility.getProperty("user");  // close when sending to IAF
		String userpassword = configUtility.getProperty("password");
		String dfcrepository = configUtility.getProperty("repository");
		SessionFactory sessionFactory = new SessionFactory(userRepo, userpassword, dfcrepository);

		session = sessionFactory.getSession();

		log.warn("Session fetched");
		log.warn("session " + session);
		try {
			if (session == null)
				return new ResponseEntity<String>("session is null", HttpStatus.OK);
			log.warn("Hello 1 ");
			System.out.println("Hello 2");
			return new ResponseEntity<String>("hello", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);

		} finally {
			sessionFactory.destroySession(session);
		}

	}
	
	@GetMapping(path = "/uploaddoc")
	@ResponseBody
	public ResponseEntity<String> uploadOnDCTM() throws DfException {

		log.warn("Session fetching");


		IDfSession session = null;
		String userRepo = configUtility.getProperty("user");  // close when sending to IAF
		String userpassword = configUtility.getProperty("password");
		String dfcrepository = configUtility.getProperty("repository");
		SessionFactory sessionFactory = new SessionFactory(userRepo, userpassword, dfcrepository);

		session = sessionFactory.getSession();

		log.warn("Session fetched");
		log.warn("session " + session);
		try {
			if (session == null)
				return new ResponseEntity<String>("session is null", HttpStatus.OK);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
		//	File file = new File("C:\\Users\\admin\\Documents\\Costa docs\\Audit Trail.docx");	
			log.warn("Creating doc ");
			//InputStream is=null;
			try {
			//	is = new FileInputStream(file);	
				ClassPathResource resource = new ClassPathResource("Audit Trail.docx");
				InputStream is = resource.getInputStream();
//				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//				InputStream is = classloader.getResourceAsStream("Audit Trail.docx");
				// InputStream is = getClass().getClassLoader().getResourceAsStream("Audit Trail.docx");

				int nRead;
				byte[] data = new byte[16384];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					bos.write(data, 0, nRead);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			IDfDocument doc = (IDfDocument) session.newObject("iaf_ns_content");
			doc.setObjectName("Audit Trail");
			doc.setContentType("msw12");
			doc.setContent(bos);
			doc.link("/GovOffice/WAC/7WG/7WG.ACO/ACO/7WG-100-1-ACO BM-I/7WG.ACO-7WG-100-1-ACO BM-I/BM/Noting");
			doc.save();
			log.info("Created doc at destination path");
			System.out.println("Created doc at destination path");
			bos.close();
			bos.flush();

		}catch(DfException d){
			log.warn("Exception e in dfc "+d.toString());
			System.out.println("Exception e in dfc "+d.toString());
			
		}
		catch(Exception d){
			log.warn("Exception e "+d.toString());
			System.out.println("Exception e "+d.toString());



		}
			return new ResponseEntity<String>("hello", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);

		} finally {
			sessionFactory.destroySession(session);
		}

	}
	@PostMapping(path = "/mergeDoc")
	public ResponseEntity<String> mergedoc(@RequestBody JsonBean jb) {
		IDfCollection collection = null;
		log.warn("Session fetching");

		IDfSession session = null;
		String userRepo = configUtility.getProperty("user");  // close when sending to IAF
		String userpassword = configUtility.getProperty("password");
		String dfcrepository = configUtility.getProperty("repository");
		System.out.println(jb.getId());
		//		//		String fileZip = "C:\\Users\\admin\\Desktop\\zipfolder.zip";
		JSONObject json = new JSONObject();
		log.info("Input JsonBean "+jb);
		PartCaseBean pb =sd.getdataInPartCaseBean(jb);
		log.info("PartCaseBean "+pb);

		try {
			SessionFactory sessionFactory = new SessionFactory(userRepo, userpassword, dfcrepository);
			session = sessionFactory.getSession();
			log.warn("Session fetched");
			log.warn("session " + session);
			if(session!=null) {
				log.info("Session "+session);
				PartCaseBean pbean =dt.getDestPath(session,pb);
				if(pbean.getFilePath().isEmpty() || pbean.getFilePath()==null) {

					json.put("Result", "destination is empty");
					return new ResponseEntity<String>(json.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				else {
					InputPBean bean=new InputPBean();
					bean.setDestID(pbean.getFilePath());
					CopyPCase service=new CopyPCase();			
					String enclosureResult=service.copyEnclosureToDepFileBm(session,pb, pbean.getFilePath(), bean,jb.getFile(),jb.getNofFileName());
					String NotingResult=service.copyNotingToDepFileNoting(session,pb,pbean.getFilePath(), bean,jb.getFile(),jb.getNofFileName());
					json.put("Result Enclosure", enclosureResult);
					json.put("Result Noting", NotingResult);
					sessionFactory.destroySession(session);
				}
			}
			else {
				log.warn("Session is null");
				json.put("Session","session is null");
			}
		}catch(Exception ex) {
			log.warn("Exception ex "+ex.toString());
			json.put("Exception ", ex.toString());
			return new ResponseEntity<String>(json.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		finally {
			if (collection != null) {
				try {
					collection.close();
				} catch (DfException e) {
					e.printStackTrace();
				}
				// utilSessionFactory.releaseSession();
			}
		}
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK); 

	}
}
