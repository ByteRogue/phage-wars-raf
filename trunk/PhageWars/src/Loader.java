import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Loader {
	public static ArrayList<VirusDefinition> loadViruses(){
		ArrayList<VirusDefinition> list = new ArrayList<VirusDefinition>();
		File file = new File("viruses");
		if (file.exists()){
			DataInputStream in;
			try {
				in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				int n;
				try {
					n = in.readInt();
					for (int i = 0; i < n; i++) {
						VirusDefinition def = new VirusDefinition();
						def.setStrength(in.readInt());
						def.setDexterity(in.readInt());
						def.setVitality(in.readInt());
						int red = in.readInt();
						int green = in.readInt();
						int blue = in.readInt();
						def.setColor(new Color(red, green, blue));
						list.add(def);
					}
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				file.createNewFile();
				DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				out.writeInt(0);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
