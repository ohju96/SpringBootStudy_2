package mongodb.mongo.controller;

import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.service.IMongoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class MongoController {

    @Resource(name = "MongoService")
    private IMongoService iMongoService;

    @GetMapping("mongo/test")
    public String test() throws Exception {

        iMongoService.mongoTest();

        return "MongoDB Test";
    }

}
