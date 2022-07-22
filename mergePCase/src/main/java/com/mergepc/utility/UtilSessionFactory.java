package com.mergepc.utility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfLoginInfo;

@Component
public class UtilSessionFactory {
	Logger log = Logger.getLogger(UtilSessionFactory.class);
	public DfLoginInfo login;

	public IDfClient client;

	public IDfSessionManager sessMgr;

	public IDfSession idfSession;
	public String repo = "corp";

	public IDfSession getSession() {
		System.out.println("Please Wait for IDfSession ...");
		// login = new DfLoginInfo();
		System.out.println("login " + login);

		login.setUser("corp");
		login.setPassword("demo.demo");
		client = new DfClient();
		sessMgr = client.newSessionManager();
		try {
			sessMgr.setIdentity(repo, login);
			idfSession = sessMgr.getSession(repo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (idfSession != null) {
			return idfSession;
		}
		return null;
	}

	public IDfSession getSession(String user, String pass, String repo) {
		System.out.println("Please Wait for IDfSession ...");
		login = new DfLoginInfo();

		login.setUser(user);
		login.setPassword(pass);
		System.out.println("Login " + login);
		client = new DfClient();
		sessMgr = client.newSessionManager();
		try {
			sessMgr.setIdentity(repo, login);
			idfSession = sessMgr.getSession(repo);
			log.warn("session: " + idfSession.getLoginUserName());

			log.warn("Host Address : " + idfSession.getServerMap(repo).getString("i_host_addr"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		if (idfSession != null) {
			return idfSession;
		}
		return null;
	}

	public void releaseSession() {
		sessMgr = idfSession.getSessionManager();
		sessMgr.release(idfSession);
		System.out.println("IDfSession is Released ");
	}
}