package com.opentext.sample.code;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import com.actuate.activeportal.idapiadapter.ActuateAPIEx;
import com.actuate.activeportal.idapiadapter.ActuateAPILocatorEx;
import com.actuate.schemas.ActuateSoapPort;
import com.actuate.schemas.GetFileDetails;
import com.actuate.schemas.GetFileDetailsResponse;

public class BDOTimestamp {	
	private ActuateSoapPort proxy;
	private ActuateAPIEx    actuateAPI;
	//private Authenticator 	auth;
	//private String ihub;
	
	public BDOTimestamp() {
		actuateAPI = new ActuateAPILocatorEx();		
	}
	
	public void authenticate(String authid, String ihub, String volume) {
		try {			
			proxy = actuateAPI.getActuateSoapPort(new URL(ihub));
					actuateAPI.setAuthId(authid);
					actuateAPI.setTargetVolume(volume);
		} catch (MalformedURLException | ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public Object getFileDetails(String bdo) {
		System.out.println("Getting file details");
		
		try {
			GetFileDetails 		   gfd = new GetFileDetails();
			GetFileDetailsResponse gfdr = new GetFileDetailsResponse();
			
			gfd.setFileName(bdo);
			gfdr = proxy.getFileDetails(gfd);
			
			return gfdr.getFile().getTimeStamp().getTimeInMillis();
		} catch (RemoteException e) {
			return e.getMessage();
			
		}
	}
}
