package smart.packaging.technology.services.resource.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smart.packaging.technology.services.dto.Input;

import javax.validation.Valid;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping(value="/api/v1")
@Validated
public class MainResourceImpl {

    @PostMapping(
            path = "sendInput",
            headers = "content-type=application/json",
            consumes = "application/json")
    public String sendInput(@Valid @RequestBody Input request) {
        log.info("Got input: {}", request);
        return "OK";
    }
}