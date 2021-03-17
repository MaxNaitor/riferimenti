package mappers;

import java.util.List;

import pojo.TracciaPOJO;

public interface TracciaMapper {

	public List<TracciaPOJO> findTracceByAlbum(Long id);
	
	public void insert (TracciaPOJO t);
}
