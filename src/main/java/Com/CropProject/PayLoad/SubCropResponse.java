package Com.CropProject.PayLoad;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class SubCropResponse {

	  private List<SubCropDto>contentCropDtos;
	  private int pageNumber;
	    private int pageSize;
	    private long totalElement;
	    private int totalPages;
	    private boolean lastPage;
}
