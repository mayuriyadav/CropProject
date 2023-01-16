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
import Com.CropProject.Config.appConstant;
import Com.CropProject.PayLoad.MandiDto;
import Com.CropProject.PayLoad.MandiResponse;
import Com.CropProject.Response.ApiResponse;
import Com.CropProject.Service.MandiService;

@RestController
public class MandiController {

	 @Autowired
	 private MandiService mandiService;
	 
	 @PostMapping("/mandi")
	 public ResponseEntity<MandiDto>createMandi( @RequestBody MandiDto mandiDto){
		 
		 MandiDto mandi = this.mandiService .createMandi(mandiDto);
		 return new  ResponseEntity<MandiDto>(mandi,HttpStatus.CREATED);
		 
	 }
	 
	 @PutMapping("/mandi/{mandiId}")
	 public ResponseEntity<MandiDto> updateMandiDto(@RequestBody MandiDto mandiDto ,@PathVariable Integer mandiId){
		 MandiDto mandi = this.mandiService .updateMandiDto(mandiDto,mandiId);
		 return new ResponseEntity<MandiDto>(mandi,HttpStatus.OK);
	 }
	 
	 @GetMapping("/mandi/{mandiId}")
	 public ResponseEntity<MandiDto>Mandigetbyid(@PathVariable Integer mandiId){
		 
		 MandiDto mandi = this.mandiService.Mandigetbyid( mandiId);
		 return new ResponseEntity<MandiDto>(mandi,HttpStatus.OK);
	 }
	 
	 @GetMapping("/mandi")
	  public ResponseEntity<MandiResponse>  getAllMandi(@RequestParam(value = "pageNumber",defaultValue =appConstant.PAGE_NUMBER,required = false)Integer pageNumber,
	            @RequestParam(value = "pageSize",defaultValue =appConstant.PAGE_SIZE ,required = false)Integer pageSize ,
	            @RequestParam(value = "sortBy",defaultValue = appConstant.SORT_BY,required = false)String sortBy,
	            @RequestParam(value = "sortdir",defaultValue = appConstant.SORT_DIR,required = false)String sortdir,
	            @RequestParam(value = "tahsilName",required = false) String tahsilName){
		 MandiResponse mandi = this.mandiService.getAllMandi(tahsilName,pageNumber,pageSize,sortBy,sortdir);
		 return new ResponseEntity<MandiResponse>(mandi,HttpStatus.OK);
		 
	 }
	 
	 @DeleteMapping("/mandi/{mandiId}")
	 public ResponseEntity<ApiResponse> deleteMandi(@PathVariable Integer mandiId){
		 
		 this.mandiService.deleteMandi(mandiId);
		  
		 return new ResponseEntity<ApiResponse>(new ApiResponse("delted successfully", true),HttpStatus.OK );
	 }
	 
	 // searching
	 @GetMapping(value="/mandi/search/{keywords}")
	 public ResponseEntity<List>SearchMandi(@PathVariable ("keywords") String tahsilName){
		 
		 List<MandiDto> Search  = this.mandiService .SearchMandi(tahsilName);
		
		 return new ResponseEntity<List>(Search,HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/mandi/search/")
	 public ResponseEntity<List<MandiDto>>getAllMandi(){
		 List<MandiDto> mandiDtos = this.mandiService.getAllMandi();
		 return new ResponseEntity<List<MandiDto>>(mandiDtos,HttpStatus.OK);
	 }
	 
}
