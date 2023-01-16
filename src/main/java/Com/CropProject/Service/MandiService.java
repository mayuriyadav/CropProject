package Com.CropProject.Service;

import java.util.List;

import Com.CropProject.PayLoad.MandiDto;
import Com.CropProject.PayLoad.MandiResponse;


public interface MandiService {

	MandiDto createMandi (MandiDto mandiDto);
	
	MandiDto updateMandiDto (MandiDto mandiDto ,Integer mandiId);
	
	MandiDto Mandigetbyid(Integer mandiId);
	
	public MandiResponse getAllMandi(String tahsilName,Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
	
	void deleteMandi(Integer mandiId);
	
	List<MandiDto>SearchMandi(String tahsilName);
	
	List<MandiDto>getAllMandi();
}
