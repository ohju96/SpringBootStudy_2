package Project.melon.controller;

import Project.melon.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class MongoController {

    @Resource(name = "MongoService")
    private IMongoService mongoService;

    @GetMapping("mongo/test")
    public String test() throws Exception {

        mongoService.mongoTest();

        return "MongoDB Test !!";
    }
}
