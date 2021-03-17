package interfaces;


import javax.ejb.Local;

import pojo.AlbumPOJO;

@Local
public interface TimerBeanLocal {
	
//	public void aggiungi (Album a);
	
	public void aggiungi (AlbumPOJO a);

}
