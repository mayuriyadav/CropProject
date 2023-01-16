package Com.CropProject.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import Com.CropProject.Exception.ResourceNotFoundException;
import Com.CropProject.PayLoad.CropDto;
import Com.CropProject.PayLoad.CropResponse;
import Com.CropProject.Service.CropService;
import Com.CropProject.entity.Crop;
import Com.CropProject.repo.CropRepo;

@Service
public class CropServiceImpl implements CropService {
@Autowired
	private  CropRepo cropRepo;

@Autowired
private ModelMapper modelMapper;

	@Override 
	public CropDto createCrop(CropDto cropDto) {
	Crop crop = this.modelMapper.map(cropDto, Crop.class);
	Crop saveCrop  = this.cropRepo.save(crop);
	
		return this.modelMapper.map(saveCrop, CropDto.class);
	}

	@Override
	public CropDto updateCrop(CropDto cropDto, Integer cropId) {
		Crop crop1 = this.cropRepo.findById(cropId).orElseThrow(() -> new ResourceNotFoundException("Crop" ,"cropId",cropId));
		crop1 .setCropName(cropDto.getCropName());
		Crop saveCrop = this.cropRepo .save(crop1);
		return this.modelMapper.map(saveCrop, CropDto.class);
	}

	@Override
	public CropDto getCrop(Integer cropId) {
		Crop crop = this.cropRepo .findById(cropId).orElseThrow( () -> new ResourceNotFoundException("Crop" ,"cropId",cropId) );
		return this.modelMapper.map(crop,  CropDto.class);
	}

	@Override
	public CropResponse getallCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
		Sort sort= null;
		if(sortdir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Crop> pagecrop = this.cropRepo.findAll(p);
		
		List<Crop> findAllCrops= pagecrop.getContent();
		
		List<CropDto>collect = findAllCrops.stream().map(user -> this.modelMapper.map(user,CropDto.class)).collect(Collectors.toList());
		
		CropResponse response = new CropResponse();
		response.setContentCrop(collect);
		response.setPageNumber(pagecrop.getNumber());
		response.setPageSize(pagecrop.getSize());
		response.setTotalElement(pagecrop.getTotalElements());
		response.setTotalPages(pagecrop.getTotalPages());
		response.setLastPage(pagecrop.isLast());
		return response;
	}

	@Override
	public void deleteCrop(Integer cropId) {
	Crop crop = this.cropRepo.findById(cropId).orElseThrow( () -> new ResourceNotFoundException("Crop" ,"cropId",cropId) );
		this.cropRepo.delete(crop);
	}

}
