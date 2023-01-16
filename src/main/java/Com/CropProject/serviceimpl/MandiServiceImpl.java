package Com.CropProject.serviceimpl;

import java.security.PublicKey;
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

import Com.CropProject.PayLoad.MandiDto;
import Com.CropProject.PayLoad.MandiResponse;
import Com.CropProject.Service.MandiService;
import Com.CropProject.entity.Mandi;
import Com.CropProject.repo.MandiRepo;


@Service
public class MandiServiceImpl implements  MandiService{
@Autowired
private MandiRepo mandiRepo;
@Autowired
private ModelMapper modelMapper;
	@Override
	public MandiDto createMandi(MandiDto mandiDto) {
     Mandi M =  this.modelMapper.map(mandiDto,Mandi.class );
     
     Mandi saveMandi = this.mandiRepo.save(M);
		return this.modelMapper.map(saveMandi, MandiDto.class);
	}

	@Override
	public MandiDto updateMandiDto(MandiDto mandiDto, Integer mandiId) {
		Mandi M = this.mandiRepo.findById(mandiId).orElseThrow( ()-> new  ResourceNotFoundException("Mandi", "mandiId",mandiId));
		M.setMandiName(mandiDto.getMandiName());
		M.setTahsilName(mandiDto.getTahsilName());
		Mandi saveMandi = this.mandiRepo.save(M) ;
		return this.modelMapper.map(saveMandi, MandiDto.class);
	}

	@Override
	public MandiDto Mandigetbyid( Integer mandiId) {
		Mandi M   = this.mandiRepo.findById(mandiId).orElseThrow(()-> new  ResourceNotFoundException("Mandi", "mandiId",mandiId));
		return this.modelMapper.map(M, MandiDto.class);
	}

	@Override
	public MandiResponse getAllMandi(String tahsilName,Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
		Sort sort= null;
		if(sortdir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Mandi> pageMandi =this.mandiRepo.findBytahsilNameContaining(tahsilName,p);
		
          List<Mandi> findMandiDtos = pageMandi.getContent();         
          
          List<MandiDto> collectDtos = findMandiDtos.stream().map(user -> this.modelMapper.map(user,MandiDto.class)).collect(Collectors.toList());
		
          MandiResponse response = new MandiResponse();
          
          response.setContentDtos(collectDtos);
          response.setPageNumber(pageMandi.getNumber());
          response .setPageSize(pageMandi.getSize());
          response .setTotalElement(pageMandi.getTotalElements());
          response.setLastPage(pageMandi.isLast());
          response .setTotalPages(pageMandi.getTotalPages());
          return  response;
	}

	@Override
	public void deleteMandi(Integer mandiId) {
	
		Mandi M = this.mandiRepo .findById(mandiId).orElseThrow(()-> new  ResourceNotFoundException("Mandi", "mandiId",mandiId));
	      
		this.mandiRepo.delete(M);
	}
	// searching

	@Override
	public List<MandiDto> SearchMandi(String tahsilName) {
		List<Mandi> mandi = this.mandiRepo.findBytahsilNameContaining(tahsilName);
		List<MandiDto> collectDtos = mandi.stream().map((ma) -> this.modelMapper.map(ma, MandiDto.class)).collect(Collectors.toList());
		return collectDtos;
		
	}

	@Override
	public List<MandiDto> getAllMandi() {
		List<Mandi> mandi = this.mandiRepo.findAll();
		List<MandiDto> collect = mandi.stream().map(user -> this.modelMapper.map(user,MandiDto.class)).collect(Collectors.toList());
		return collect;
	}

}
