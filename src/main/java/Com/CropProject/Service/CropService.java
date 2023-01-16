package Com.CropProject.Service;



import Com.CropProject.PayLoad.CropDto;
import Com.CropProject.PayLoad.CropResponse;

public interface CropService {

	 CropDto createCrop(CropDto cropDto);
	 
	 CropDto updateCrop(CropDto cropDto , Integer cropId);
	 
	 CropDto getCrop(Integer cropId);
	 
	  public CropResponse getallCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
	 
	 void deleteCrop(Integer cropId);
}
