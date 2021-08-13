package com.higgs.server.web.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "metadata")
public class MetaDataRest {
    private final BuildProperties buildProperties;

    @ResponseBody
    @GetMapping(value = "version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServerVersion() {
        return ResponseEntity.ok(this.buildProperties.getVersion());
    }
}

