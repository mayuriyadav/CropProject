package Com.CropProject.controller;


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

import Com.CropProject.Config.Appcontants;
import Com.CropProject.PayLoad.CropDto;
import Com.CropProject.PayLoad.CropResponse;
import Com.CropProject.Response.ApiResponse;
import Com.CropProject.Service.CropService;



@RestController
public class CropController {

	@Autowired
	private CropService cropService;
	
	@PostMapping("/crop")
	public ResponseEntity<CropDto >createCrop(@RequestBody CropDto cropDto ){
		 CropDto cropDto2 =this.cropService .createCrop(cropDto);
		 return new ResponseEntity <CropDto>(cropDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/crop/{cropId}")
	public ResponseEntity<CropDto > updateCrop(@RequestBody CropDto cropDto,@PathVariable Integer cropId){
		CropDto cropDto2 = this.cropService .updateCrop(cropDto,cropId);
	return new ResponseEntity<CropDto>(cropDto2, HttpStatus.OK);
	}
	
	@GetMapping("/crop/{cropId}")
	public ResponseEntity<CropDto> getCrop(@PathVariable  Integer cropId){
		CropDto cropDto2 = this.cropService.getCrop(cropId);
		return new ResponseEntity<CropDto>(cropDto2, HttpStatus.OK);
	}
	        @GetMapping("/crop")
	        public ResponseEntity<CropResponse> getallCrops(@RequestParam(value = "pageNumber",defaultValue =Appcontants.PAGE_NUMBER,required = false)Integer pageNumber,
		            @RequestParam(value = "pageSize",defaultValue =Appcontants.PAGE_SIZE ,required = false)Integer pageSize ,
		            @RequestParam(value = "sortBy",defaultValue = Appcontants.SORT_BY,required = false)String sortBy,
		            @RequestParam(value = "sortdir",defaultValue = Appcontants.SORT_DIR,required = false)String sortdir){
	        	CropResponse cropDto2 = this.cropService.getallCrops(pageNumber,pageSize,sortBy,sortdir);
	        	return new ResponseEntity<CropResponse>(cropDto2, HttpStatus.OK);
	        }
	        @DeleteMapping("/crop/{cropId}")
			public ResponseEntity< ApiResponse> deleteCrop(@PathVariable Integer cropId) {
				this.cropService.deleteCrop(cropId);
				
				return new ResponseEntity<ApiResponse>( new ApiResponse("deleted successfully", true),HttpStatus.OK );
			}

}
