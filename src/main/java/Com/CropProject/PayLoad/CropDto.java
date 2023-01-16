package Com.CropProject.PayLoad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CropDto {

	 private int cropId;
	 private String cropName;
	@Override
	public String toString() {
		return "CropDto [cropId=" + cropId + ", cropName=" + cropName + "]";
	}
	 
}
