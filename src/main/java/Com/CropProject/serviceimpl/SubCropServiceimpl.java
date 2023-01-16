package Com.CropProject.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import Com.CropProject.Exception.ResourceNotFoundException;
import Com.CropProject.PayLoad.SubCropDto;
import Com.CropProject.PayLoad.SubCropResponse;
import Com.CropProject.Service.SubCropService;
import Com.CropProject.entity.Crop;
import Com.CropProject.entity.SubCrop;
import Com.CropProject.repo.CropRepo;
import Com.CropProject.repo.SubCropRepo;



@Service
public class SubCropServiceimpl  implements SubCropService{

	@Autowired
	private  CropRepo cropRepo;
	@Autowired
	private SubCropRepo subCropRepo;
	@Autowired

	private ModelMapper modelMapper;
	@Override
	public SubCropDto createSubCrop(SubCropDto subCropDto ,Integer cropId) {
		Crop crop = this.cropRepo.findById(cropId).orElseThrow(() -> new  ResourceNotFoundException("Crop","cropId",cropId));
	 
		SubCrop subCrop = this.modelMapper.map(subCropDto,SubCrop.class);
		subCrop.setCrop(crop);
		SubCrop newAddSubCrop = this.subCropRepo.save(subCrop );
		
		return this.modelMapper.map(newAddSubCrop, SubCropDto.class);
	}

	@Override
	public SubCropDto updateSubCrop(SubCropDto subCropDto, Integer subcropId) {
	SubCrop subCrop = this.subCropRepo .findById(subcropId).orElseThrow(() -> new  ResourceNotFoundException("SubCrop","subcropId",subcropId));
		subCrop.setSubcropName(subCropDto.getSubcropName());
		SubCrop subCrop2  = this.subCropRepo.save(subCrop);
	return this.modelMapper.map(subCrop2, SubCropDto.class);
	}

	@Override
	public SubCropDto getSubCropByIdSub( Integer subcropId) {
		SubCrop subCrop = this.subCropRepo.findById(subcropId).orElseThrow(() -> new  ResourceNotFoundException("SubCrop","subcropId",subcropId));
		return this.modelMapper.map(subCrop, SubCropDto.class);
	}


	@Override
	public void deleteSubCrop(Integer subcropId) {
		
		SubCrop subCrop = this.subCropRepo .findById(subcropId).orElseThrow(() -> new  ResourceNotFoundException("SubCrop","subcropId",subcropId));
	this.subCropRepo.delete(subCrop);
	}

	@Override
	public SubCropResponse getAllSubCrop(Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
		
		Sort sort= null;
		if(sortdir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<SubCrop> pagecrop = this.subCropRepo.findAll(p); 
		
		List<SubCrop> findAllSubCrops= pagecrop.getContent();
		//List<SubCrop> findAllSubCrops= this.subCropRepo.findAll();
		
		List<SubCropDto>collect = findAllSubCrops.stream().map(user -> this.modelMapper.map(user,SubCropDto.class)).collect(Collectors.toList());
		
		SubCropResponse response = new SubCropResponse();
		response.setContentCropDtos(collect);
		response.setPageNumber(pagecrop.getNumber());
		response.setPageSize(pagecrop.getSize());
		response.setTotalElement(pagecrop.getTotalElements());
		response.setTotalPages(pagecrop.getTotalPages());
		response.setLastPage(pagecrop.isLast());
		return response;
	}

	@Override
	public List<SubCropDto> getByCrop(Integer cropId) {
	Crop crop = this.cropRepo .findById(cropId).orElseThrow(() -> new  ResourceNotFoundException("Crop","cropId",cropId));
		List<SubCrop> subCrop = this.subCropRepo.findByCrop(crop);
		List<SubCropDto> subCrop1= subCrop.stream().map((sub)->this.modelMapper.map(sub, SubCropDto.class)).collect(Collectors.toList());
	return subCrop1;
	}

}



//List<SubCrop> findAllSubCrops= this.subCropRepo.findAll();
//
//List<SubCropDto>collect = findAllSubCrops.stream().map(user -> this.modelMapper.map(user,SubCropDto.class)).collect(Collectors.toList());
//return collect;

//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-mail</artifactId>
//</dependency>
