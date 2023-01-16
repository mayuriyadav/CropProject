package Com.CropProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor 
@Entity
@Table(name = "SubCrop")
public class SubCrop {
	

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	
	private int subcropId;
	private String subcropName ;

	@Override
	public String toString() {
		return "SubCrop [subcropId=" + subcropId + ", subcropName=" + subcropName + "]";
	}
	
	@ManyToOne
	private Crop crop;
	
}
