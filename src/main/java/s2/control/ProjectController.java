package s2.control;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@EnableAutoConfiguration
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping("/project")
    public String index() {
        System.out.println("Project Home Page");
        return "project home page";
    }


    @RequestMapping("/project/types")
    String home() {
        List<ProjectTypeEntity> types = retrieveProjectTypes();

        Gson gson = new Gson();
        String s = gson.toJson(types);
        return s;
    }

    protected List<ProjectTypeEntity> retrieveProjectTypes() {
//        ProjectService projectService = new ProjectService();
        List<ProjectTypeEntity> all = projectService.findAllProjectTypes();

        return all;
    }

    protected List<String> retrieveProjectTypeCds() {
//        ProjectService service = new ProjectService();
        List<ProjectTypeEntity> all = projectService.findAllProjectTypes();

        List<String> list = new ArrayList<>();
        List<String> collect = all.stream().map(e -> e.getProjectTypeCd()).collect(Collectors.toList());
        return collect;
    }


}