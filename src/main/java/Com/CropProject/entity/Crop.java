package Com.CropProject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "crop")
public class Crop {

	
	@Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	private int cropId;
	private String cropName ;
	
	 @OneToMany (mappedBy = "crop",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private  List<SubCrop> subcrops=new ArrayList<>();
}
