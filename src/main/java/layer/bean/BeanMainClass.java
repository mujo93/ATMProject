package layer.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//lombok
//@RequiredArgsConstructor

//MVC
@Controller
public class BeanMainClass {
    //field

    //@Autowired
    private final  BeanConfig beanConfig;

    public BeanMainClass(BeanConfig beanConfig) {
        this.beanConfig = beanConfig;
    }
   /* @Autowired
    public BeanMainClass(BeanConfig beanConfig) {
        this.beanConfig = beanConfig;
    }*/

    // http://localhost:8080/bean/dto
    @GetMapping("/bean/dto")
    @ResponseBody
    public String getBeanMethod(){
        return beanConfig.beanDtoMethod()+" ";
    }
}