package mt.com.uom.project.pest.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum FileManagement {
	INSTANCE;
	
	public void writeObjectToFile(Object object, String filePath) throws IOException {
		
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		try {
			fout = new FileOutputStream(filePath, false); 
			oos = new ObjectOutputStream(fout);
			oos.writeObject(object);
		}
		catch (Exception ex) {
			
		}
		finally {
			if (null != fout) {
				fout.close();
			}
			
			if (null != oos) {
				oos.close();
			}
		}
	}
	
	public Object readObjectFromFile(String filePath) throws IOException {

		Object result = null;
		FileInputStream fin = null;
		ObjectInputStream ois = null;		
		
		try {
			fin = new FileInputStream(filePath);
			ois = new ObjectInputStream(fin);
			result = ois.readObject();
		}
		catch (Exception ex) {
			
		}
		finally {
			if (null != fin) {
				fin.close();
			}
			
			if (null != ois) {
				ois.close();
			}
		}
		return result;
	}
	
	public boolean fileExists(String filePath) {
		File f = new File(filePath);
		return (f.exists() && (!f.isDirectory()));
	}
	
}
