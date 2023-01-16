package Com.CropProject.Service;

import java.util.List;

import Com.CropProject.PayLoad.SubCropDto;
import Com.CropProject.PayLoad.SubCropResponse;


public interface SubCropService {

	  SubCropDto  createSubCrop(SubCropDto subCropDto,Integer cropId);
	  
	  SubCropDto   updateSubCrop(SubCropDto subCropDto, Integer subcropId);
	  
	  SubCropDto getSubCropByIdSub(Integer subcropId);
	  
	  public SubCropResponse getAllSubCrop(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
	  
	  void deleteSubCrop(Integer  subcropId) ;
	  
	  // get by cropId 
	  List<SubCropDto>getByCrop(Integer cropId);
}
