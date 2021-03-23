package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AlbumPOJO;
import pojo.UtentePOJO;

public interface UtenteMapper {
	
	public void insert (UtentePOJO u);
	
	public UtentePOJO accesso (UtentePOJO u);
	
	public void acquista (@Param("idUtente") Long idUtente, @Param("idAlbum") Long idAlbum);
	
	public void restituisci (@Param("idUtente") Long idUtente, @Param("idAlbum") Long idAlbum);
	
	public List<AlbumPOJO> getAcquisti (UtentePOJO u);

	public List<UtentePOJO> getAll();
	
}
