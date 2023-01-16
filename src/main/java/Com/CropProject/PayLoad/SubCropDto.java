package Com.CropProject.PayLoad;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor 

public class SubCropDto {

	private int subcropId ;
	private String subcropName ;
	
	private int cropId;
	@Override
	public String toString() {
		return "SubCropDto [subcropId=" + subcropId + ", subcropName=" + subcropName + "]";
	}
	
	
}
