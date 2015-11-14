package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**Serialization of class object*/
public class ReadWriteSerial {
	/**write object to file*/
	public void writeToFite(Object net) throws IOException{
		FileOutputStream fos = new FileOutputStream("networkSerial.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try{
			oos.writeObject(net);
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		finally{
			oos.flush();
			oos.close();
		}
	}
	/**read object from file*/
	public Object readFromFile() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("networkSerial.out");
		ObjectInputStream oin = new ObjectInputStream(fis);
		Object net = null;
		try{
			net = oin.readObject();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		finally{
			oin.close();
		}
		return net;
	}
}
