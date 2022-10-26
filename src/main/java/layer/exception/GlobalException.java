package layer.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({HamitMizrakException.class})
    public String notFound(){
        return "id is not ";
    }

    @ExceptionHandler({NullPointerException.class})
    public String nullPointerException(){
        return "@Autowired veya instance oluşturulmadı";
    }

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
    public String arrayIndexOutOfBoundsException(){
        return "olmayan dizideki indisie ulaşıyorsun dikkat";
    }
}