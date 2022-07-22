package com.mergepc.utility;

import org.apache.log4j.Logger;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;

public class SessionFactory {
	private static final Logger log = Logger.getLogger(SessionFactory.class);
	public IDfSession idfSession;

	IDfLoginInfo login = null;

	IDfClient client = null;
	IDfSessionManager sessMgr = null;

	private String username = null;
	private String password = null;
	private String repository = null;

	public SessionFactory(String username, String password, String repository) {
		super();
		this.username = username;
		this.password = password;
		this.repository = repository;
	}

	public IDfSession getSession() {

		log.warn("username " + username);
		//log.warn("password " + password);
		log.warn("repository " + repository);
		log.warn("Please Wait for IDfSession ...");
		System.out.println("Please Wait for IDfSession ...");
		idfSession = null;
		login = new DfLoginInfo();
		login.setUser(username);
		login.setPassword(password);
		client = new DfClient();
		sessMgr = client.newSessionManager();
		try {
			sessMgr.setIdentity(repository, login);
			idfSession = sessMgr.getSession(repository);
			log.warn("session: " + idfSession.getLoginUserName());
			System.out.println("session: " + idfSession.getLoginUserName());

			log.warn("Host Address : " + idfSession.getServerMap(repository).getString("i_host_addr"));
			System.out.println("Host Address : " + idfSession.getServerMap(repository).getString("i_host_addr"));
			return idfSession;
		} catch (Exception e) {
			log.error("Exception in getSession " + e.getMessage());
			return null;
		}

	}

	public void destroySession(IDfSession idfSession) {
		log.warn("Pre Destroy Called");
		sessMgr.release(idfSession);
		log.warn("IDfSession is Released ");
	}

}