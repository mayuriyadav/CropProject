package Com.CropProject.PayLoad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MandiDto {
    
	
	private  int mandiId ;
	private  String mandiName;
	private  String tahsilName;
	@Override
	public String toString() {
		return "MandiDto [mandiId=" + mandiId + ", mandiName=" + mandiName + ", tahsilName=" + tahsilName + "]";
	}
}
