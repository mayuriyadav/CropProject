package Com.CropProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.CropProject.entity.Crop;
import Com.CropProject.entity.SubCrop;

public interface SubCropRepo extends JpaRepository<SubCrop, Integer>{

	List<SubCrop>findByCrop(Crop crop );
}
