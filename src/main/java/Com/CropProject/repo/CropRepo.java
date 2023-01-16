package Com.CropProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.CropProject.entity.Crop;

public interface CropRepo extends JpaRepository<Crop, Integer> {

}
