package Com.CropProject.repo;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import Com.CropProject.entity.Mandi;


public interface MandiRepo extends JpaRepository<Mandi, Integer>{
	
 List <Mandi>findBytahsilNameContaining(String tahsilName);
 
 Page<Mandi>findBytahsilNameContaining(String tahsil,Pageable pageable);
}
