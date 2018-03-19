package chat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ChatUtil
{
	//º”‘ÿ◊¢≤·–≈œ¢
	public static void loadPro(Properties pro, File file)
	{
		if(!file.exists())
			{
				try
					{
						file.createNewFile();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
			}
		
		try
			{
				pro.load(new FileInputStream(file));
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	
		@SuppressWarnings("unused")
		public static String getTimer()
		{
			SimpleDateFormat simpleDateFormat = new
					SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.format(new Date());
		}
}
