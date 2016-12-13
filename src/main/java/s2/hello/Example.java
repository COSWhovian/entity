package s2.hello;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s2.entities.ProjectTypeEntity;
import s2.service.impl.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by russl on 11/28/2016.
 */
//@RestController
//@EnableAutoConfiguration
public class Example {

//    @RequestMapping("/")
//    String home() {
//        List<ProjectTypeEntity> types = retrieveProjectTypes();
//
//        Gson gson = new Gson();
//        String s = gson.toJson(types);
//        return s;
////        return "Hello Gallifrey";
//    }
//
//    protected List<ProjectTypeEntity> retrieveProjectTypes() {
//        ProjectService service = new ProjectService();
//        List<ProjectTypeEntity> all = service.findAllProjectTypes();
//
////            ProjectTypeDao dao = new ProjectTypeDao();
////            List<ProjectTypeEntity> all = dao.findAllProjects();
////        List<String> list = new ArrayList<>();
////        List<String> collect = all.stream().map(e -> e.getProjectTypeCd()).collect(Collectors.toList());
//        return all;
//    }
//
//    protected List<String> retrieveProjectTypeCds() {
//        ProjectService service = new ProjectService();
//        List<ProjectTypeEntity> all = service.findAllProjectTypes();
//
////            ProjectTypeDao dao = new ProjectTypeDao();
////            List<ProjectTypeEntity> all = dao.findAllProjects();
//        List<String> list = new ArrayList<>();
//        List<String> collect = all.stream().map(e -> e.getProjectTypeCd()).collect(Collectors.toList());
//        return collect;
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }
}
