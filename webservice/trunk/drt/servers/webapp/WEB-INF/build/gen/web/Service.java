package web;

/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Header:$
 */

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.beehive.sample.Address;
import org.apache.beehive.sample.AddressBook;
import org.apache.beehive.sample.AddressBookImpl;


@WebService(targetNamespace="http://controlhaus.org/ServiceControlTestServer", serviceName="ServiceControlTest")
//@SOAPBinding( style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.ENCODED)
public class Service implements AddressBook {

    AddressBook addressBook;

    /**
     * Constructor.
     */
    public Service() {
        addressBook = new AddressBookImpl();
    }
    
    /**
     * Web method that adds an entry to the AddressBook.
     * @param name
     * @param address
     */
    @WebMethod
    public void addEntry(String name, Address address) {
        addressBook.addEntry(name, address);
    }

    /**
     * Web method that queries the AddressBook.
     * @param name
     * @return
     */
    @WebMethod
    public Address getAddressFromName(String name) {
    	System.out.println("Get Address for: " + name);
    	if(name == null) return null;
    	if( 0 == "Jack".compareTo(name)) {
    		Address result = new Address();
    		result.setCity("Seattle");
    		result.setZip(98119);
    		return result;
    	}
        return addressBook.getAddressFromName(name);
    }
    
    @WebMethod
    public Address[] getAddressFromNames(String[] name) {
    	if(null == name) return null;
    	Address[] result = new Address[name.length];
    	for(int i=0; i< name.length; i++) {
    	 result[i] = addressBook.getAddressFromName(name[i]);
    	}
    	return result;
    }    
    
    @WebMethod
    @Oneway
    public void oneWayWithParam(String param1) {
    	return;
    }
    
    
     @WebMethod
    @Oneway
    public void oneWayWithNoParam() {
    	return;
    }
    
    
    
    @WebMethod
    public String simpleNoParamMethod () {
    	return "No Param method";
    }
    
    /**
     * This method is not exposed by the Web Service and can only be used
     * locally.
     * @return A random string.
     */
    public String notWebService() {
        return "Not available through Web service";
    }
    
    
}
