package Com.CropProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Mandi {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int mandiId ;
	private  String mandiName;
	  @Value("${cases.caseList.numberOfCasesPerPage}")
	private  String tahsilName;
	
}
