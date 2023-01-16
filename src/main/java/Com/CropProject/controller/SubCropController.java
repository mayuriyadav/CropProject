package Com.CropProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Com.CropProject.Config.AppConstants;
import Com.CropProject.PayLoad.SubCropDto;
import Com.CropProject.PayLoad.SubCropResponse;
import Com.CropProject.Response.ApiResponse;
import Com.CropProject.Service.SubCropService;


@RestController
public class SubCropController {

	@Autowired 
	private SubCropService subCropService;
	
	@PostMapping("/crop/subcrop/{cropId}")
	public ResponseEntity<SubCropDto> creatSubCrop(@RequestBody SubCropDto subCropDto,@PathVariable Integer cropId ){
		SubCropDto subCrop = this.subCropService.createSubCrop(subCropDto, cropId);
		return new ResponseEntity<SubCropDto>(subCrop,HttpStatus.CREATED);
	}
	
	@PutMapping("/subcrop/{subcropId}")
	public ResponseEntity< SubCropDto>updateSubCrop(@RequestBody SubCropDto subCropDto,@PathVariable Integer subcropId){
		SubCropDto subCropDto2 = this.subCropService .updateSubCrop(subCropDto, subcropId);
            return new ResponseEntity<SubCropDto>(subCropDto2,HttpStatus.OK);
	}
	
	@DeleteMapping("/subcrop/{subcropId}")
	public ResponseEntity<ApiResponse> deleteSubCrop(@PathVariable Integer subcropId){
		this.subCropService.deleteSubCrop( subcropId);
		 return new ResponseEntity(new ApiResponse("Success deleted !!!", true),HttpStatus.OK) ;
	}
	@GetMapping("/subcrop/{subcropId}")
	public ResponseEntity<SubCropDto> getSubCropByIdSub(@PathVariable Integer subcropId){
		SubCropDto subCropDto2 = this.subCropService.getSubCropByIdSub(subcropId);
		return new ResponseEntity<SubCropDto>(subCropDto2,HttpStatus.OK);
	}
	
	@GetMapping("/subcrop")
    public ResponseEntity<SubCropResponse> getAllSubCrop(
    		@RequestParam(value = "pageNumber",defaultValue =AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE ,required = false)Integer pageSize ,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortdir",defaultValue = AppConstants.SORT_DIR,required = false)String sortdir)	{
		
		SubCropResponse subCropResponse	= this.subCropService.getAllSubCrop(pageNumber,pageSize,sortBy,sortdir );
		return new ResponseEntity<SubCropResponse>(subCropResponse,HttpStatus.OK);
	}
	
	
	@GetMapping("/crop/subcrop/{cropId}")
	public  ResponseEntity<List<SubCropDto>>getByCrop(@PathVariable Integer cropId){
		List<SubCropDto> subCrop =this.subCropService .getByCrop(cropId);
		return new ResponseEntity<List<SubCropDto>>(subCrop,HttpStatus.OK);
	}
}
