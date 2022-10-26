package layer.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class BeanDto {
    private Long id;
    private String beanName;
    private String beanData;


    //life cycle
    public void initialBean(){
        log.info("bean doğdu");
        System.out.println("bean doğdu");
    }
    public void destroyBean(){
        log.error("bean öldü");
        System.out.println("bean öldü");
    }
}
